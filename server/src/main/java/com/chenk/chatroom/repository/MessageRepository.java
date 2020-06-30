package com.chenk.chatroom.repository;

import com.chenk.chatroom.pojo.bean.MessageBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageBean, Long>{
}
