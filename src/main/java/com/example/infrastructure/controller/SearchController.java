package com.example.infrastructure.controller;

import com.example.domain.service.GlobalService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

public class SearchController extends JFrame implements ActionListener {
    private GlobalService entity;
    private Object findServiceEntity;
    private Map<String, String> types;

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JButton backButton;
    private JButton returnButton;
    private JButton searchButton;
    private JTextField selectTextId;

    public SearchController(GlobalService entity, Object findServiceEntity) {
        this.entity = entity;
        this.findServiceEntity = findServiceEntity;

        setTitle("Look for " + entity.getClass().getSimpleName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x123456));
        setSize(500, 700);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0x123456));
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));
        mainPanel.setBounds(75, 150, 350, 100);

        JLabel selectLabel = new JLabel("Type the ID " + entity.getClass().getSimpleName());
        selectLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        selectLabel.setForeground(new Color(236, 224, 220));

        selectTextId = new JTextField();
        selectTextId.setFont(new Font("Calibri", Font.BOLD, 15));

        searchButton = new JButton("Search ->");
        searchButton.addActionListener(this);
        backButton = new JButton("<- Go Back");
        backButton.addActionListener(this);

        mainPanel.add(selectLabel);
        mainPanel.add(selectTextId);
        mainPanel.add(backButton);
        mainPanel.add(searchButton);

        mainPanel.setVisible(true);
        add(mainPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        } else if (e.getSource() == searchButton) {
            int selected = Integer.parseInt(selectTextId.getText());
            mainPanel.setVisible(false);

            try {
                Method findMethod = findServiceEntity.getClass().getMethod("find", int.class);
                Optional<?> gotcha = (Optional<?>) findMethod.invoke(findServiceEntity, selected);
                if (gotcha.isPresent()) {
                    showEntity(gotcha.get());
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with ID: " + selected, "Error", JOptionPane.ERROR_MESSAGE);
                    mainPanel.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                mainPanel.setVisible(true);
            }
        } else if (e.getSource() == returnButton) {
            contentPanel.setVisible(false);
            mainPanel.setVisible(true);
        }
    }

    public void showEntity(Object gotcha) {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        contentPanel.setBounds(35, 60, 425, 470);

        JLabel empty = new JLabel();
        returnButton = new JButton("Go Back");
        returnButton.addActionListener(this);

        try {
            types = entity.getTypes();
            for (String key : types.keySet()) {
                JLabel label1 = new JLabel(key);
                label1.setFont(new Font("Calibri", Font.BOLD, 30));
                label1.setForeground(new Color(236, 224, 220));

                Method getMethod = gotcha.getClass().getMethod("get" + key);
                Object value = getMethod.invoke(gotcha);

                JTextField text = new JTextField();
                text.setText(value != null ? value.toString() : "");
                text.setFont(new Font("Calibri", Font.BOLD, 23));
                text.setForeground(new Color(58, 58, 58));

                text.setEditable(false);

                contentPanel.add(label1);
                contentPanel.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentPanel.add(empty);
        contentPanel.add(returnButton);

        contentPanel.setVisible(true);
        add(contentPanel);
    }
}
