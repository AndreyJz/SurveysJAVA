package com.example.chapter.application;

import com.example.chapter.domain.service.ChapterService;

public class DeleteChapterUC {
    private final ChapterService chapterService;

    public DeleteChapterUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public void delete(int id) { this.chapterService.deleteChapter(id); }
}
