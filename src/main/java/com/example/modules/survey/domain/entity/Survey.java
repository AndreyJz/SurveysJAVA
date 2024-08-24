package com.example.modules.survey.domain.entity;

import com.example.UI.domain.service.GlobalService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Survey implements GlobalService {
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

    @Override
    public Map<String, String> getTypes() {
        Map<String, String> types = new HashMap<>();
        types.put("Id", "Null");
        types.put("CreatedAt", "Null");
        types.put("UpdatedAt", "Null");
        types.put("Description", "TextField");
        types.put("Name", "TextField");
        return types;
    }
}
