package com.example.modules.responseoptions.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
import com.example.modules.categoriescatalog.application.*;
import com.example.modules.question.application.*;
import com.example.modules.responseoptions.application.*;
import com.example.modules.responseoptions.domain.entity.ResponseOptions;

import java.util.*;

public class ResponseOptionsController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private CreateResponseOptionsUC createResponseOptionsUC;
    private UpdateResponseOptionsUC updateResponseOptionsUC;
    private DeleteResponseOptionsUC deleteResponseOptionsUC;
    private FindResponseOptionsByIdUC findResponseOptionsByIdUC;
    private FindResponseOptionsByNameUC findResponseOptionsByNameUC;
    private FindResponseOptionsByCategoryCatalogIdUC findResponseOptionsByCategoryCatalogIdUC;
    private ListResponseOptionsUC listResponseOptionsUC;
    private FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC;
    private FindCategoriesCatalogByIdUC findCategoriesCatalogByIdUC;
    private ListCategoriesCatalogsUC listCategoriesCatalogsUC;
    private FindQuestionByNameUC findQuestionByNameUC;
    private FindQuestionByIdUC findQuestionByIdUC;
    private ListQuestionsUC listQuestionsUC;

    Map<String, List<Object>> mapOfList;

    // Handle Create
    public ResponseOptionsController(CreateResponseOptionsUC createResponseOptionsUC, ListCategoriesCatalogsUC listCategoriesCatalogsUC, FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, ListQuestionsUC listQuestionsUC, FindQuestionByNameUC findQuestionByNameUC) {
        this.createResponseOptionsUC = createResponseOptionsUC;
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
        this.findCategoriesCatalogByNameUC = findCategoriesCatalogByNameUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
        this.listQuestionsUC = listQuestionsUC;
        this.findQuestionByNameUC = findQuestionByNameUC;
    }

    // Handle List
    public ResponseOptionsController(ListResponseOptionsUC listResponseOptionsUC) {
        this.listResponseOptionsUC = listResponseOptionsUC;
    }
     
    // Handle Seach
    public ResponseOptionsController(FindResponseOptionsByIdUC findResponseOptionsByIdUC) {
        this.findResponseOptionsByIdUC = findResponseOptionsByIdUC;
    }

    // Handle Update
    public ResponseOptionsController(UpdateResponseOptionsUC updateResponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, ListQuestionsUC listQuestionsUC, ListCategoriesCatalogsUC listCategoriesCatalogsUC, FindResponseOptionsByIdUC findResponseOptionsByIdUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, FindQuestionByIdUC findQuestionByIdUC, FindQuestionByNameUC findQuestionByNameUC, FindCategoriesCatalogByIdUC findCategoriesCatalogByIdUC, FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC ) {
        this.updateResponseOptionsUC = updateResponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.listQuestionsUC = listQuestionsUC;
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
        this.findResponseOptionsByIdUC = findResponseOptionsByIdUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
        this.findQuestionByIdUC = findQuestionByIdUC;
        this.findQuestionByNameUC = findQuestionByNameUC;
        this.findCategoriesCatalogByIdUC = findCategoriesCatalogByIdUC;
        this.findCategoriesCatalogByNameUC = findCategoriesCatalogByNameUC;
    }

    public ResponseOptionsController(DeleteResponseOptionsUC deleteResponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC) {
        this.deleteResponseOptionsUC = deleteResponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
    }


    public void createResponseOptions() {
        ResponseOptions responseOptions = new ResponseOptions();
        List<Object> listOfResponseOption = Arrays.asList(listResponseOptionsUC, findResponseOptionsByNameUC);
        List<Object> listOfCategory = Arrays.asList(listCategoriesCatalogsUC, findCategoriesCatalogByNameUC);
        List<Object> listOfQuestion = Arrays.asList(listQuestionsUC, findQuestionByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("ResponseOptions", listOfResponseOption);
        mapOfList.put("CategoryCatalog", listOfCategory);
        mapOfList.put("Question", listOfQuestion);
        this.createController = new CreateController(responseOptions, createResponseOptionsUC, mapOfList);
    }

    public void updateResponseOptions() {
        ResponseOptions responseOptions = new ResponseOptions();
        List<Object> listOfOptions = Arrays.asList(listResponseOptionsUC, findResponseOptionsByCategoryCatalogIdUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("ResponseOptions", listOfOptions);
        this.updateController = new UpdateController(responseOptions, updateResponseOptionsUC, listResponseOptionsUC, findResponseOptionsByCategoryCatalogIdUC, mapOfList);
    }

    public void deleteResponseOptions() {
        ResponseOptions responseOptions = new ResponseOptions();
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
        this.deleteController = new DeleteController(responseOptions, deleteResponseOptionsUC, listResponseOptionsUC, findResponseOptionsByNameUC);
    }

    public void listResponseOptions() {
        ResponseOptions responseOptions = new ResponseOptions();
        this.listController = new ListController(responseOptions, listResponseOptionsUC);
    }

    public void findResponseOptionsById() {
        ResponseOptions responseOptions = new ResponseOptions();
        this.searchController = new SearchController(responseOptions, findResponseOptionsByIdUC);
    }
}