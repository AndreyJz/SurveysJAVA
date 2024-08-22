package com.example.modules.subresponseoptions.infrastructure.repository;

import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;
import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class SubresponseOptionsRepository implements SubresponseOptionsService {
    private Connection connection;

    public SubresponseOptionsRepository() {
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
    public void createSubresponseOptions(SubresponseOptions subresponseOptions) {
        String query = "INSERT INTO subresponse_options (subresponse_number, created_at, updated_at, component_html, subresponse_text, responseoptions_id) VALUES (?, NOW(), NULL, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, subresponseOptions.getSubresponseNumber());
            ps.setString(2, subresponseOptions.getComponentHtml());
            ps.setString(3, subresponseOptions.getSubresponseText());
            ps.setInt(4, subresponseOptions.getResponseOptionsId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "SubresponseOptions has been created!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubresponseOptions(SubresponseOptions subresponseOptions) {
        String query = "UPDATE subresponse_options SET subresponse_number = ?, updated_at = NOW(), component_html = ?, subresponse_text = ?, responseoptions_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, subresponseOptions.getSubresponseNumber());
            ps.setString(2, subresponseOptions.getComponentHtml());
            ps.setString(3, subresponseOptions.getSubresponseText());
            ps.setInt(4, subresponseOptions.getResponseOptionsId());
            ps.setInt(5, subresponseOptions.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "SubresponseOptions has been updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubresponseOptions(int id) {
        String query = "DELETE FROM subresponse_options WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "SubresponseOptions has been deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SubresponseOptions> findSubresponseOptionsById(int id) {
        String query = "SELECT * FROM subresponse_options WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SubresponseOptions subresponseOptions = new SubresponseOptions();
                subresponseOptions.setId(rs.getInt("id"));
                subresponseOptions.setSubresponseNumber(rs.getInt("subresponse_number"));
                subresponseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                subresponseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                subresponseOptions.setComponentHtml(rs.getString("component_html"));
                subresponseOptions.setSubresponseText(rs.getString("subresponse_text"));
                subresponseOptions.setResponseOptionsId(rs.getInt("responseoptions_id"));
                return Optional.of(subresponseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<SubresponseOptions> listSubresponseOptions() {
        String query = "SELECT * FROM subresponse_options";
        List<SubresponseOptions> subresponseOptionsList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SubresponseOptions subresponseOptions = new SubresponseOptions();
                subresponseOptions.setId(rs.getInt("id"));
                subresponseOptions.setSubresponseNumber(rs.getInt("subresponse_number"));
                subresponseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                subresponseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                subresponseOptions.setComponentHtml(rs.getString("component_html"));
                subresponseOptions.setSubresponseText(rs.getString("subresponse_text"));
                subresponseOptions.setResponseOptionsId(rs.getInt("responseoptions_id"));
                subresponseOptionsList.add(subresponseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subresponseOptionsList;
    }

    @Override
    public List<SubresponseOptions> findSubresponseOptionsByResponseOptionsId(int id) {
        String query = "SELECT * FROM subresponse_options WHERE responseoptions_id = ?";
        List<SubresponseOptions> subresponseOptionsList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SubresponseOptions subresponseOptions = new SubresponseOptions();
                subresponseOptions.setId(rs.getInt("id"));
                subresponseOptions.setSubresponseNumber(rs.getInt("subresponse_number"));
                subresponseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                subresponseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                subresponseOptions.setComponentHtml(rs.getString("component_html"));
                subresponseOptions.setSubresponseText(rs.getString("subresponse_text"));
                subresponseOptions.setResponseOptionsId(rs.getInt("responseoptions_id"));
                subresponseOptionsList.add(subresponseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subresponseOptionsList;
    }

    @Override
    public List<SubresponseOptions> findSubresponseOptionsBySubresponseText(String text) {
        String query = "SELECT * FROM subresponse_options WHERE subresponse_text = ?";
        List<SubresponseOptions> subresponseOptionsList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SubresponseOptions subresponseOptions = new SubresponseOptions();
                subresponseOptions.setId(rs.getInt("id"));
                subresponseOptions.setSubresponseNumber(rs.getInt("subresponse_number"));
                subresponseOptions.setCreatedAt(rs.getTimestamp("created_at"));
                subresponseOptions.setUpdatedAt(rs.getTimestamp("updated_at"));
                subresponseOptions.setComponentHtml(rs.getString("component_html"));
                subresponseOptions.setSubresponseText(rs.getString("subresponse_text"));
                subresponseOptions.setResponseOptionsId(rs.getInt("responseoptions_id"));
                subresponseOptionsList.add(subresponseOptions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subresponseOptionsList;
    }
}