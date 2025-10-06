package com.ktz.equalization.qa.websocket;

import java.sql.Timestamp;

public class Message {

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 发送者ID
     */
    private String fromId;

    /**
     * 发送者Name
     */
    private String fromName;

    /**
     * 接收者 userSocketSessionMap - ID ， 存放在集合中链接 - ID
     */
    private String toId;

    /**
     * 发送的信息
     */
    private String messageText;

    /**
     * 发送的时间
     */
    private Timestamp messageDate;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", fromId='" + fromId + '\'' +
                ", fromName='" + fromName + '\'' +
                ", toId='" + toId + '\'' +
                ", messageText='" + messageText + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}