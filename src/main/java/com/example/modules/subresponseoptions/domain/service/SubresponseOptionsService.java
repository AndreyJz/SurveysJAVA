package com.example.modules.subresponseoptions.domain.service;

import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;

import java.util.List;
import java.util.Optional;

public interface SubresponseOptionsService {
    void createSubresponseOptions(SubresponseOptions subresponseOptions);
    void updateSubresponseOptions(SubresponseOptions subresponseOptions);
    void deleteSubresponseOptions(int id);
    Optional<SubresponseOptions> findSubresponseOptionsById(int id);
    List<SubresponseOptions> listSubresponseOptions();
    List<SubresponseOptions> findSubresponseOptionsBySubresponseText(String text);
    List<SubresponseOptions> listSubresponseOptionsByResponseOptionsId(int id);
}
