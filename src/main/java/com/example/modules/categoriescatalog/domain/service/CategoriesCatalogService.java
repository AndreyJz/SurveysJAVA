package com.example.modules.categoriescatalog.domain.service;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;

import java.util.List;
import java.util.Optional;

public interface CategoriesCatalogService {
    public void createCategoriesCatalog(CategoriesCatalog categoriesCatalog);
    public void updateCategoriesCatalog(CategoriesCatalog categoriesCatalog);
    public void deleteCategoriesCatalog(int id);
    public Optional<CategoriesCatalog> findCategoriesCatalogById(int id);
    public List<CategoriesCatalog> listCategoriesCatalogs();
    public Optional<CategoriesCatalog> findCategoriesCatalogsByName(String name);
}