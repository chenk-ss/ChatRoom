package com.chenk.chatroom.service;

import com.chenk.chatroom.pojo.bean.MessageBean;
import com.chenk.chatroom.pojo.entity.Message;

import java.util.List;

public interface MessageService {

    Boolean add(MessageBean bean);

    List<Message> query();

    Message toEntity(MessageBean bean);

    List<Message> toEntity(List<MessageBean> beans);
}
