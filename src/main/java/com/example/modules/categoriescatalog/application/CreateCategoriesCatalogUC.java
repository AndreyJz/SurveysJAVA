package com.example.modules.categoriescatalog.application;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

public class CreateCategoriesCatalogUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public CreateCategoriesCatalogUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public void create(CategoriesCatalog categoriesCatalog) {
        this.categoriesCatalogService.createCategoriesCatalog(categoriesCatalog);
    }
}
