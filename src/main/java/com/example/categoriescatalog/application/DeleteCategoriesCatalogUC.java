package com.example.categoriescatalog.application;

import com.example.categoriescatalog.domain.service.CategoriesCatalogService;

public class DeleteCategoriesCatalogUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public DeleteCategoriesCatalogUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public void delete(int id) { 
        this.categoriesCatalogService.deleteCategoriesCatalog(id); 
    }
}