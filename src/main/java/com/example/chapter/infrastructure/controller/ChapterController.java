package com.example.chapter.infrastructure.controller;

import com.example.chapter.application.*;
import com.example.chapter.domain.entity.Chapter;
import com.example.infrastructure.controller.CreateController;
import com.example.infrastructure.controller.DeleteController;
import com.example.infrastructure.controller.ListController;
import com.example.infrastructure.controller.UpdateController;
import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;

public class ChapterController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private FindChapterByNameUC findChapterByNameUC;
    private ListChaptersUC listChaptersUC;
    private ListSurveysUC listSurveysUC;
    private CreateChapterUC createChapterUC;
    private UpdateChapterUC updateChapterUC;
    private DeleteChapterUC deleteChapterUC;

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

    public ChapterController(DeleteChapterUC deleteChapterUC, ListChaptersUC listChaptersUC, FindChapterByNameUC findChapterByNameUC) {
        this.deleteChapterUC = deleteChapterUC;
        this.listChaptersUC = listChaptersUC;
        this.findChapterByNameUC = findChapterByNameUC;
    }

    public ChapterController(ListChaptersUC listChaptersUC) {
        this.listChaptersUC = listChaptersUC;
    }

    public void createChapter() {
        Chapter chapter = new Chapter();
        this.createController = new CreateController(chapter, createChapterUC, listSurveysUC, findSurveyByNameUC);
    }

    public void updateChapter() {
        Chapter chapter = new Chapter();
        this.updateController = new UpdateController(chapter, updateChapterUC, listChaptersUC, listSurveysUC, findChapterByNameUC, findSurveyByIdUC, findSurveyByNameUC);
    }

    public void deleteChapter() {
        Chapter chapter = new Chapter();
        this.deleteController = new DeleteController(chapter, deleteChapterUC, listChaptersUC, findChapterByNameUC);
    }

    public void listChapters() {
        Chapter chapter = new Chapter();
        this.listController = new ListController(chapter,listChaptersUC);
    }

}
