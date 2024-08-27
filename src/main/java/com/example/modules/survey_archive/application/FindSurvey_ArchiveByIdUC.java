package com.example.modules.survey_archive.application;

import com.example.modules.survey_archive.domain.entity.Survey_Archive;
import com.example.modules.survey_archive.domain.service.Survey_ArchiveService;

import java.util.Optional;

public class FindSurvey_ArchiveByIdUC {
    private final Survey_ArchiveService survey_archive;

    public FindSurvey_ArchiveByIdUC(Survey_ArchiveService surveyArchive) {
        survey_archive = surveyArchive;
    }

    public Optional<Survey_Archive> find(int id) {return this.survey_archive.findSurvey_Archive(id); }
}
