package com.example.modules.chapter.domain.entity;

import com.example.UI.domain.service.GlobalService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class Chapter implements GlobalService {
    private int id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String chapterNumber;
    private String chapterTitle;
    private int surveyId;

    public Chapter() {
    }

    public Chapter(int id, Timestamp createdAt, Timestamp updatedAt, String chapterNumber, String chapterTitle, int surveyId) {
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
        lista.put("Id","Null");
        lista.put("CreatedAt", "Null");
        lista.put("UpdatedAt", "Null");
        lista.put("ChapterNumber", "TextField");
        lista.put("ChapterTitle", "TextField");
        lista.put("SurveyId", "JComboBox");
        return lista;
    }
}
