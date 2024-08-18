package com.example.chapter.application;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

import java.util.Optional;

public class FindChapterByNameUC {
    private final ChapterService chapterService;

    public FindChapterByNameUC(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    public Optional<Chapter> find(String name) { return this.chapterService.findChaptersByName(name); }
}
