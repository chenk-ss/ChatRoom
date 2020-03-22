package com.chenk.chatroom.filter;

import com.chenk.chatroom.result.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 自定义过滤器，用来判断用户请求的header中是否存在Token属性，如果存在，则校验其是否存在<br/>
 * 校验规则为： Token = “xxxxx”，其中“xxxxx”为用户申请的访问令牌<br>
 * 校验全部通过，则执行下一个过滤器<br/>
 * 如果配置了{@link AuthorizationFilter#tokenUrl}属性，且客户访问的请求与该配置一致，则不进行认证，因为此时用户就是要来获取token的。
 **/
@Slf4j
@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    private static final String TOKEN = "Token";

    Gson GSON = new Gson();

    @Resource
    private RedisTemplate redisTemplate;

    @Value("${authorization.token.url}")
    private String tokenUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化authorizationFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("开始执行doFilter方法", AuthorizationFilter.class);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("request.url：" + request.getRequestURI());
        boolean authenticated = false;
        //若当前请求的连接为获取token的请求，则无需进行token认证
        if (tokenUrl != null && !"".equals(tokenUrl)) {
            if (request.getRequestURI().contains(tokenUrl)) {
                log.info("登录请求，无需进行认证信息验证");
                authenticated = true;
            }if (request.getRequestURI().contains("/user/register")) {
                log.info("注册请求，无需进行认证信息验证");
                authenticated = true;
            }
            if ("/".equals(request.getRequestURI())) {
                log.info("首页");
                authenticated = true;
            }
            if (request.getRequestURI().endsWith(".js")|| request.getRequestURI().endsWith(".ico")|| request.getRequestURI().contains("websocket")) {
                log.info("js、ico、websocket");
                authenticated = true;
            }
        }
        String error = null;
        //如果不是获取token的请求，则仅需验证
        if (!authenticated) {
            String authorization = request.getHeader(AuthorizationFilter.TOKEN);
            log.info("从header中获取到的认证信息：" + authorization);
            //验证是否提交了authorization信息
            if (authorization == null || "".equals(authorization)) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                error = "认证失败，请提交Authorization信息！";
            } else {
                JsonResult<Boolean> jsonResult = validateToken(authorization.trim());
                authenticated = jsonResult.isSuccess();
                if (!authenticated) {
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    error = jsonResult.getMessage();
                }
            }
        }
        if (authenticated) {//认证通过，执行下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //设置Header信息
//            response.setHeader("Content-Type", "application/json;charset=UTF-8");
//            JsonResult result = new JsonResult();
//            result.setMessage("用户认证失败，错误信息：" + error);
//            result.setSuccess(false);
//            ObjectMapper mapper = new ObjectMapper();
//            String errorString = mapper.writeValueAsString(result);
//            PrintWriter out = response.getWriter();
//            out.print(errorString);
//            out.close();
            response.sendRedirect("/?path=Login");
//            log.error(errorString);
        }
        log.info("doFilter方法执行完毕");

    }

    @Override
    public void destroy() {
        log.info("authorizationFilter已被销毁");
    }

    /**
     * 描述： 校验token是否正确，如果正确，则返回true，否则返回false
     * 参数： [token]
     * 返回结果： boolean
     **/
    private JsonResult<Boolean> validateToken(String token) {
        JsonResult<Boolean> result = new JsonResult<Boolean>();
        log.info("开始校验客户上送的token是否合法。", this.getClass());
        Object userName = redisTemplate.opsForValue().get("TOKEN:" + token + ",USERNAME:");
        if (userName == null || "".equals(userName)) {
            log.info("根据token未查找到相关认证信息，请核对token是否正确", this.getClass());
            result.setMessage("token不存在或已过期");
            result.setSuccess(false);
            return result;
        }
        log.info("根据token查询到相关认证信息，用户ID：" + userName);
        redisTemplate.expire("TOKEN:" + userName + ",VALUE:", 10L, TimeUnit.MINUTES);
        redisTemplate.expire("TOKEN:" + token + ",USERNAME:", 10L, TimeUnit.MINUTES);
        result.setSuccess(true);
        return result;
    }
}
