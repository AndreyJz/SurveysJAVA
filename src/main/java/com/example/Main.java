package com.example;

import com.example.chapter.application.*;
import com.example.chapter.domain.service.ChapterService;
import com.example.chapter.infrastructure.controller.ChapterController;
import com.example.chapter.infrastructure.repository.ChapterRepository;
import com.example.login.infrastructure.controller.LoginController;
import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;
import com.example.survey.domain.service.SurveySercive;
import com.example.survey.infrastructure.repository.SurveyRepository;

public class Main {
    public static void main(String[] args) {
//        LoginController loginController = new LoginController();
        ChapterService cs = new ChapterRepository();
        SurveySercive ss = new SurveyRepository();
        UpdateChapterUC uc = new UpdateChapterUC(cs);
        ListChaptersUC lc = new ListChaptersUC(cs);
        ListSurveysUC ls = new ListSurveysUC(ss);
        FindChapterByNameUC fc = new FindChapterByNameUC(cs);
        FindSurveyByIdUC fsi = new FindSurveyByIdUC(ss);
        FindSurveyByNameUC fs = new FindSurveyByNameUC(ss);
        ChapterController c = new ChapterController(uc, lc, ls, fc, fsi, fs);
        c.updateChapter();
    }
}