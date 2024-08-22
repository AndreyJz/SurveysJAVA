package com.example.modules.survey.infrastructure.repository;

import com.example.modules.survey.domain.entity.Survey;
import com.example.modules.survey.domain.service.SurveySercive;

import java.sql.*;
import java.util.*;

public class SurveyRepository implements SurveySercive {
    private Connection connection;

    public SurveyRepository() {
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
    public List<Survey> listSurveys() {
        String query = "SELECT * FROM surveys";
        List<Survey> surveys = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Survey survey = new Survey();
                survey.setId(rs.getInt("id"));
                survey.setCreatedAt(rs.getDate("created_at"));
                survey.setUpdatedAt(rs.getDate("updated_at"));
                survey.setDescription(rs.getString("description"));
                survey.setName(rs.getString("name"));
                surveys.add(survey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveys;
    }

    @Override
    public Optional<Survey> findSurveyByName(String name) {
        String query = "SELECT * FROM surveys WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Survey survey = new Survey();
                    survey.setId(rs.getInt("id"));
                    survey.setCreatedAt(rs.getDate("created_at"));
                    survey.setUpdatedAt(rs.getDate("updated_at"));
                    survey.setDescription(rs.getString("description"));
                    survey.setName(rs.getString("name"));
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Survey> findSurveyById(int id) {
        String query = "SELECT * FROM surveys WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Survey survey = new Survey();
                    survey.setId(rs.getInt("id"));
                    survey.setCreatedAt(rs.getDate("created_at"));
                    survey.setUpdatedAt(rs.getDate("updated_at"));
                    survey.setDescription(rs.getString("description"));
                    survey.setName(rs.getString("name"));
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
