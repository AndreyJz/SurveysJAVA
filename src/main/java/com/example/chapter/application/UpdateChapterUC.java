package com.example.chapter.application;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

public class UpdateChapterUC {
    private final ChapterService chapterService;

    public UpdateChapterUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void update(Chapter chapter) { this.chapterService.updateChapter(chapter); }
}
