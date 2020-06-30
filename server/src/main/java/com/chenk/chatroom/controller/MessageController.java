package com.chenk.chatroom.controller;

import com.chenk.chatroom.pojo.bean.MessageBean;
import com.chenk.chatroom.pojo.entity.Message;
import com.chenk.chatroom.pojo.param.MessageParam;
import com.chenk.chatroom.result.JsonResult;
import com.chenk.chatroom.service.MessageService;
import com.chenk.chatroom.socket.WebSocketServer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
public class MessageController {

    Gson GSON = new Gson();

    @Resource
    private MessageService messageService;
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "/query/history")
    public JsonResult<List<Message>> history() {
        JsonResult<List<Message>> result = new JsonResult<List<Message>>();
        List<Message> messages = messageService.query();
        result.setData(messages);
        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/send")
    public JsonResult<String> sendMessage(@RequestParam("message") String message) {
        JsonResult<String> result = new JsonResult<>();
        MessageBean bean = new MessageBean();
        bean.setId(UUID.randomUUID().toString());
        bean.setCreateTime(new Date());
        bean.setMessage(message);
        bean.setUserName((String) redisTemplate.opsForValue().get("TOKEN:" + request.getHeader("Token") + ",USERNAME:"));
        messageService.add(bean);
        try {
            WebSocketServer.sendInfo(GSON.toJson(bean), "1");
            result.setMessage("发送成功");
            result.setSuccess(true);
        } catch (IOException e) {
            log.error(e.getMessage());
            result.setMessage("发送失败");
            result.setSuccess(false);
        } finally {
            return result;
        }
    }
}
