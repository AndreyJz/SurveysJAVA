package com.example.modules.survey_archive.infrastructure.repository;

import com.example.modules.survey_archive.domain.entity.Survey_Archive;
import com.example.modules.survey_archive.domain.service.Survey_ArchiveService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Survey_ArchiveRepository implements Survey_ArchiveService {
    private Connection connection;

    public Survey_ArchiveRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createSurvey_Archive(Survey_Archive survey_archive) {
        String query = "INSERT INTO survey_archive (created_at, updated_at, survey_id, payload) VALUES (NOW(),NULL,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, survey_archive.getSurveyId());
            ps.setString(2, survey_archive.getPayload());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Survey_Archive has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Survey_Archive> listSurveys_Archive() {
        String query = "SELECT * FROM survey_archive ORDER BY created_at DESC";
        List<Survey_Archive> survey_archives = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Survey_Archive survey_archive = new Survey_Archive();
                    survey_archive.setId(rs.getInt("id"));
                    survey_archive.setCreatedAt(rs.getTimestamp("created_at"));
                    survey_archive.setUpdatedAt(rs.getTimestamp("updated_at"));
                    survey_archive.setSurveyId(rs.getInt("survey_id"));
                    survey_archive.setPayload(rs.getString("payload"));
                    survey_archives.add(survey_archive);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return survey_archives;
    }

    @Override
    public Optional<Survey_Archive> findSurvey_Archive(int id) {
        String query = "SELECT * FROM survey_archive WHERE id = ?";
        Survey_Archive survey_archive = new Survey_Archive();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    survey_archive.setSurveyId(rs.getInt("survey_id"));
                    survey_archive.setCreatedAt(rs.getTimestamp("created_at"));
                    survey_archive.setUpdatedAt(rs.getTimestamp("updated_at"));
                    survey_archive.setPayload(rs.getString("payload"));
                    return Optional.of(survey_archive);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
