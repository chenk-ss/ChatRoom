package com.chenk.chatroom.service;

import com.chenk.chatroom.pojo.bean.UserBean;
import com.chenk.chatroom.pojo.entity.User;

import java.util.List;

/**
 * @Author chenk
 * @create 2020/3/14 18:10
 */
public interface UserService {

    Boolean addUser(UserBean bean);

    User queryUserByUserName(String userName);

    User toEntity(UserBean bean);

    List<User> toEntity(List<UserBean> beans);
}
