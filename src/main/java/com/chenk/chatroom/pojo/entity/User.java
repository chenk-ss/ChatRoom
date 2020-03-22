package com.chenk.chatroom.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author chenk
 * @create 2020/3/14 18:10
 */
@Data
public class User {

    private String id;

    private String userName;

    private String password;

    private String realName;

    private String email;

    private Date createTime;

    private int status;
}
