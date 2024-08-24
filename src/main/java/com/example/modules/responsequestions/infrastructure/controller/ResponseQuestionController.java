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
    private UpdateResponseQuestionUC updateResponseQuestionUC;
    private DeleteResponseQuestionUC deleteResponseQuestionUC;
    private FindResponseQuestionByIdUC findResponseQuestionByIdUC;
    private FindResponseQuestionByNameUC findResponseQuestionByNameUC;
    private ListResponseQuestionsUC listResponseQuestionsUC;
    Map<String, List<Object>> mapOfList;
    private ListResponseOptionsUC listResponseOptionsUC;
    private FindResponseOptionsByNameUC findResponseOptionsByName;
    private ListResponseOptionsUC listSubresponseOptionsUC;
    private FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC;

    // Handle Create
    public ResponseQuestionController(CreateResponseQuestionUC createResponseQuestionUC, ListResponseQuestionsUC listResponseQuestionsUC, FindResponseQuestionByNameUC findResponseQuestionByNameUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, ListSubresponseOptionsUC listSubresponseOptionsUC, FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC) {
        this.createResponseQuestionUC = createResponseQuestionUC;
        this.listResponseQuestionsUC = listResponseQuestionsUC;
        this.findResponseQuestionByNameUC = findResponseQuestionByNameUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByName = findResponseOptionsByNameUC;
        this.listSubresponseOptionsUC = listResponseOptionsUC;
        this.findSubresponseOptionsBySubresponseTextUC = findSubresponseOptionsBySubresponseTextUC;
    }

    // Handle List
    public ResponseQuestionController(ListResponseQuestionsUC listResponseQuestionsUC) {
        this.listResponseQuestionsUC = listResponseQuestionsUC;
    }

    // Handle Search
    public ResponseQuestionController(FindResponseQuestionByIdUC findResponseQuestionByIdUC) {
        this.findResponseQuestionByIdUC = findResponseQuestionByIdUC;
    }

    // Handle Update
    public ResponseQuestionController(UpdateResponseQuestionUC updateResponseQuestionUC, ListResponseQuestionsUC listResponseQuestionsUC, FindResponseQuestionByNameUC findResponseQuestionByNameUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, ListSubresponseOptionsUC listSubresponseOptionsUC, FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC) {
        this.updateResponseQuestionUC = updateResponseQuestionUC;
        this.listResponseQuestionsUC = listResponseQuestionsUC;
        this.findResponseQuestionByNameUC = findResponseQuestionByNameUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByName = findResponseOptionsByNameUC;
        this.listSubresponseOptionsUC = listResponseOptionsUC;
        this.findSubresponseOptionsBySubresponseTextUC = findSubresponseOptionsBySubresponseTextUC;
    }

    // Handle Delete
    public ResponseQuestionController(DeleteResponseQuestionUC deleteResponseQuestionUC, ListResponseQuestionsUC listResponseQuestionsUC, FindResponseQuestionByNameUC findResponseQuestionByNameUC) {
        this.deleteResponseQuestionUC = deleteResponseQuestionUC;
        this.listResponseQuestionsUC = listResponseQuestionsUC;
        this.findResponseQuestionByNameUC = findResponseQuestionByNameUC;
    }

    public void createResponseQuestion() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        mapOfList = new LinkedHashMap<>();
        this.createController = new CreateController(responseQuestion, createResponseQuestionUC, mapOfList);
    }

    public void updateResponseQuestion() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        mapOfList = new LinkedHashMap<>();
        this.updateController = new UpdateController(responseQuestion, updateResponseQuestionUC, listResponseQuestionsUC, null, mapOfList);
    }

    public void deleteResponseQuestion() {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        this.deleteController = new DeleteController(responseQuestion, deleteResponseQuestionUC, listResponseQuestionsUC, null);
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