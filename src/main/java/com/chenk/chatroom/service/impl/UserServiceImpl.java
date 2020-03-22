package com.chenk.chatroom.service.impl;

import com.chenk.chatroom.pojo.bean.UserBean;
import com.chenk.chatroom.pojo.entity.User;
import com.chenk.chatroom.repository.UserRepository;
import com.chenk.chatroom.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chenk
 * @create 2020/3/14 18:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Boolean addUser(UserBean bean) {
        return userRepository.save(bean) != null;
    }

    @Override
    public User queryUserByUserName(String userName) {
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setStatus(1);
        Example<UserBean> example = Example.of(userBean);
        List<UserBean> beans = userRepository.findAll(example);
        if (beans == null || beans.isEmpty()) {
            return null;
        }
        return toEntity(beans.get(0));
    }

    @Override
    public User toEntity(UserBean bean) {
        if (bean == null) {
            return null;
        }
        User entity = new User();
        entity.setId(bean.getId());
        entity.setUserName(bean.getUserName());
        entity.setPassword(bean.getPassword());
        entity.setRealName(bean.getRealName());
        entity.setEmail(bean.getEmail());
        entity.setCreateTime(bean.getCreateTime());
        entity.setStatus(bean.getStatus());
        return entity;
    }

    @Override
    public List<User> toEntity(List<UserBean> beans) {
        List<User> result = new ArrayList<>();
        for (UserBean bean : beans) {
            result.add(toEntity(bean));
        }
        return result;
    }
}
