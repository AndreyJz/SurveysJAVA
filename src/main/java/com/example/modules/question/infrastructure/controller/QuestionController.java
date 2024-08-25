package com.example.modules.question.infrastructure.controller;

import com.example.modules.question.application.*;
import com.example.modules.chapter.application.*;
import com.example.UI.infrastructure.controller.CreateController;
import com.example.UI.infrastructure.controller.DeleteController;
import com.example.UI.infrastructure.controller.ListController;
import com.example.UI.infrastructure.controller.SearchController;
import com.example.UI.infrastructure.controller.UpdateController;
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
    private ListController listController;
    private FindChapterByIdUC findChapterByIdUC;
    Map<String, List<Object>> mapOfList;
    private SearchController searchController;
    private FindQuestionByIdUC findQuestionByIdUC;
    private UpdateQuestionUC updateQuestionUC;
    private UpdateController updateController;
    private DeleteQuestionUC deleteQuestionUC;
    private DeleteController deleteController;

    // Handle Create
    public QuestionController(CreateQuestionUC createQuestionUC, ListChaptersUC listChaptersUC, FindChapterByNameUC findChapterByNameUC) {
        this.createQuestionUC = createQuestionUC;
        this.listChaptersUC = listChaptersUC;
        this.findChapterByNameUC = findChapterByNameUC;
    }

    //Handle List
    public QuestionController(ListQuestionsUC listQuestionsUC) {
        this.listQuestionsUC = listQuestionsUC;
    }
    
    // Handle Seach
    public QuestionController(FindQuestionByIdUC findQuestionByIdUC) {
        this.findQuestionByIdUC = findQuestionByIdUC;
    }

    // Handle Update
    public QuestionController(UpdateQuestionUC updateQuestionUC, ListQuestionsUC listQuestionsUC, ListChaptersUC listChaptersUC, FindQuestionByNameUC findQuestionByNameUC, FindChapterByIdUC findChapterByIdUC, FindChapterByNameUC findChapterByNameUC) {
        this.updateQuestionUC = updateQuestionUC;
        this.listQuestionsUC = listQuestionsUC;
        this.listChaptersUC = listChaptersUC;
        this.findQuestionByNameUC = findQuestionByNameUC;
        this.findChapterByIdUC = findChapterByIdUC;
        this.findChapterByNameUC = findChapterByNameUC;
    }

    // Handle Delete
    public QuestionController(DeleteQuestionUC deleteQuestionUC, ListQuestionsUC listQuestionsUC, FindQuestionByNameUC findQuestionByNameUC) {
        this.deleteQuestionUC = deleteQuestionUC;
        this.listQuestionsUC = listQuestionsUC;
        this.findQuestionByNameUC = findQuestionByNameUC;
    }

    public void createQuestion() {
        Question question = new Question();
        List<Object> listOfChapters = Arrays.asList(listChaptersUC,findChapterByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("Chapter", listOfChapters);
        this.createController = new CreateController(question, createQuestionUC, mapOfList);
    }

    public void updateQuestion() {
        Question question = new Question();
        List<Object> listOfChapters = Arrays.asList(listChaptersUC, findChapterByIdUC, findChapterByNameUC);
        List<Object> listOfQuestions = Arrays.asList(listQuestionsUC, findQuestionByNameUC);
        mapOfList = new LinkedHashMap<>();

        mapOfList.put("Chapter", listOfChapters);
        mapOfList.put("Question", listOfQuestions);
        this.updateController = new UpdateController(question, updateQuestionUC, listOfChapters, listOfQuestions, mapOfList);
    }

    public void deleteQuestion() {
        Question question = new Question();
        this.deleteController = new DeleteController(question, deleteQuestionUC, listQuestionsUC, findQuestionByNameUC);

    }
    
    public void listQuestions() {
        Question question = new Question();
        this.listController = new ListController(question, listQuestionsUC);
    }

    public void searchQuestions() {
        Question question = new Question();
        this.searchController = new SearchController(question, findQuestionByIdUC);
    }
}
