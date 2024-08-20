package com.example.categoriescatalog.application;

import com.example.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.categoriescatalog.domain.service.CategoriesCatalogService;

import java.util.Optional;

public class FindCategoriesCatalogByNameUC {
    private final CategoriesCatalogService categoriesCatalogService;

    public FindCategoriesCatalogByNameUC(CategoriesCatalogService categoriesCatalogService) {
        this.categoriesCatalogService = categoriesCatalogService;
    }

    public Optional<CategoriesCatalog> find(String name) { 
        return this.categoriesCatalogService.findCategoriesCatalogsByName(name); 
    }
}