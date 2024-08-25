package com.example.modules.responsequestions.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
import com.example.modules.responseoptions.application.FindResponseOptionsByNameUC;
import com.example.modules.responseoptions.application.ListResponseOptionsUC;
import com.example.modules.responsequestions.application.*;
import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.subresponseoptions.application.FindSubresponseOptionsByIdUC;
import com.example.modules.subresponseoptions.application.FindSubresponseOptionsBySubresponseTextUC;
import com.example.modules.subresponseoptions.application.ListSubresponseOptionsUC;

import java.util.*;

public class ResponseQuestionController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private CreateResponseQuestionUC createResponseQuestionUC;
    private FindResponseQuestionByIdUC findResponseQuestionByIdUC;
    private FindResponseQuestionByNameUC findResponseQuestionByNameUC;
    private ListResponseQuestionsUC listResponseQuestionsUC;
    Map<String, List<Object>> mapOfList;
    private ListResponseOptionsUC listResponseOptionsUC;

    // Handle Create
    public ResponseQuestionController(CreateResponseQuestionUC createResponseQuestionUC) {
        this.createResponseQuestionUC = createResponseQuestionUC;
    }

    public ResponseQuestionController(ListResponseQuestionsUC listResponseQuestionsUC) {
        this.listResponseQuestionsUC = listResponseQuestionsUC;
    }

    public ResponseQuestionController(FindResponseQuestionByIdUC findResponseQuestionByIdUC) {
        this.findResponseQuestionByIdUC = findResponseQuestionByIdUC;
    }

    public void createResponseQuestion(Map<Integer,List<String>> responsesOP, Map<Integer,List<String>> subResponses) {
        responsesOP.forEach((id,info) -> {
            ResponseQuestion responseQuestion = new ResponseQuestion();
            responseQuestion.setResponseId(Integer.parseInt(info.get(0)));
            responseQuestion.setSubresponsesId(0);
            responseQuestion.setResponseText(info.get(1));
            this.createResponseQuestionUC.create(responseQuestion);
        });
        subResponses.forEach((id,info) -> {
            ResponseQuestion responseQuestion = new ResponseQuestion();
            responseQuestion.setResponseId(id);
            responseQuestion.setSubresponsesId(Integer.parseInt(info.get(0)));
            responseQuestion.setResponseText(info.get(1));
            this.createResponseQuestionUC.create(responseQuestion);
        });
    }

    public void listResponseQuestions() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        this.listController = new ListController(responseQuestion, listResponseQuestionsUC);
    }

    public void findResponseQuestionById() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        this.searchController = new SearchController(responseQuestion, findResponseQuestionByIdUC);
    }
}