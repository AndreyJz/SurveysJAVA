package com.example.modules.responsequestions.infrastructure.repository;

import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.jar.Attributes.Name;

public class ResponseQuestionRepository implements ResponseQuestionService {
    private Connection connection;

    public ResponseQuestionRepository() {
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
    public void createResponseQuestion(ResponseQuestion responseQuestion) {
        String query = "INSERT INTO response_question (response_id, subresponses_id, responsetext) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, responseQuestion.getResponseId());
            ps.setInt(2, responseQuestion.getSubresponsesId());
            ps.setString(3, responseQuestion.getResponseText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Response Question has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateResponseQuestion(ResponseQuestion responseQuestion) {
        String query = "UPDATE response_question SET response_id = ?, subresponses_id = ?, responsetext = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, responseQuestion.getResponseId());
            ps.setInt(2, responseQuestion.getSubresponsesId());
            ps.setString(3, responseQuestion.getResponseText());
            ps.setInt(4, responseQuestion.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Response Question has been updated!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteResponseQuestion(int id) {
        String query = "DELETE FROM response_question WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Response Question has been deleted!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<ResponseQuestion> findResponseQuestionById(int id) {
        String query = "SELECT * FROM response_question WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ResponseQuestion responseQuestion = new ResponseQuestion();
                responseQuestion.setId(rs.getInt("id"));
                responseQuestion.setResponseId(rs.getInt("response_id"));
                responseQuestion.setSubresponsesId(rs.getInt("subresponses_id"));
                responseQuestion.setResponseText(rs.getString("responsetext"));
                return Optional.of(responseQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ResponseQuestion> findResponseQuestionByName(String name) {
        String query = "SELECT * FROM response_question WHERE responsetext = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ResponseQuestion responseQuestion = new ResponseQuestion();
                responseQuestion.setId(rs.getInt("id"));
                responseQuestion.setResponseId(rs.getInt("response_id"));
                responseQuestion.setSubresponsesId(rs.getInt("subresponses_id"));
                responseQuestion.setResponseText(rs.getString("responsetext"));
                return Optional.of(responseQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public List<ResponseQuestion> listResponseQuestions() {
        List<ResponseQuestion> responseQuestions = new ArrayList<>();
        String query = "SELECT * FROM response_question";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ResponseQuestion responseQuestion = new ResponseQuestion();
                responseQuestion.setId(rs.getInt("id"));
                responseQuestion.setResponseId(rs.getInt("response_id"));
                responseQuestion.setSubresponsesId(rs.getInt("subresponses_id"));
                responseQuestion.setResponseText(rs.getString("responsetext"));
                responseQuestions.add(responseQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return responseQuestions;
    }

    @Override
    public Optional<ResponseQuestion> findResponseQuestionsByText(String responseText) {
        String query = "SELECT * FROM response_question WHERE responsetext LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + responseText + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ResponseQuestion responseQuestion = new ResponseQuestion();
                responseQuestion.setId(rs.getInt("id"));
                responseQuestion.setResponseId(rs.getInt("response_id"));
                responseQuestion.setSubresponsesId(rs.getInt("subresponses_id"));
                responseQuestion.setResponseText(rs.getString("responsetext"));
                return Optional.of(responseQuestion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }
}