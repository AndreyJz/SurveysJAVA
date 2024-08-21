package com.example.modules.responseoptions.domain.entity;

import java.sql.Timestamp;

public class ResponseOptions {
    private int id;
    private int optionValue;
    private int categoryCatalogId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int parentResponseId;
    private int questionId;
    private String typeComponentHtml;
    private String commentResponse;
    private String optionText;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOptionValue() {
        return optionValue;
    }
    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }
    public int getCategoryCatalogId() {
        return categoryCatalogId;
    }
    public void setCategoryCatalogId(int categoryCatalogId) {
        this.categoryCatalogId = categoryCatalogId;
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
    public int getParentResponseId() {
        return parentResponseId;
    }
    public void setParentResponseId(int parentResponseId) {
        this.parentResponseId = parentResponseId;
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getTypeComponentHtml() {
        return typeComponentHtml;
    }
    public void setTypeComponentHtml(String typeComponentHtml) {
        this.typeComponentHtml = typeComponentHtml;
    }
    public String getCommentResponse() {
        return commentResponse;
    }
    public void setCommentResponse(String commentResponse) {
        this.commentResponse = commentResponse;
    }
    public String getOptionText() {
        return optionText;
    }
    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    
}
