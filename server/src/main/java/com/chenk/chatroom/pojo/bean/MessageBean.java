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
    @Column(name = "id")
    private String id;

    @Column(name = "userid")
    private String userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "ip")
    private String ip;

    @Column(name = "message")
    private String message;

    @Column(name = "createtime")
    private Date createTime;
}
