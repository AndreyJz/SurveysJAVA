package com.example.modules.chapter.application;

import com.example.modules.chapter.domain.entity.Chapter;
import com.example.modules.chapter.domain.service.ChapterService;

import java.util.List;

public class ListChaptersUC {
    private final ChapterService chapterService;

    public ListChaptersUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<Chapter> list() { return this.chapterService.listChapters(); }
}
