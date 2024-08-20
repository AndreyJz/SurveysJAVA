package com.example.modules.login.infrastructure.controller;

import com.example.UI.infrastructure.controller.AdminForumController;
import com.example.modules.login.application.LoginCheckUC;
import com.example.modules.login.application.RoleUC;
import com.example.modules.login.domain.entity.Login;
import com.example.modules.login.domain.service.LoginService;
import com.example.modules.login.infrastructure.repository.LoginRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginController extends JFrame implements ActionListener {
    private LoginCheckUC loginCheck;

    private JLabel tittle;
    private JPanel contentPane;
    private JLabel username;
    private JTextField usernameField;
    private JLabel password;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginController() {
        initializeController();
    }

    private void initializeController() {
        setTitle("WELCOME");
        setSize(850, 600);
        getContentPane().setBackground(new Color(0x123456));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        tittle = new JLabel("Forum Surveys");
        tittle.setFont(new Font("Calibri", Font.BOLD, 100));
        tittle.setForeground(new Color(236, 224, 220));
        tittle.setBounds((getWidth() - 600) / 2, (getHeight() - 430) / 2,700,150);

        contentPane = new JPanel();
        contentPane.setBounds((getWidth() - 400) / 2,(getHeight() - 150) / 2,400,200);
        contentPane.setLayout(new GridLayout(0,1,10,5));
        contentPane.setBackground(new Color(0x123456));

        username = new JLabel("Username:");
        username.setFont(new Font("Calibri", Font.BOLD, 30));
        username.setForeground(new Color(236, 224, 220));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Calibri", Font.BOLD, 20));
        password = new JLabel("Password:");
        password.setFont(new Font("Calibri", Font.BOLD, 30));
        password.setForeground(new Color(236, 224, 220));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Calibri", Font.BOLD, 20));
        passwordField.setEchoChar('*');

        contentPane.add(username);
        contentPane.add(usernameField);
        contentPane.add(password);
        contentPane.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Calibri", Font.BOLD, 35));
        loginButton.setBounds((getWidth() - 200) / 2, 470,200,40);
        loginButton.setBackground(new Color(244, 221, 216));
        loginButton.setForeground(new Color(0,0,0));
        loginButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        loginButton.addActionListener(this);

        add(tittle);
        add(contentPane);
        add(loginButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            LoginService ls = new LoginRepository();
            LoginCheckUC check = new LoginCheckUC(ls);
            Optional<Login> logged = check.login(user, pass);

            RoleUC role = new RoleUC(ls);
            boolean admin = role.checkRole(logged.get().getId());

            if (admin) {
                setVisible(false);
                AdminForumController menu = new AdminForumController();
                menu.setResizable(false);
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
            } else {
                System.out.println("no");
            }
        }
    }

}
