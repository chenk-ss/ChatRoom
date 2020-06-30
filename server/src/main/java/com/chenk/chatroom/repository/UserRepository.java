package com.chenk.chatroom.repository;

import com.chenk.chatroom.pojo.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBean, String>{
}
