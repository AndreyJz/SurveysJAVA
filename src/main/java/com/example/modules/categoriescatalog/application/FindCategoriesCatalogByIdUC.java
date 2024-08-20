package com.example.modules.categoriescatalog.application;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

import java.util.Optional;

public class FindCategoriesCatalogByIdUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public FindCategoriesCatalogByIdUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public Optional<CategoriesCatalog> find(int id) {
        return this.categoriesCatalogService.findCategoriesCatalogById(id); 
    }
}