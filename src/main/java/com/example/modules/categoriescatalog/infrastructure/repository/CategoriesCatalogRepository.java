package com.example.modules.categoriescatalog.infrastructure.repository;

import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CategoriesCatalogRepository implements CategoriesCatalogService {
    private Connection connection;

    public CategoriesCatalogRepository() {
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
    public void createCategoriesCatalog(CategoriesCatalog categoriesCatalog) {
        String query = "INSERT INTO categories_catalog (id, created_at, updated_at, name) VALUES (?, NOW(), NULL, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoriesCatalog.getId());
            ps.setString(2, categoriesCatalog.getName());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CategoriesCatalog has been created!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateCategoriesCatalog(CategoriesCatalog categoriesCatalog) {
        String query = "UPDATE categories_catalog SET updated_at = NOW(), name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoriesCatalog.getName());
            ps.setInt(2, categoriesCatalog.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CategoriesCatalog has been updated!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteCategoriesCatalog(int id) {
        String query = "DELETE FROM categories_catalog WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "CategoriesCatalog has been deleted!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Optional<CategoriesCatalog> findCategoriesCatalogById(int id) {
        String query = "SELECT * FROM categories_catalog WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
                categoriesCatalog.setId(rs.getInt("id"));
                categoriesCatalog.setCreatedAt(rs.getTimestamp("created_at"));
                categoriesCatalog.setUpdatedAt(rs.getTimestamp("updated_at"));
                categoriesCatalog.setName(rs.getString("name"));
                return Optional.of(categoriesCatalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "An Error has occurred", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    @Override
    public List<CategoriesCatalog> listCategoriesCatalogs() {
        String query = "SELECT * FROM categories_catalog";
        List<CategoriesCatalog> categoriesCatalogs = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
                    categoriesCatalog.setId(rs.getInt("id"));
                    categoriesCatalog.setCreatedAt(rs.getTimestamp("created_at"));
                    categoriesCatalog.setUpdatedAt(rs.getTimestamp("updated_at"));
                    categoriesCatalog.setName(rs.getString("name"));
                    categoriesCatalogs.add(categoriesCatalog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoriesCatalogs;
    }

    @Override
    public Optional<CategoriesCatalog> findCategoriesCatalogsByName(String name) {
        String query = "SELECT * FROM categories_catalog WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CategoriesCatalog categoriesCatalog = new CategoriesCatalog();
                categoriesCatalog.setId(rs.getInt("id"));
                categoriesCatalog.setCreatedAt(rs.getTimestamp("created_at"));
                categoriesCatalog.setUpdatedAt(rs.getTimestamp("updated_at"));
                categoriesCatalog.setName(rs.getString("name"));
                return Optional.of(categoriesCatalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}