package com.example.modules.categoriescatalog.application;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

public class UpdateCategoriesCatalogUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public UpdateCategoriesCatalogUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public void update(CategoriesCatalog categoriesCatalog) { 
        this.categoriesCatalogService.updateCategoriesCatalog(categoriesCatalog); 
    }
}
