package com.example.UI.infrastructure.controller;

import com.example.UI.domain.service.GlobalService;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class CreateController extends JFrame implements ActionListener {
    private GlobalService entity;
    private Object createService;
    private Object listService;
    private Object findService;

    private JPanel contentPanel;
    private JButton backButton;
    private JButton createButton;
    private Map<String, String> types;
    private List<Component> components = new ArrayList<>();

    public CreateController(GlobalService entity, Object createService, Map<String, List<Object>> mapOfList) {
        this.entity = entity;
        this.createService = createService;

        setTitle("Create " + entity.getClass().getSimpleName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x123456));
        setSize(500, 700);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        contentPanel.setBounds(35, 60, 425, 470);

        backButton = new JButton("Go Back");
        backButton.addActionListener(this);
        createButton = new JButton("Create");
        createButton.addActionListener(this);

        types = entity.getTypes();
        System.out.println(types);

        types.forEach((k, v) -> {
            if (!Objects.equals(k, "Id") && !Objects.equals(k, "CreatedAt") && !Objects.equals(k, "UpdatedAt")) {
                JLabel label = new JLabel(k);
                label.setFont(new Font("Calibri", Font.BOLD, 25));
                label.setForeground(new Color(236, 224, 220));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label);
                Component comp = null;
                if (v.equals("JComboBox")) {
                    mapOfList.forEach((key,val) -> {
                        if (k.contains(key)) {
                            this.listService = val.get(0);
                            this.findService = val.get(1);
                        }
                    });
                    comp = generateComboBox(entity);
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));
                } else if (v.equals("JDateChooser")) {
                    comp = new JDateChooser();
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));
                } else {
                    comp = new JTextField();
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));
                }

                contentPanel.add(comp);
                components.add(comp);
            }
        });

        contentPanel.add(backButton);
        contentPanel.add(createButton);
        add(contentPanel);

        setVisible(true);
    }

    private JComboBox<String> generateComboBox(GlobalService entity) {
        JComboBox<String> comboBox = new JComboBox<>();
        try {
            Method listMethod = listService.getClass().getMethod("list");
            List<?> items = (List<?>) listMethod.invoke(listService);

            String propertyName = entity.getClass().getSimpleName().equals("Chapter") ? "Name" : "ChapterTitle";

            for (Object item : items) {
                Method getPropertyMethod = item.getClass().getMethod("get" + propertyName);
                comboBox.addItem((String) getPropertyMethod.invoke(item));
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
        } else if (e.getSource() == createButton) {
            saveEntity();
        }
    }

    private void saveEntity() {
        int index = 0;
        for (String propertyName : types.keySet()) {
            if (!Objects.equals(propertyName, "Id") && !Objects.equals(propertyName, "CreatedAt") && !Objects.equals(propertyName, "UpdatedAt")) {
                System.out.println(propertyName);
                try {
                    String type = types.get(propertyName);
                    Component comp = components.get(index);
                    Object value = null;

                    if (type.equals("JComboBox")) {
                        JComboBox<?> comboBox = (JComboBox<?>) comp;
                        Object selectedItem = comboBox.getSelectedItem();

                        Method findMethod = findService.getClass().getMethod("find", String.class);
                        Optional<?> result = (Optional<?>) findMethod.invoke(findService, selectedItem.toString());

                        if (result.isPresent()) {
                            Object relatedEntity = result.get();
                            Method getIdMethod = relatedEntity.getClass().getMethod("getId");
                            value = getIdMethod.invoke(relatedEntity);
                        } else {
                            throw new SQLException("Related entity not found for " + selectedItem.toString());
                        }

                    } else if (type.equals("JDateChooser")) {
                        JDateChooser dateChooser = (JDateChooser) comp;
                        value = dateChooser.getDate();
                    } else if (type.equals("TextField")) {
                        JTextField textField = (JTextField) comp;
                        value = textField.getText();
                    }

                    PropertyDescriptor pd = new PropertyDescriptor(propertyName, entity.getClass());
                    Method setter = pd.getWriteMethod();
                    setter.invoke(entity, value);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                index++;
            }
        }

        try {
            Method createMethod = createService.getClass().getMethod("create", entity.getClass());
            createMethod.invoke(createService, entity);
            System.out.println("Entity saved: " + entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
