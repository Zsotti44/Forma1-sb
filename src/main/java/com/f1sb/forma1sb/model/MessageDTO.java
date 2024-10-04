package com.f1sb.forma1sb.model;

import java.time.LocalDate;

/**
 * MessageDTO, uzenetek adatbázis tábla
 */
public class MessageDTO {

    private final int id;
    private int userId;
    private String message;
    private LocalDate createdDate;

    public MessageDTO(int userId, int id, String message, LocalDate createdDate) {
        this.userId = userId;
        this.id = id;
        this.message = message;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
