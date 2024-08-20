package com.example.modules.chapter.application;

import com.example.modules.chapter.domain.entity.Chapter;
import com.example.modules.chapter.domain.service.ChapterService;

public class CreateChapterUC {
    private final ChapterService chapterService;

    public CreateChapterUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void create(Chapter chapter) { this.chapterService.createChapter(chapter); }
}
