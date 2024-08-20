package com.example.modules.chapter.application;

import com.example.modules.chapter.domain.entity.Chapter;
import com.example.modules.chapter.domain.service.ChapterService;

import java.util.Optional;

public class FindChapterByIdUC {
    private final ChapterService chapterService;

    public FindChapterByIdUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public Optional<Chapter> find(int id) { return this.chapterService.findChapterById(id); }
}
