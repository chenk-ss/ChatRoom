package com.chenk.chatroom.pojo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author chenk
 * @create 2020/7/13 21:34
 */
@Entity
@Data
@Table(name = "TB_GROUP")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class GroupBean {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADMIN_ID")
    private String adminId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "INTRODUCTION")
    private String introduction;
}
