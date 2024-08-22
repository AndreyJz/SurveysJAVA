package com.example.modules.login.infrastructure.repository;

import com.example.modules.login.domain.entity.Login;
import com.example.modules.login.domain.service.LoginService;

import javax.swing.*;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class LoginRepository implements LoginService {
    private Connection connection;

    public LoginRepository() {
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
    public Optional<Login> loginCheck(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Login login = new Login();
                login.setId(rs.getInt("id"));
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                return Optional.of(login);
            } else {
                JOptionPane.showMessageDialog(null,"Please, Check and Try Again", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean role(int id) {
        String query = "SELECT r.name FROM users_roles ur JOIN roles r ON r.id = ur.role_id WHERE ur.user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("name").equals("admin")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
