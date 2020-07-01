package com.chenk.chatroom.controller;

import com.chenk.chatroom.pojo.bean.UserBean;
import com.chenk.chatroom.pojo.entity.User;
import com.chenk.chatroom.result.JsonResult;
import com.chenk.chatroom.service.UserService;
import com.chenk.chatroom.util.SHA256;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public JsonResult<String> getToken(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        JsonResult<String> result = new JsonResult<String>();
        System.out.println("userName:" + userName + "|password:" + password);
        User user = userService.queryUserByUserName(userName);
        if (user == null) {
            result.setMessage("用户不存在！");
            result.setSuccess(false);
            return result;
        }
        if (!password.equals(user.getPassword())) {
            result.setMessage("密码错误！");
            result.setSuccess(false);
            return result;
        }
        Object savedToken = redisTemplate.opsForValue().get("TOKEN:" + userName + ",VALUE:");
        if (savedToken != null && !"".equals(savedToken)) {
            result.setData(savedToken.toString());
            result.setSuccess(true);
            return result;
        }
        String token = SHA256.getSHA256(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        redisTemplate.opsForValue().set("TOKEN:" + userName + ",VALUE:", token, 10L, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("TOKEN:" + token + ",USERNAME:", userName, 10L, TimeUnit.MINUTES);
        result.setData(token);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("/register")
    public JsonResult<String> register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        JsonResult<String> result = new JsonResult<String>();
        System.out.println("userName:" + userName + "|password:" + password);
        User user = userService.queryUserByUserName(userName);
        if (user != null) {
            result.setMessage("用户名已存在！");
            result.setSuccess(false);
            return result;
        }
        UserBean userBean = new UserBean();
        userBean.setId(UUID.randomUUID().toString());
        userBean.setStatus(1);
        userBean.setUserName(userName);
        userBean.setPassword(password);
        userBean.setCreateTime(new Date());
        boolean b = userService.addUser(userBean);
        result.setSuccess(b);
        result.setMessage(b ? "新增成功" : "新增失败");
        return result;
    }
}
