package com.example.modules.responseoptions.domain.service;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;

import java.util.List;
import java.util.Optional;

public interface ResponseOptionsService {
    public void createResponseOptions(ResponseOptions responseOptions);
    public void updateResponseOptions(ResponseOptions responseOptions);
    public void deleteResponseOptions(int id);
    public Optional<ResponseOptions> findResponseOptionsById(int id);
    public List<ResponseOptions> listResponseOptions();
    public List<ResponseOptions> findResponseOptionsByCategoryCatalogId(int id);
}