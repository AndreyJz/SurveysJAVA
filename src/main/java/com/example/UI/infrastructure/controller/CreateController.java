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
import java.util.concurrent.atomic.AtomicInteger;

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
    private Map<String,List<Object>> mapOfList;

    public CreateController(GlobalService entity, Object createService, Map<String, List<Object>> mapOfList) {
        this.entity = entity;
        this.createService = createService;
        this.mapOfList = mapOfList;

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

            String propertyName = "Name";

            comboBox.addItem("");

            for (Object item : items) {

                switch (item.getClass().getSimpleName()) {
                    case "Chapter":
                        propertyName = "ChapterTitle";
                        break;
                    case "Question":
                        propertyName = "CommentQuestion";
                        break;
                    case "ResponseOptions", "SubResponseOptions":
                        propertyName = "OptionText";
                        break;
                    default:
                        propertyName = "Name";
                        break;
                }

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
        AtomicInteger index = new AtomicInteger();
        types.forEach((propertyName, v) -> {
            if (!Objects.equals(propertyName, "Id") && !Objects.equals(propertyName, "CreatedAt") && !Objects.equals(propertyName, "UpdatedAt")) {
                System.out.println(propertyName);
                try {
                    String type = types.get(propertyName);
                    Component comp = components.get(index.get());
                    Object value = null;

                    if (type.equals("JComboBox")) {
                        mapOfList.forEach((key,val) -> {
                            if (propertyName.contains(key)) {
                                this.listService = val.get(0);
                                this.findService = val.get(1);
                            }
                        });
                        JComboBox<?> comboBox = (JComboBox<?>) comp;
                        Object selectedItem = comboBox.getSelectedItem();

                        if (selectedItem != "") {
                            Method findMethod = findService.getClass().getMethod("find", String.class);
                            Optional<?> result = (Optional<?>) findMethod.invoke(findService, selectedItem.toString());

                            if (result.isPresent()) {
                                Object relatedEntity = result.get();
                                Method getIdMethod = relatedEntity.getClass().getMethod("getId");
                                value = getIdMethod.invoke(relatedEntity);
                            } else {
                                throw new SQLException("Related entity not found for " + selectedItem.toString());
                            }
                        } else {
                            value = 0;
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

                    Class<?>[] parameterTypes = setter.getParameterTypes();

                    if (parameterTypes.length == 1) {
                        Class<?> parameterType = parameterTypes[0];

                        System.out.println(value);
                        if (parameterType == int.class || parameterType == Integer.class) {
                            if (value instanceof String) {
                                value = Integer.parseInt((String) value);
                            } else if (value instanceof Number) {
                                value = ((Number) value).intValue();
                                setter.invoke(entity, value);
                            }
                        } else if (value.equals("")) {
                            value = null;
                        } else {
                            setter.invoke(entity, value);
                        }

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                index.getAndIncrement();
            }

        });

        try {
            Method createMethod = createService.getClass().getMethod("create", entity.getClass());
            createMethod.invoke(createService, entity);
            System.out.println("Entity saved: " + entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
