package com.example.modules.subresponseoptions.domain.entity;

import com.example.UI.domain.service.GlobalService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class SubresponseOptions implements GlobalService {
    private int id;
    private int subresponseNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String componentHtml;
    private String subresponseText;
    private int responseOptionsId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSubresponseNumber() {
        return subresponseNumber;
    }
    public void setSubresponseNumber(int subresponseNumber) {
        this.subresponseNumber = subresponseNumber;
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
    public String getComponentHtml() {
        return componentHtml;
    }
    public void setComponentHtml(String componentHtml) {
        this.componentHtml = componentHtml;
    }
    public String getSubresponseText() {
        return subresponseText;
    }
    public void setSubresponseText(String subresponseText) {
        this.subresponseText = subresponseText;
    }
    public int getResponseOptionsId() {
        return responseOptionsId;
    }
    public void setResponseOptionsId(int responseOptionsId) {
        this.responseOptionsId = responseOptionsId;
    }

   @Override
    public Map<String, String> getTypes() {
        Map<String, String> typesMap = new LinkedHashMap<>();
        typesMap.put("Id", "Null");
        typesMap.put("SubresponseNumber", "TextField");
        typesMap.put("CreatedAt", "Null");
        typesMap.put("UpdatedAt", "Null");
        typesMap.put("ComponentHtml", "TextField");
        typesMap.put("SubresponseText", "TextField");
        typesMap.put("ResponseOptionsId", "JComboBox");
        return typesMap;
    }
 
}
