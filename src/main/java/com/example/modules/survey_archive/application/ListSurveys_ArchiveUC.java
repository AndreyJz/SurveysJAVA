package com.example.modules.survey_archive.application;

import com.example.modules.survey_archive.domain.entity.Survey_Archive;
import com.example.modules.survey_archive.domain.service.Survey_ArchiveService;

import java.util.List;

public class ListSurveys_ArchiveUC {
    private final Survey_ArchiveService survey_archive;

    public ListSurveys_ArchiveUC(Survey_ArchiveService surveyArchive) {
        survey_archive = surveyArchive;
    }

    public List<Survey_Archive> list() {return this.survey_archive.listSurveys_Archive(); }
}
