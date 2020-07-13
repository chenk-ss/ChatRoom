package com.chenk.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

}
