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
@Table(name = "TB_MESSAGE_RECORD")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class MessageRecordBean {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column(name = "IS_READ")
    private int isRead;
}
