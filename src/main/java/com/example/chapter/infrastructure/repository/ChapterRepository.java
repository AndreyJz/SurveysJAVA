package com.example.chapter.infrastructure.repository;

import com.example.chapter.domain.entity.Chapter;
import com.example.chapter.domain.service.ChapterService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ChapterRepository implements ChapterService {
    private Connection connection;

    public ChapterRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            connection = DriverManager.getConnection(url, user, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createChapter(Chapter chapter) {
        String query = "INSERT INTO chapter (id, created_at, updated_at, chapter_number, chapter_title, survey_id) VALUES (?, NOW(), NULL, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, chapter.getId());
            ps.setString(2, chapter.getChapterNumber());
            ps.setString(3, chapter.getChapterTitle());
            ps.setInt(4, chapter.getSurveyId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chapter has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateChapter(Chapter chapter) {
        String query = "UPDATE chapter SET updated_at = NOW(), chapter_number = ?, chapter_title = ?, survey_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, chapter.getChapterNumber());
            ps.setString(2, chapter.getChapterTitle());
            ps.setInt(3, chapter.getSurveyId());
            ps.setInt(4, chapter.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chapter has been updated!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteChapter(int id) {
        String query = "DELETE FROM chapter WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Chapter has been deleted!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<Chapter> findChapterById(int id) {
        String query = "SELECT * FROM chapter WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setId(rs.getInt("id"));
                chapter.setCreatedAt(rs.getTimestamp("created_at"));
                chapter.setUpdatedAt(rs.getTimestamp("updated_at"));
                chapter.setChapterNumber(rs.getString("chapter_number"));
                chapter.setChapterTitle(rs.getString("chapter_title"));
                chapter.setSurveyId(rs.getInt("survey_id"));
                return Optional.of(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public List<Chapter> listChapters() {
        String query = "SELECT * FROM chapter";
        List<Chapter> chapters = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Chapter chapter = new Chapter();
                    chapter.setId(rs.getInt("id"));
                    chapter.setCreatedAt(rs.getTimestamp("created_at"));
                    chapter.setUpdatedAt(rs.getTimestamp("updated_at"));
                    chapter.setChapterNumber(rs.getString("chapter_number"));
                    chapter.setChapterTitle(rs.getString("chapter_title"));
                    chapter.setSurveyId(rs.getInt("survey_id"));
                    chapters.add(chapter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    @Override
    public Optional<Chapter> findChaptersByName(String name) {
        String query = "SELECT * FROM chapter WHERE chapter_title = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Chapter chapter = new Chapter();
                chapter.setId(rs.getInt("id"));
                chapter.setCreatedAt(rs.getTimestamp("created_at"));
                chapter.setUpdatedAt(rs.getTimestamp("updated_at"));
                chapter.setChapterNumber(rs.getString("chapter_number"));
                chapter.setChapterTitle(rs.getString("chapter_title"));
                chapter.setSurveyId(rs.getInt("survey_id"));
                return Optional.of(chapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
