package com.example.survey.domain.entity;

import java.sql.Date;

public class Survey {
    private int id;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private String name;

    public Survey() {
    }

    public Survey(int id, Date createdAt, Date updatedAt, String description, String name) {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
