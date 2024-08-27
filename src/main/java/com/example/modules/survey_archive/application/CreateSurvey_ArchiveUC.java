package com.example.modules.survey_archive.application;

import com.example.modules.survey_archive.domain.entity.Survey_Archive;
import com.example.modules.survey_archive.domain.service.Survey_ArchiveService;

public class CreateSurvey_ArchiveUC {
    private final Survey_ArchiveService survey_archive;

    public CreateSurvey_ArchiveUC(Survey_ArchiveService surveyArchive) {
        survey_archive = surveyArchive;
    }

    public void create(Survey_Archive survey_archive) { this.survey_archive.createSurvey_Archive(survey_archive); }
}
