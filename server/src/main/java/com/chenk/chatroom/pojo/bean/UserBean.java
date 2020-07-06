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
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REAL_NAME")
    private String realName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "STATUS")
    private int status;
}
