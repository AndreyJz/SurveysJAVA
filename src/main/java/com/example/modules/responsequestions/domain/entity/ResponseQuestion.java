package com.example.modules.responsequestions.domain.entity;

import com.example.UI.domain.service.GlobalService;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseQuestion implements GlobalService {
    private int id;
    private int responseId;
    private int subresponsesId;
    private String responseText;

    public ResponseQuestion() {
    }

    public ResponseQuestion(int id, int responseId, int subresponsesId, String responseText) {
        this.id = id;
        this.responseId = responseId;
        this.subresponsesId = subresponsesId;
        this.responseText = responseText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getSubresponsesId() {
        return subresponsesId;
    }

    public void setSubresponsesId(int subresponsesId) {
        this.subresponsesId = subresponsesId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public Map<String, String> getTypes() {
        Map<String, String> lista = new LinkedHashMap<>();
        lista.put("Id", "Null");
        lista.put("ResponseId", "JComboBox");
        lista.put("SubresponsesId", "JComboBox");
        lista.put("ResponseText", "TextField");
        return lista;
    }
}