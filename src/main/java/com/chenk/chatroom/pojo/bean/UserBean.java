package com.chenk.chatroom.pojo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "TB_USER")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserBean {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "realname")
    private String realName;

    @Column(name = "email")
    private String email;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "status")
    private int status;
}
