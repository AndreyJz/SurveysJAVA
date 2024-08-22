package com.example.modules.subresponseoptions.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
import com.example.modules.subresponseoptions.application.*;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.*;

public class SubresponseOptionsController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private CreateSubresponseOptionsUC createSubresponseOptionsUC;
    private UpdateSubresponseOptionsUC updateSubresponseOptionsUC;
    private DeleteSubresponseOptionsUC deleteSubresponseOptionsUC;
    private FindSubresponseOptionsByIdUC findSubresponseOptionsByIdUC;
    private ListSubresponseOptionsUC listSubresponseOptionsUC;

    Map<String, List<Object>> mapOfList;

    public SubresponseOptionsController(CreateSubresponseOptionsUC createSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.createSubresponseOptionsUC = createSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    public SubresponseOptionsController(UpdateSubresponseOptionsUC updateSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.updateSubresponseOptionsUC = updateSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    public SubresponseOptionsController(DeleteSubresponseOptionsUC deleteSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.deleteSubresponseOptionsUC = deleteSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    public SubresponseOptionsController(ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    public SubresponseOptionsController(FindSubresponseOptionsByIdUC findSubresponseOptionsByIdUC) {
        this.findSubresponseOptionsByIdUC = findSubresponseOptionsByIdUC;
    }

    public void createSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        List<Object> listOfSubresponseOption = Arrays.asList(listSubresponseOptionsUC);
        mapOfList = new LinkedHashMap<>();
        mapOfList.put("SubresponseOptions", listOfSubresponseOption);
        this.createController = new CreateController(subresponseOptions, createSubresponseOptionsUC, mapOfList);
    }

    public void updateSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        List<Object> listOfSubresponseOption = Arrays.asList(listSubresponseOptionsUC);
        mapOfList = new LinkedHashMap<>();
        mapOfList.put("SubresponseOptions", listOfSubresponseOption);
        this.updateController = new UpdateController(subresponseOptions, updateSubresponseOptionsUC, listSubresponseOptionsUC, findSubresponseOptionsByIdUC, mapOfList);
    }

    public void deleteSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        this.deleteController = new DeleteController(subresponseOptions, deleteSubresponseOptionsUC, listSubresponseOptionsUC, findSubresponseOptionsByIdUC);
    }

    public void listSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        this.listController = new ListController(subresponseOptions, listSubresponseOptionsUC);
    }

    public void findSubresponseOptionsById() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        this.searchController = new SearchController(subresponseOptions, findSubresponseOptionsByIdUC);
    }
}