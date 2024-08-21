package com.example.modules.responseoptions.domain.entity;

import com.example.UI.domain.service.GlobalService;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseOptions implements GlobalService {
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


    @Override
    public Map<String, String> getTypes() {
        Map<String, String> lista = new LinkedHashMap<>();
        lista.put("Id", "Null");
        lista.put("OptionValue", "TextField");
        lista.put("CategoryCatalogId", "JComboBox");
        lista.put("CreatedAt", "Null");
        lista.put("UpdatedAt", "Null");
        lista.put("ParentResponseId", "JComboBox");
        lista.put("QuestionId", "JComboBox");
        lista.put("TypeComponentHtml", "TextField");
        lista.put("CommentResponse", "TextField");
        lista.put("OptionText", "TextField");
        return lista;
    }
}
