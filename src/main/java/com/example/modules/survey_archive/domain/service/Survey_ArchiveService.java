package com.example.modules.survey_archive.domain.service;

import com.example.modules.survey_archive.domain.entity.Survey_Archive;

import java.util.List;
import java.util.Optional;

public interface Survey_ArchiveService {
    public void createSurvey_Archive(Survey_Archive survey_archive);
    public List<Survey_Archive> listSurveys_Archive();
    public Optional<Survey_Archive> findSurvey_Archive(int id);
}
