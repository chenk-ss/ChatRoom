package com.chenk.chatroom.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private String id;

    private String userId;

    private String userName;

    private String ip;

    private String message;

    private Date createTime;
}
