package com.example.modules.subresponseoptions.infrastructure.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.UI.infrastructure.controller.CreateController;
import com.example.UI.infrastructure.controller.DeleteController;
import com.example.UI.infrastructure.controller.ListController;
import com.example.UI.infrastructure.controller.SearchController;
import com.example.UI.infrastructure.controller.UpdateController;
import com.example.modules.responseoptions.application.FindResponseOptionsByIdUC;
import com.example.modules.responseoptions.application.FindResponseOptionsByNameUC;
import com.example.modules.responseoptions.application.ListResponseOptionsUC;
import com.example.modules.subresponseoptions.application.CreateSubresponseOptionsUC;
import com.example.modules.subresponseoptions.application.DeleteSubresponseOptionsUC;
import com.example.modules.subresponseoptions.application.FindSubresponseOptionsByIdUC;
import com.example.modules.subresponseoptions.application.FindSubresponseOptionsBySubresponseTextUC;
import com.example.modules.subresponseoptions.application.ListSubresponseOptionsUC;
import com.example.modules.subresponseoptions.application.UpdateSubresponseOptionsUC;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

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
    private FindResponseOptionsByNameUC findResponseOptionsByNameUC;
    private ListResponseOptionsUC listResponseOptionsUC;
    private FindResponseOptionsByIdUC findResponseOptionsByIdUC;
    private FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC;
    Map<String, List<Object>> mapOfList;
    
    // Handle Create
    public SubresponseOptionsController(CreateSubresponseOptionsUC createSubresponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC, ListSubresponseOptionsUC listSubresponseOptionsUC, FindSubresponseOptionsByIdUC findSubresponseOptionsByIdUC) {
        this.createSubresponseOptionsUC = createSubresponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
        this.findSubresponseOptionsByIdUC = findSubresponseOptionsByIdUC;
    }
    
    // Handle List
    public SubresponseOptionsController(ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }
    
    // Handle Search
    public SubresponseOptionsController(FindSubresponseOptionsByIdUC findSubresponseOptionsByIdUC) {
        this.findSubresponseOptionsByIdUC = findSubresponseOptionsByIdUC;
    }

    // Handle Update
    public SubresponseOptionsController(UpdateSubresponseOptionsUC updateSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC, ListResponseOptionsUC listResponseOptionsUC, FindSubresponseOptionsByIdUC findSubresponseOptionsByIdUC, FindSubresponseOptionsBySubresponseTextUC findSubresponseOptionsBySubresponseTextUC, FindResponseOptionsByIdUC findResponseOptionsByIdUC, FindResponseOptionsByNameUC findResponseOptionsByNameUC) {
        this.updateSubresponseOptionsUC = updateSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
        this.listResponseOptionsUC = listResponseOptionsUC;
        this.findSubresponseOptionsByIdUC = findSubresponseOptionsByIdUC;
        this.findSubresponseOptionsBySubresponseTextUC = findSubresponseOptionsBySubresponseTextUC;
        this.findResponseOptionsByIdUC = findResponseOptionsByIdUC;
        this.findResponseOptionsByNameUC = findResponseOptionsByNameUC;
    }
    public SubresponseOptionsController(CreateSubresponseOptionsUC createSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.createSubresponseOptionsUC = createSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    public SubresponseOptionsController(DeleteSubresponseOptionsUC deleteSubresponseOptionsUC, ListSubresponseOptionsUC listSubresponseOptionsUC) {
        this.deleteSubresponseOptionsUC = deleteSubresponseOptionsUC;
        this.listSubresponseOptionsUC = listSubresponseOptionsUC;
    }

    

    public void createSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        List<Object> listOfResponseOption = Arrays.asList(listResponseOptionsUC, findResponseOptionsByNameUC);
        mapOfList = new LinkedHashMap<>();
        mapOfList.put("ResponseOptions", listOfResponseOption);
        this.createController = new CreateController(subresponseOptions, createSubresponseOptionsUC, mapOfList);
    }

    public void updateSubresponseOptions() {
        SubresponseOptions subresponseOptions = new SubresponseOptions();
        List<Object> listOfSubResponseOption = Arrays.asList(listSubresponseOptionsUC, findSubresponseOptionsBySubresponseTextUC);
        List<Object> listOfResponseOption = Arrays.asList(listResponseOptionsUC, findResponseOptionsByNameUC);
        mapOfList = new LinkedHashMap<>();
        mapOfList.put("ResponseOptions", listOfResponseOption);
        mapOfList.put("SubResponseOptions", listOfSubResponseOption);
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