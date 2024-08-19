package com.example.chapter.infrastructure.controller;

import com.example.chapter.application.*;
import com.example.chapter.domain.entity.Chapter;
import com.example.infrastructure.controller.CreateController;
import com.example.infrastructure.controller.UpdateController;
import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;

public class ChapterController {
    private CreateController createController;
    private UpdateController updateController;
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private FindChapterByNameUC findChapterByNameUC;
    private ListChaptersUC listChaptersUC;
    private ListSurveysUC listSurveysUC;
    private CreateChapterUC createChapterUC;
    private UpdateChapterUC updateChapterUC;

    public ChapterController(CreateChapterUC createChapterUC, ListSurveysUC listSurveysUC, FindSurveyByNameUC findSurveyByNameUC) {
        this.createChapterUC = createChapterUC;
        this.listSurveysUC = listSurveysUC;
        this.findSurveyByNameUC = findSurveyByNameUC;
    }

    public ChapterController(UpdateChapterUC updateChapterUC, ListChaptersUC listChaptersUC, ListSurveysUC listSurveysUC, FindChapterByNameUC findChapterByNameUC, FindSurveyByIdUC findSurveyByIdUC, FindSurveyByNameUC findSurveyByNameUC) {
        this.updateChapterUC = updateChapterUC;
        this.listChaptersUC = listChaptersUC;
        this.listSurveysUC = listSurveysUC;
        this.findChapterByNameUC = findChapterByNameUC;
        this.findSurveyByIdUC = findSurveyByIdUC;
        this.findSurveyByNameUC = findSurveyByNameUC;
    }

    public void createChapter() {
        Chapter chapter = new Chapter();
        this.createController = new CreateController(chapter, createChapterUC, listSurveysUC, findSurveyByNameUC);
    }

    public void updateChapter() {
        Chapter chapter = new Chapter();
        this.updateController = new UpdateController(chapter, updateChapterUC, listChaptersUC, listSurveysUC, findChapterByNameUC, findSurveyByIdUC, findSurveyByNameUC);
    }

}
