package com.chenk.chatroom.pojo.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class Message {
    private String id;

    private String userId;

    private String userName;

    private String ip;

    private String message;

    private Date createTime;

    private String type;

    private Date groupId;
}
