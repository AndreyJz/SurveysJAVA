package com.example.UI.infrastructure.controller;

import com.example.UI.domain.service.GlobalService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public class DeleteController extends JFrame implements ActionListener {
    private GlobalService entity;
    private Optional<?> selectedDelete;
    private Object deleteService;
    private Object findServiceEntity;
    private Object listServiceEntity;
    private String propertyName;

    private JPanel mainPanel;
    private JButton backButton;
    private JButton deleteButton;
    private JComboBox<String> selectComboBox;

    public DeleteController(GlobalService entity, Object deleteService, Object listServiceEntity, Object findServiceEntity) {
        this.entity = entity;
        this.deleteService = deleteService;
        this.listServiceEntity = listServiceEntity;
        this.findServiceEntity = findServiceEntity;

        setTitle("Delete " + entity.getClass().getSimpleName());
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

        JLabel selectLabel = new JLabel("Select " + entity.getClass().getSimpleName());
        selectLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        selectLabel.setForeground(new Color(236, 224, 220));

        selectComboBox = generateComboBox(listServiceEntity);

        deleteButton = new JButton("Delete ->");
        deleteButton.addActionListener(this);
        backButton = new JButton("<- Go Back");
        backButton.addActionListener(this);

        mainPanel.add(selectLabel);
        mainPanel.add(selectComboBox);
        mainPanel.add(backButton);
        mainPanel.add(deleteButton);

        mainPanel.setVisible(true);
        add(mainPanel);

        setVisible(true);
    }

    private JComboBox<String> generateComboBox(Object listService) {
        JComboBox<String> comboBox = new JComboBox<>();
        try {
            Method listMethod = listService.getClass().getMethod("list");
            java.util.List<?> items = (List<?>) listMethod.invoke(listService);

            for (Object item : items) {

                switch (item.getClass().getSimpleName()) {
                    case "Chapter":
                        propertyName = "ChapterTitle";
                        break;
                    case "Question":
                        propertyName = "CommentQuestion";
                        break;
                    case "Survey", "CategoriesCatalog":
                        propertyName = "Name";
                        break;
                    case "SubresponseOptions":
                        propertyName = "SubresponseText";
                        break;
                    default:
                        propertyName = "OptionText";
                        break;
                }

                System.out.println(item);
                Method getPropertyMethod = item.getClass().getMethod("get" + propertyName);
                Object result = getPropertyMethod.invoke(item);
                if (result != null) {
                    comboBox.addItem(result.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
        } else if (e.getSource() == deleteButton) {
            String select = (String) selectComboBox.getSelectedItem();
            try {
                Method getDelete = findServiceEntity.getClass().getMethod("find", String.class);
                selectedDelete = (Optional<?>) getDelete.invoke(findServiceEntity, select);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            deleteEntity();

        }
    }

    public void deleteEntity() {
        try {
            Method getDeleteID = selectedDelete.get().getClass().getMethod("getId");
            int id = (int) getDeleteID.invoke(selectedDelete.get());

            Method deleteMethod = deleteService.getClass().getMethod("delete", int.class);
            deleteMethod.invoke(deleteService, id);
            System.out.println("Entity deleted: " + entity.getClass().getSimpleName());
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
