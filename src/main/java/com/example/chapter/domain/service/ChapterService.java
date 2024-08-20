package com.example.chapter.domain.service;

import com.example.chapter.domain.entity.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
    public void createChapter(Chapter chapter);
    public void updateChapter(Chapter chapter);
    public void deleteChapter(int id);
    public Optional<Chapter> findChapterById(int id);
    public List<Chapter> listChapters();
    public Optional<Chapter> findChaptersByName(String name);
    public List<Chapter> listChaptersBySurveyID(int id);
}
