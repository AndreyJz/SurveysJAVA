package com.example.survey.application;

import com.example.survey.domain.entity.Survey;
import com.example.survey.domain.service.SurveySercive;

import java.util.List;
import java.util.Optional;

public class ListSurveysUC {
    private final SurveySercive surveySercive;

    public ListSurveysUC(SurveySercive surveySercive) {
        this.surveySercive = surveySercive;
    }

    public List<Survey> list() {
        return this.surveySercive.listSurveys();
    }
}
