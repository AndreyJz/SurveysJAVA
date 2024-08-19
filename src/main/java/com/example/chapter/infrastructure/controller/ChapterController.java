package com.example.chapter.infrastructure.controller;

import com.example.chapter.application.*;
import com.example.chapter.domain.entity.Chapter;
import com.example.infrastructure.controller.*;
import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;

import java.util.*;

public class ChapterController {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private FindChapterByNameUC findChapterByNameUC;
    private FindChapterByIdUC findChapterByIdUC;
    private ListChaptersUC listChaptersUC;
    private ListSurveysUC listSurveysUC;
    private CreateChapterUC createChapterUC;
    private UpdateChapterUC updateChapterUC;
    private DeleteChapterUC deleteChapterUC;
    Map<String, List<Object>> mapOfList;

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

    public ChapterController(FindChapterByIdUC findChapterByIdUC) {
        this.findChapterByIdUC = findChapterByIdUC;
    }

    public void createChapter() {
        Chapter chapter = new Chapter();
        List<Object> listOfSurvey = Arrays.asList(listSurveysUC,findSurveyByNameUC);
        List<Object> listOfChapters = Arrays.asList(listChaptersUC,findChapterByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("Survey", listOfSurvey);
        mapOfList.put("Chapter", listOfChapters);
        this.createController = new CreateController(chapter, createChapterUC, mapOfList);
    }

    public void updateChapter() {
        Chapter chapter = new Chapter();
        List<Object> listOfSurvey = Arrays.asList(listSurveysUC,findSurveyByIdUC,findSurveyByNameUC);
        List<Object> listOfChapters = Arrays.asList(listChaptersUC,findChapterByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("Survey", listOfSurvey);
        mapOfList.put("Chapter", listOfChapters);
        this.updateController = new UpdateController(chapter, updateChapterUC, listChaptersUC, findChapterByNameUC, mapOfList);
    }

    public void deleteChapter() {
        Chapter chapter = new Chapter();
        this.deleteController = new DeleteController(chapter, deleteChapterUC, listChaptersUC, findChapterByNameUC);
    }

    public void listChapters() {
        Chapter chapter = new Chapter();
        this.listController = new ListController(chapter, listChaptersUC);
    }

    public void findChapterById() {
        Chapter chapter = new Chapter();
        this.searchController = new SearchController(chapter, findChapterByIdUC);
    }
}
