package com.chenk.chatroom.controller;

import com.chenk.chatroom.pojo.param.MessageParam;
import com.chenk.chatroom.socket.WebSocketServer;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Log4j2
@Controller
public class MessageController {

    Gson GSON = new Gson();

    @RequestMapping(value = "/")
    public String index(Model m){
        return "Index";
    }

    @ResponseBody
    @RequestMapping(value = "/send")
    public void sendMessage(@RequestParam("userName") String userName,@RequestParam("message") String message){
        MessageParam param=new MessageParam();
        param.setUserName(userName);
        param.setMessage(message);
        try {
            WebSocketServer.sendInfo(GSON.toJson(param), "1");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
