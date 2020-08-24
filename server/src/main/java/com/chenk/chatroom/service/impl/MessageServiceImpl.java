package com.chenk.chatroom.service.impl;

import com.chenk.chatroom.pojo.bean.MessageBean;
import com.chenk.chatroom.pojo.entity.Message;
import com.chenk.chatroom.repository.MessageRepository;
import com.chenk.chatroom.service.MessageService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Override
    public Boolean add(MessageBean bean) {
        return messageRepository.save(bean) != null;
    }

    @Override
    public List<Message> query() {
        Sort.Order order = Sort.Order.asc("createTime");
        Sort sort = Sort.by(order);
        List<MessageBean> beanList = messageRepository.findAll(sort);
        return toEntity(beanList);
    }

    @Override
    public Message toEntity(MessageBean bean) {
        if (bean == null) {
            return null;
        }
        Message entity = new Message();
        entity.setId(bean.getId());
        entity.setUserId(bean.getUserId());
        entity.setUserName(bean.getUserName());
        entity.setIp(bean.getIp());
        entity.setCreateTime(bean.getCreateTime());
        entity.setIp(bean.getIp());
        entity.setMessage(bean.getMessage());
        entity.setType(bean.getType());
        entity.setGroupId(bean.getGroupId());
        return entity;
    }

    @Override
    public List<Message> toEntity(List<MessageBean> beans) {
        List<Message> result = new ArrayList<>();
        for (MessageBean bean : beans) {
            result.add(toEntity(bean));
        }
        return result;
    }
}
