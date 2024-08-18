package com.example.question.domain.entity;

import com.example.domain.service.GlobalService;

import java.io.Serializable;
import java.sql.Date;
import java.util.Hashtable;

public class Question implements GlobalService {
    private int id;
    private Date createdAt;
    private Date updatedAt;
    private String questionNumber;
    private String responseType;
    private String commentQuestion;
    private String questionText;
    private int chapterId;

    public Question() {
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

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getCommentQuestion() {
        return commentQuestion;
    }

    public void setCommentQuestion(String commentQuestion) {
        this.commentQuestion = commentQuestion;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }


    @Override
    public Hashtable<String, String> getTypes() {
        Hashtable<String, String> types = new Hashtable<>();
        types.put("id", "TextField");
        types.put("createdAt", "JDateChooser");
        types.put("updatedAt", "JDateChooser");
        types.put("questionNumber", "TextField");
        types.put("responseType", "TextField");
        types.put("commentQuestion", "TextField");
        types.put("questionText", "TextField");
        types.put("chapterId", "JComboBox");
        return types;
    }
}
