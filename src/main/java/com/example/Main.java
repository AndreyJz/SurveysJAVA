package com.example;

import com.example.chapter.application.CreateChapterUC;
import com.example.chapter.application.FindChapterByNameUC;
import com.example.chapter.application.ListChaptersUC;
import com.example.chapter.domain.service.ChapterService;
import com.example.chapter.infrastructure.controller.ChapterController;
import com.example.chapter.infrastructure.repository.ChapterRepository;
import com.example.login.infrastructure.controller.LoginController;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;
import com.example.survey.domain.service.SurveySercive;
import com.example.survey.infrastructure.repository.SurveyRepository;

public class Main {
    public static void main(String[] args) {
//        LoginController loginController = new LoginController();
        ChapterService cs = new ChapterRepository();
        SurveySercive ss = new SurveyRepository();
        CreateChapterUC cc = new CreateChapterUC(cs);
        ListSurveysUC lc = new ListSurveysUC(ss);
        FindSurveyByNameUC fc = new FindSurveyByNameUC(ss);
        ChapterController c = new ChapterController(cc,lc,fc);
        c.createChapter();
    }
}