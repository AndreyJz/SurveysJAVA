package com.example.chapter.infrastructure.controller;

import com.example.chapter.application.CreateChapterUC;
import com.example.chapter.application.FindChapterByNameUC;
import com.example.chapter.application.ListChaptersUC;
import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;
import com.example.infrastructure.controller.CreateController;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;

public class ChapterController {
    private CreateController createController;
    private FindSurveyByNameUC findSurveyByNameUC;
    private ListSurveysUC listSurveysUC;
    private CreateChapterUC createChapterUC;

    public ChapterController(CreateChapterUC createChapterUC, ListSurveysUC listSurveysUC, FindSurveyByNameUC findSurveyByNameUC) {
        this.createChapterUC = createChapterUC;
        this.listSurveysUC = listSurveysUC;
        this.findSurveyByNameUC = findSurveyByNameUC;

    }

    public void createChapter () {
        Chapter chapter = new Chapter();
        this.createController = new CreateController(chapter, createChapterUC, listSurveysUC, findSurveyByNameUC);
    }
}
