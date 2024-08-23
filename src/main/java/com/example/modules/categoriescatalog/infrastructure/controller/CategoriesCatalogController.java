package com.example.modules.categoriescatalog.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.application.*;

import java.util.*;

public class CategoriesCatalogController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private FindCategoriesCatalogByIdUC findCategoriesCatalogByIdUC;
    private FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC;
    private ListCategoriesCatalogsUC listCategoriesCatalogsUC;
    private CreateCategoriesCatalogUC createCategoriesCatalogUC;
    private UpdateCategoriesCatalogUC updateCategoriesCatalogUC;
    private DeleteCategoriesCatalogUC deleteCategoriesCatalogUC;
    Map<String, List<Object>> mapOfList;

    // Handle Create
    public CategoriesCatalogController(CreateCategoriesCatalogUC createCategoriesCatalogUC, ListCategoriesCatalogsUC listCategoriesCatalogsUC, FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC) {
        this.createCategoriesCatalogUC = createCategoriesCatalogUC;
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
        this.findCategoriesCatalogByNameUC = findCategoriesCatalogByNameUC;
    }

    // Handle List
    public CategoriesCatalogController(ListCategoriesCatalogsUC listCategoriesCatalogsUC) {
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
    }

    // Handle Search
    public CategoriesCatalogController(FindCategoriesCatalogByIdUC findCategoriesCatalogByIdUC) {
        this.findCategoriesCatalogByIdUC = findCategoriesCatalogByIdUC;
    }

    // Handle Update
    public CategoriesCatalogController(UpdateCategoriesCatalogUC updateCategoriesCatalogUC, ListCategoriesCatalogsUC listCategoriesCatalogsUC, FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC, FindCategoriesCatalogByIdUC findCategoriesCatalogByIdUC) {
        this.updateCategoriesCatalogUC = updateCategoriesCatalogUC;
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
        this.findCategoriesCatalogByNameUC = findCategoriesCatalogByNameUC;
        this.findCategoriesCatalogByIdUC = findCategoriesCatalogByIdUC;
    }

    public CategoriesCatalogController(DeleteCategoriesCatalogUC deleteCategoriesCatalogUC, ListCategoriesCatalogsUC listCategoriesCatalogsUC, FindCategoriesCatalogByNameUC findCategoriesCatalogByNameUC) {
        this.deleteCategoriesCatalogUC = deleteCategoriesCatalogUC;
        this.listCategoriesCatalogsUC = listCategoriesCatalogsUC;
        this.findCategoriesCatalogByNameUC = findCategoriesCatalogByNameUC;
    }
    


    public void createCategoriesCatalog() {
        CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
        List<Object> listOfCategoriesCatalogs = Arrays.asList(listCategoriesCatalogsUC, findCategoriesCatalogByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("CategoriesCatalog", listOfCategoriesCatalogs);
        this.createController = new CreateController(categoriesCatalog, createCategoriesCatalogUC, mapOfList);
    }

    public void updateCategoriesCatalog() {
        CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
        List<Object> listOfCategoriesCatalogs = Arrays.asList(listCategoriesCatalogsUC, findCategoriesCatalogByIdUC, findCategoriesCatalogByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("CategoriesCatalog", listOfCategoriesCatalogs);
        this.updateController = new UpdateController(categoriesCatalog, updateCategoriesCatalogUC, listCategoriesCatalogsUC, findCategoriesCatalogByNameUC, mapOfList);
    }

    public void deleteCategoriesCatalog() {
        CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
        this.deleteController = new DeleteController(categoriesCatalog, deleteCategoriesCatalogUC, listCategoriesCatalogsUC, findCategoriesCatalogByNameUC);
    }

    public void listCategoriesCatalogs() {
        CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
        this.listController = new ListController(categoriesCatalog, listCategoriesCatalogsUC);
    }

    public void findCategoriesCatalogById() {
        CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
        this.searchController = new SearchController(categoriesCatalog, findCategoriesCatalogByIdUC);
    }
}
// :w