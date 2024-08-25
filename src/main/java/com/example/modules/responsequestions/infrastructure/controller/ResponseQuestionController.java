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
    public ResponseQuestionController(CreateResponseQuestionUC createResponseQuestionUC, ListResponseQuestionsUC listResponseQuestionsUC, FindResponseQuestionByNameUC findResponseQuestionByNameUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, ListSubresponseOptionsUC listSubresponseOptionsUC, FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC) {
        this.createResponseQuestionUC = createResponseQuestionUC;
        this.listResponseQuestionsUC = listResponseQuestionsUC;
        this.findResponseQuestionByNameUC = findResponseQuestionByNameUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
    }

    public ResponseQuestionController(ListResponseQuestionsUC listResponseQuestionsUC) {
        this.listResponseQuestionsUC = listResponseQuestionsUC;
    }

    public ResponseQuestionController(FindResponseQuestionByIdUC findResponseQuestionByIdUC) {
        this.findResponseQuestionByIdUC = findResponseQuestionByIdUC;
    }

    public void createResponseQuestion() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        mapOfList = new LinkedHashMap<>();
        this.createController = new CreateController(responseQuestion, createResponseQuestionUC, mapOfList);
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