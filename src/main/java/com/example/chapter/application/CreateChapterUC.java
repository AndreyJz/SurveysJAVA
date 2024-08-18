package com.example.chapter.application;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

public class CreateChapterUC {
    private final ChapterService chapterService;

    public CreateChapterUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void create(Chapter chapter) { this.chapterService.createChapter(chapter); }
}
