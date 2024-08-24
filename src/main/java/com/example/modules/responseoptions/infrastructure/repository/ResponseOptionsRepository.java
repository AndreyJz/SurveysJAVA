package com.example.modules.responseoptions.infrastructure.repository;

import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class ResponseOptionsRepository implements ResponseOptionsService {
    private Connection connection;

    public ResponseOptionsRepository() {
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
    public void createResponseOptions(ResponseOptions responseOptions) {
        String query = "INSERT INTO response_options (option_value, categorycatalog_id, created_at, updated_at, parentresponse_id, question_id, typecomponenthtml, comment_response, option_text) VALUES (?, ?, NOW(), NULL, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, responseOptions.getOptionValue());
            if (responseOptions.getCategoryCatalogId() == 0) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, responseOptions.getParentResponseId());
            }
            if (responseOptions.getParentResponseId() == 0) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, responseOptions.getParentResponseId());
            }
            ps.setInt(4, responseOptions.getQuestionId());
            ps.setString(5, responseOptions.getTypeComponentHtml());
            ps.setString(6, responseOptions.getCommentResponse());
            ps.setString(7, responseOptions.getOptionText());
            System.out.println(responseOptions.getCategoryCatalogId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "ResponseOptions has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateResponseOptions(ResponseOptions responseOptions) {
        String query = "UPDATE response_options SET option_value = ?, categorycatalog_id = ?, updated_at = NOW(), parentresponse_id = ?, question_id = ?, typecomponenthtml = ?, comment_response = ?, option_text = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, responseOptions.getOptionValue());
            ps.setInt(2, responseOptions.getCategoryCatalogId());
            ps.setInt(3, responseOptions.getParentResponseId());
            ps.setInt(4, responseOptions.getQuestionId());
            ps.setString(5, responseOptions.getTypeComponentHtml());
            ps.setString(6, responseOptions.getCommentResponse());
            ps.setString(7, responseOptions.getOptionText());
            ps.setInt(8, responseOptions.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "ResponseOptions has been updated!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteResponseOptions(int id) {
        String query = "DELETE FROM response_options WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "ResponseOptions has been deleted!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<ResponseOptions> findResponseOptionsById(int id) {
        String query = "SELECT * FROM response_options WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ResponseOptions responseOptions = new ResponseOptions();
                responseOptions.setId(rs.getInt("id"));
                responseOptions.setOptionValue(rs.getInt("option_value"));
                responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
                responseOptions.setQuestionId(rs.getInt("question_id"));
                responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                responseOptions.setCommentResponse(rs.getString("comment_response"));
                responseOptions.setOptionText(rs.getString("option_text"));
                return Optional.of(responseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public List<ResponseOptions> listResponseOptions() {
        String query = "SELECT * FROM response_options";
        List<ResponseOptions> responseOptionsList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions();
                    responseOptions.setId(rs.getInt("id"));
                    responseOptions.setOptionValue(rs.getInt("option_value"));
                    responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                    responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                    responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                    responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
                    responseOptions.setQuestionId(rs.getInt("question_id"));
                    responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                    responseOptions.setCommentResponse(rs.getString("comment_response"));
                    responseOptions.setOptionText(rs.getString("option_text"));
                    responseOptionsList.add(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responseOptionsList;
    }

    @Override
    public Optional<ResponseOptions> findResponseOptionsByName(String name) {
        String query = "SELECT * FROM response_options WHERE option_text = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions();
                    responseOptions.setId(rs.getInt("id"));
                    responseOptions.setOptionValue(rs.getInt("option_value"));
                    responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                    responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                    responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                    responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
                    responseOptions.setQuestionId(rs.getInt("question_id"));
                    responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                    responseOptions.setCommentResponse(rs.getString("comment_response"));
                    responseOptions.setOptionText(rs.getString("option_text"));
                    return Optional.of(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ResponseOptions> listResponseOptionsByQuestionId(int Id) {
        String query = "SELECT * FROM response_options WHERE question_id = ?";
        List<ResponseOptions> responseOptionsList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions();
                    responseOptions.setId(rs.getInt("id"));
                    responseOptions.setOptionValue(rs.getInt("option_value"));
                    responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                    responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                    responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                    responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
                    responseOptions.setQuestionId(rs.getInt("question_id"));
                    responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                    responseOptions.setCommentResponse(rs.getString("comment_response"));
                    responseOptions.setOptionText(rs.getString("option_text"));
                    responseOptionsList.add(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responseOptionsList;
    }

    @Override
    public List<ResponseOptions> listResponseOptionsByParentId(int id) {
        String query = "SELECT * FROM response_options WHERE parentresponse_id = ?";
        List<ResponseOptions> responseOptionsList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ResponseOptions responseOptions = new ResponseOptions();
                    responseOptions.setId(rs.getInt("id"));
                    responseOptions.setOptionValue(rs.getInt("option_value"));
                    responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
                    responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                    responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                    responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
                    responseOptions.setQuestionId(rs.getInt("question_id"));
                    responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
                    responseOptions.setCommentResponse(rs.getString("comment_response"));
                    responseOptions.setOptionText(rs.getString("option_text"));
                    responseOptionsList.add(responseOptions);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responseOptionsList;
    }

//    @Override
//    public List<ResponseOptions> listResponseOptionsByQuestionId(int Id) {
//        String query = "SELECT * FROM response_options WHERE categorycatalog_id = ?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setInt(1, Id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                ResponseOptions responseOptions = new ResponseOptions();
//                responseOptions.setId(rs.getInt("id"));
//                responseOptions.setOptionValue(rs.getInt("option_value"));
//                responseOptions.setCategoryCatalogId(rs.getInt("categorycatalog_id"));
//                responseOptions.setCreatedAt(rs.getTimestamp("created_at"));
//                responseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
//                responseOptions.setParentResponseId(rs.getInt("parentresponse_id"));
//                responseOptions.setQuestionId(rs.getInt("question_id"));
//                responseOptions.setTypeComponentHtml(rs.getString("typecomponenthtml"));
//                responseOptions.setCommentResponse(rs.getString("comment_response"));
//                responseOptions.setOptionText(rs.getString("option_text"));
//                return List.of(responseOptions);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}