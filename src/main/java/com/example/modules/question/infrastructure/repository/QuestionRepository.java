package com.example.modules.question.infrastructure.repository;

import com.example.modules.question.domain.entity.Question;
import com.example.modules.question.domain.service.QuestionService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class QuestionRepository implements QuestionService {
    private Connection connection;

    public QuestionRepository() {
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
    public void createQuestion(Question question) {
        String query = "INSERT INTO questions (id, created_at, updated_at, question_number, response_type, comment_question, question_text, chapter_id) VALUES (?, NOW, NULL, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, question.getId());
            ps.setString(2, question.getQuestionNumber());
            ps.setString(3, question.getResponseType());
            ps.setString(4, question.getCommentQuestion());
            ps.setString(5, question.getQuestionText());
            ps.setInt(6, question.getChapterId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Question has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateQuestion(Question question) {
        String query = "UPDATE questions SET updated_at = NOW, question_number = ?, reponse_type = ?, comment_question = ?, question_text = ?, chapter_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, question.getQuestionNumber());
            ps.setString(2, question.getResponseType());
            ps.setString(3, question.getCommentQuestion());
            ps.setString(4, question.getQuestionText());
            ps.setInt(5, question.getChapterId());
            ps.setInt(6, question.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Question has been updated!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteQuestion(int id) {
        String query = "DELETE FROM questions WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Question has been deleted!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<Question> findQuestionById(int id) {
        String query = "SELECT * FROM questions WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setCreatedAt(rs.getDate("created_at"));
                question.setUpdatedAt(rs.getDate("updated_at"));
                question.setQuestionNumber(rs.getString("question_number"));
                question.setResponseType(rs.getString("response_type"));
                question.setCommentQuestion(rs.getString("comment_question"));
                question.setQuestionText(rs.getString("question_text"));
                question.setChapterId(rs.getInt("chapter_id"));
                return Optional.of(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public List<Question> listQuestions() {
        String query = "SELECT * FROM questions";
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Question question = new Question();
                    question.setId(rs.getInt("id"));
                    question.setCreatedAt(rs.getDate("created_at"));
                    question.setUpdatedAt(rs.getDate("updated_at"));
                    question.setQuestionNumber(rs.getString("question_number"));
                    question.setResponseType(rs.getString("response_type"));
                    question.setCommentQuestion(rs.getString("comment_question"));
                    question.setQuestionText(rs.getString("question_text"));
                    question.setChapterId(rs.getInt("chapter_id"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public Optional<Question> findQuestionsByName(String name) {
        String query = "SELECT * FROM questions WHERE comment_question = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setCreatedAt(rs.getDate("created_at"));
                question.setUpdatedAt(rs.getDate("updated_at"));
                question.setQuestionNumber(rs.getString("question_number"));
                question.setResponseType(rs.getString("response_type"));
                question.setCommentQuestion(rs.getString("comment_question"));
                question.setQuestionText(rs.getString("question_text"));
                question.setChapterId(rs.getInt("chapter_id"));
                return Optional.of(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Question> listQuestionsByChapterId(int id) {
        String query = "SELECT * FROM questions WHERE chapter_id = ?";
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Question question = new Question();
                    question.setId(rs.getInt("id"));
                    question.setCreatedAt(rs.getDate("created_at"));
                    question.setUpdatedAt(rs.getDate("updated_at"));
                    question.setQuestionNumber(rs.getString("question_number"));
                    question.setResponseType(rs.getString("response_type"));
                    question.setCommentQuestion(rs.getString("comment_question"));
                    question.setQuestionText(rs.getString("question_text"));
                    question.setChapterId(rs.getInt("chapter_id"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
