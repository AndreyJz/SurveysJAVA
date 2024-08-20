package com.example.modules.categoriescatalog.application;

import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

public class DeleteCategoriesCatalogUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public DeleteCategoriesCatalogUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public void delete(int id) { 
        this.categoriesCatalogService.deleteCategoriesCatalog(id); 
    }
}