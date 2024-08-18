package com.example.question.infrastructure.controller;

import com.example.question.application.CreateQuestionUC;
import com.example.question.application.FindQuestionByNameUC;
import com.example.question.application.ListQuestionsUC;
import com.example.question.domain.entity.Question;
import com.example.infrastructure.controller.CreateController;

public class QuestionController {
    private CreateController createController;
    private CreateQuestionUC createQuestionUC;
    private ListQuestionsUC listQuestionsUC;
    private FindQuestionByNameUC findQuestionByNameUC;

    public void QuestionController(CreateQuestionUC createQuestionUC, ListQuestionsUC listQuestionsUC, FindQuestionByNameUC findQuestionByNameUC) {
        this.createQuestionUC = createQuestionUC;
        this.listQuestionsUC = listQuestionsUC;
        this.findQuestionByNameUC = findQuestionByNameUC;
        Question question = new Question();

        this.createController = new CreateController(question, createQuestionUC, listQuestionsUC, findQuestionByNameUC);
    }
}
