package com.example.categoriescatalog.domain.entity;

import com.example.domain.service.GlobalService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class CategoriesCatalog implements GlobalService {
    private int id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String name;

    public CategoriesCatalog() {
    }

    public CategoriesCatalog(int id, Timestamp createdAt, Timestamp updatedAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, String> getTypes() {
        Map<String, String> lista = new LinkedHashMap<>();
        lista.put("Id", "Null");
        lista.put("CreatedAt", "Null");
        lista.put("UpdatedAt", "Null");
        lista.put("Name", "TextField");
        return lista;
    }
}
