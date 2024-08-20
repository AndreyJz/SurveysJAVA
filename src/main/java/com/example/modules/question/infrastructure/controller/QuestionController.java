package com.example.modules.question.infrastructure.controller;

import com.example.modules.question.application.CreateQuestionUC;
import com.example.modules.question.application.FindQuestionByNameUC;
import com.example.modules.question.application.ListQuestionsUC;
import com.example.UI.infrastructure.controller.CreateController;

public class QuestionController {
    private CreateController createController;
    private CreateQuestionUC createQuestionUC;
    private ListQuestionsUC listQuestionsUC;
    private FindQuestionByNameUC findQuestionByNameUC;

//    public void QuestionController(CreateQuestionUC createQuestionUC, ListQuestionsUC listQuestionsUC, FindQuestionByNameUC findQuestionByNameUC) {
//        this.createQuestionUC = createQuestionUC;
//        this.listQuestionsUC = listQuestionsUC;
//        this.findQuestionByNameUC = findQuestionByNameUC;
//        Question question = new Question();
//
//        this.createController = new CreateController(question, createQuestionUC, listQuestionsUC, findQuestionByNameUC);
//    }
}
