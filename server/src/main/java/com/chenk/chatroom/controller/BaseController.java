package com.chenk.chatroom.controller;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

/**
 * @Author chenk
 * @create 2020/3/15 15:18
 */
@Controller
public class BaseController {
    @Autowired
    HttpServletRequest request;
    @Resource
    HttpServletResponse response;

    @RequestMapping(value = "/")
    public String index(Model m, @PathParam("path") String path) {
        if (StringUtil.isNullOrEmpty(path)) {
            return "Index";
        }
        return path;
    }



}
