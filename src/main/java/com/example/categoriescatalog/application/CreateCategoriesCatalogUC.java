package com.example.categoriescatalog.application;

import com.example.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.categoriescatalog.domain.service.CategoriesCatalogService;

public class CreateCategoriesCatalogUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public CreateCategoriesCatalogUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public void create(CategoriesCatalog categoriesCatalog) {
        this.categoriesCatalogService.createCategoriesCatalog(categoriesCatalog);
    }
}
