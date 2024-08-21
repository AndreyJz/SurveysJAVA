package com.example.modules.question.infrastructure.controller;

import com.example.modules.question.application.*;
import com.example.modules.chapter.application.*;
import com.example.UI.infrastructure.controller.CreateController;
import com.example.modules.question.domain.entity.Question;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionController {
    private CreateController createController;
    private CreateQuestionUC createQuestionUC;
    private ListChaptersUC listChaptersUC;
    private FindChapterByNameUC findChapterByNameUC;
    private ListQuestionsUC listQuestionsUC;
    private FindQuestionByNameUC findQuestionByNameUC;
    Map<String, List<Object>> mapOfList;

    public QuestionController(CreateQuestionUC createQuestionUC, ListChaptersUC listChaptersUC, FindChapterByNameUC findChapterByNameUC) {
        this.createQuestionUC = createQuestionUC;
        this.listChaptersUC = listChaptersUC;
        this.findChapterByNameUC = findChapterByNameUC;
    }

    public void createQuestion() {
        Question question = new Question();
        List<Object> listOfChapters = Arrays.asList(listChaptersUC,findChapterByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("Chapter", listOfChapters);
        this.createController = new CreateController(question, createQuestionUC, mapOfList);
    }
}
