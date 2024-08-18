package com.example.chapter.application;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

import java.util.List;
import java.util.Optional;

public class ListChaptersUC {
    private final ChapterService chapterService;

    public ListChaptersUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public List<Chapter> list() { return this.chapterService.listChapters(); }
}
