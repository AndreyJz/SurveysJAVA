package com.example.categoriescatalog.application;

import com.example.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.categoriescatalog.domain.service.CategoriesCatalogService;

import java.util.List;

public class ListCategoriesCatalogsUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public ListCategoriesCatalogsUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public List<CategoriesCatalog> list() { 
        return this.categoriesCatalogService.listCategoriesCatalogs(); 
    }
}