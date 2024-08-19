package com.example.infrastructure.controller;

import com.example.domain.service.GlobalService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

public class ListController extends JFrame {
    private GlobalService entity;

    private Map<String,String> types;

    public ListController(GlobalService entity, Object listService) {
        this.entity = entity;

        setTitle("List of " + entity.getClass().getSimpleName() + "s");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x123456));
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        try {
            Method listMethod = listService.getClass().getMethod("list");
            List<?> items = (List<?>) listMethod.invoke(listService);
            showMedicinesTable(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMedicinesTable(List<?> items) {
        types = entity.getTypes();
        Vector<String> columns = new Vector<>(types.keySet());

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Object item : items) {
            List<Object> row = new ArrayList<>();
            for (String type : types.keySet()) {
                try {
                    Method getProperty = item.getClass().getMethod("get" + type);
                    row.add(getProperty.invoke(item));
                } catch (Exception e) {
                    e.printStackTrace();
                    row.add("--");
                }
            }
            model.addRow(row.toArray());
        }

        JLabel title = new JLabel("List of " + entity.getClass().getSimpleName() + "s");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setForeground(new Color(236, 224, 220));
        title.setBounds(250, 23, 300, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(45,80,700,450);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);

        setVisible(true);
    }
}
