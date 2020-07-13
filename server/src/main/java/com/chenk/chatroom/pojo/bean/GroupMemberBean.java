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
@Table(name = "TB_GROUP_MEMBER")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class GroupMemberBean {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ROLE_ID")
    private String roleId;
}
