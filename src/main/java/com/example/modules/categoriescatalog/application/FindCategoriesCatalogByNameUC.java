package com.example.modules.categoriescatalog.application;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

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