package com.chenk.chatroom.controller;

import com.chenk.chatroom.pojo.bean.MessageBean;
import com.chenk.chatroom.pojo.entity.Message;
import com.chenk.chatroom.pojo.param.MessageParam;
import com.chenk.chatroom.result.JsonResult;
import com.chenk.chatroom.service.MessageService;
import com.chenk.chatroom.socket.WebSocketServer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @RequestMapping(value = "/")
    public String index(Model m) {
//        List<Message> messages=messageService.query();
//        m.addAttribute("messages",messages);
        return "Index";
    }

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
    public void sendMessage(@RequestParam("userName") String userName, @RequestParam("message") String message) {
//        MessageParam param = new MessageParam();
//        param.setUserName(userName);
//        param.setMessage(message);
        MessageBean bean = new MessageBean();
        bean.setId(UUID.randomUUID().toString());
        bean.setCreateTime(new Date());
        bean.setMessage(message);
        bean.setUserName(userName);
        messageService.add(bean);
        try {
            WebSocketServer.sendInfo(GSON.toJson(bean), "1");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
