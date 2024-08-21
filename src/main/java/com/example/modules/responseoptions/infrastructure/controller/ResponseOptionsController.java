package com.example.modules.responseoptions.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
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
    private FindResponseOptionsByCategoryCatalogIdUC findResponseOptionsByCategoryCatalogIdUC;
    private ListResponseOptionsUC listResponseOptionsUC;
    Map<String, List<Object>> mapOfList;

    public ResponseOptionsController(CreateResponseOptionsUC createResponseOptionsUC, FindResponseOptionsByCategoryCatalogIdUC findResponseOptionsByCategoryCatalogIdUC) {
        this.createResponseOptionsUC = createResponseOptionsUC;
        this.findResponseOptionsByCategoryCatalogIdUC = findResponseOptionsByCategoryCatalogIdUC;
    }

    public ResponseOptionsController(UpdateResponseOptionsUC updateResponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByCategoryCatalogIdUC findResponseOptionsByCategoryCatalogIdUC) {
        this.updateResponseOptionsUC = updateResponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByCategoryCatalogIdUC = findResponseOptionsByCategoryCatalogIdUC;
    }

    public ResponseOptionsController(DeleteResponseOptionsUC deleteResponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByCategoryCatalogIdUC findResponseOptionsByCategoryCatalogIdUC) {
        this.deleteResponseOptionsUC = deleteResponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByCategoryCatalogIdUC = findResponseOptionsByCategoryCatalogIdUC;
    }

    public ResponseOptionsController(ListResponseOptionsUC listResponseOptionsUC) {
        this.listResponseOptionsUC = listResponseOptionsUC;
    }

    public ResponseOptionsController(FindResponseOptionsByIdUC findResponseOptionsByIdUC) {
        this.findResponseOptionsByIdUC = findResponseOptionsByIdUC;
    }

    public void createResponseOptions() {
        ResponseOptions responseOptions = new ResponseOptions();
        List<Object> listOfOptions = Arrays.asList(listResponseOptionsUC, findResponseOptionsByCategoryCatalogIdUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("ResponseOptions", listOfOptions);
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
        this.deleteController = new DeleteController(responseOptions, deleteResponseOptionsUC, listResponseOptionsUC, findResponseOptionsByCategoryCatalogIdUC);
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