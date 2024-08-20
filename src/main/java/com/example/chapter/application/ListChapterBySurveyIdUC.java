package com.example.chapter.application;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

import java.util.List;

public class ListChapterBySurveyIdUC {
    private final ChapterService chapterService;

    public ListChapterBySurveyIdUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<Chapter> list(int id) { return this.chapterService.findChaptersBySurveyID(id); }
}
