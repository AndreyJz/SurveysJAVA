package com.example.chapter.domain.entity;

import com.example.domain.service.GlobalService;

import java.sql.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Chapter implements GlobalService {
    private int id;
    private Date createdAt;
    private Date updatedAt;
    private String chapterNumber;
    private String chapterTitle;
    private int surveyId;

    public Chapter() {
    }

    public Chapter(int id, Date createdAt, Date updatedAt, String chapterNumber, String chapterTitle, int surveyId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chapterNumber = chapterNumber;
        this.chapterTitle = chapterTitle;
        this.surveyId = surveyId;
    }

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

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    @Override
    public Map<String, String> getTypes() {
        Map<String, String> lista = new LinkedHashMap<>();
//        lista.put("createdAt", "JDateChooser");
//        lista.put("updatedAt", "JDateChooser");
        lista.put("chapterNumber", "TextField");
        lista.put("chapterTitle", "TextField");
        lista.put("surveyId", "JComboBox");
        return lista;
    }
}
