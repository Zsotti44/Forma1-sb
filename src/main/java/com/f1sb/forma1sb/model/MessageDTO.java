package com.f1sb.forma1sb.model;

import java.sql.Timestamp;

/**
 * MessageDTO, uzenetek adatbázis tábla
 */
public class MessageDTO {

    private final int id;
    private Integer userId;
    private String message;
    private Timestamp createdDate;

    public MessageDTO(Integer userId, int id, String message, Timestamp createdDate) {
        this.userId = userId;
        this.id = id;
        this.message = message;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
