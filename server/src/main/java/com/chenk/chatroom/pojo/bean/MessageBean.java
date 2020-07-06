package com.chenk.chatroom.pojo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "TB_MESSAGE")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class MessageBean {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IP")
    private String ip;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "GROUP_ID")
    private Date groupId;
}
