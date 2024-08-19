package com.example.infrastructure.controller;

import com.example.domain.service.GlobalService;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class UpdateController extends JFrame implements ActionListener {

    private GlobalService entity;
    private Optional<?> selectedUpdate;
    private Object updateService;
    private Object listService;
    private Object findService;
    private Object findServiceEntity;
    private Object findServiceID;
    private String propertyName;
    private String propertyID;

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JButton backButton;
    private JButton cancelButton;
    private JButton updateButton;
    private JButton updatedButton;
    private JComboBox<String> selectComboBox;
    private Map<String, String> types;
    private List<Component> components = new ArrayList<>();

    public UpdateController(GlobalService entity, Object updateService, Object listServiceEntity, Object listService, Object findServiceEntity, Object findServiceID, Object findService) {
        this.entity = entity;
        this.updateService = updateService;
        this.listService = listService;
        this.findServiceEntity = findServiceEntity;
        this.findServiceID = findServiceID;
        this.findService = findService;

        setTitle("Update " + entity.getClass().getSimpleName());
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

        selectComboBox = generateComboBox(entity, propertyName, listServiceEntity);

        updatedButton = new JButton("Next ->");
        updatedButton.addActionListener(this);
        cancelButton = new JButton("<- Cancel");
        cancelButton.addActionListener(this);

        mainPanel.add(selectLabel);
        mainPanel.add(selectComboBox);
        mainPanel.add(cancelButton);
        mainPanel.add(updatedButton);

        mainPanel.setVisible(true);
        add(mainPanel);

        setVisible(true);
    }

    private void generateUpdateForm(Object selectedUpdate) {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        contentPanel.setBounds(35, 60, 425, 470);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        backButton = new JButton("Go Back");
        backButton.addActionListener(this);

        types = entity.getTypes();

        types.forEach((k, v) -> {
            if (!Objects.equals(k, "Id") && !Objects.equals(k, "CreatedAt") && !Objects.equals(k, "UpdatedAt")) {
                JLabel label = new JLabel(k);
                label.setFont(new Font("Calibri", Font.BOLD, 25));
                label.setForeground(new Color(236, 224, 220));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label);
                Component comp = null;
                if (v.equals("JComboBox")) {
                    comp = generateComboBox(entity, propertyName, listService);
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));
                    JComboBox<?> comboBox = (JComboBox<?>) comp;
                    try {
                        Method getSelected = entity.getClass().getMethod("get" + propertyID);
                        Object relatedEntityId = getSelected.invoke(entity);

                        if (relatedEntityId != null) {
                            Method findRelatedMethod = findServiceID.getClass().getMethod("find", int.class);
                            Optional<?> result = (Optional<?>) findRelatedMethod.invoke(findServiceID, relatedEntityId);

                            if (result.isPresent()) {
                                Object relatedEntity = result.get();
                                Method getPropertyMethod = relatedEntity.getClass().getMethod("get" + propertyName);
                                comboBox.setSelectedItem(getPropertyMethod.invoke(relatedEntity));
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    comp = comboBox;

                } else if (v.equals("JDateChooser")) {
                    comp = new JDateChooser();
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));

                    JDateChooser dateJ = (JDateChooser) comp;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Method getDateMethod = selectedUpdate.getClass().getMethod("get" + k);
                        Object result = getDateMethod.invoke(selectedUpdate);
                        if (result != null) {
                            Date dateS = simpleDateFormat.parse(result.toString());
                            dateJ.setDate(dateS);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    comp = new JTextField();
                    comp.setFont(new Font("Calibri", Font.BOLD, 25));
                    JTextField textField = (JTextField) comp;

                    try {
                        Method getText = selectedUpdate.getClass().getMethod("get" + k);
                        Object result = getText.invoke(selectedUpdate);
                        System.out.println(result);
                        if (result != null) {
                            textField.setText(result.toString());
                        } else {
                            textField.setText("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                contentPanel.add(comp);
                components.add(comp);
            }
        });

        contentPanel.add(backButton);
        contentPanel.add(updateButton);

        contentPanel.setVisible(true);
        add(contentPanel);
    }

    private JComboBox<String> generateComboBox(GlobalService entity, String propertyName, Object listService) {
        JComboBox<String> comboBox = new JComboBox<>();
        try {
            Method listMethod = listService.getClass().getMethod("list");
            List<?> items = (List<?>) listMethod.invoke(listService);

            for (Object item : items) {

                switch (item.getClass().getSimpleName()) {
                    case "Chapter":
                        propertyName = "ChapterTitle";
                        propertyID = "SurveyId";
                        break;
                    case "Question":
                        propertyName = "CommentQuestion";
                        propertyID = "ChapterId";
                        break;
                    default:
                        propertyName = "Name";
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
            mainPanel.setVisible(true);
            contentPanel.setVisible(false);
        } else if (e.getSource() == updateButton) {
            updateEntity();
        } else if (e.getSource() == cancelButton) {
            dispose();
        } else if (e.getSource() == updatedButton) {
            mainPanel.setVisible(false);
            String selected = (String) selectComboBox.getSelectedItem();
            try {
                Method filterMethod = findServiceEntity.getClass().getMethod("find", String.class);
                selectedUpdate = (Optional<?>) filterMethod.invoke(findServiceEntity, selected);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            generateUpdateForm(selectedUpdate.get());
        }
    }

    public void updateEntity() {
        int index = 0;
        for (String propertyName : types.keySet()) {
            if (!Objects.equals(propertyName, "Id") && !Objects.equals(propertyName, "CreatedAt") && !Objects.equals(propertyName, "UpdatedAt")) {
                try {
                    String type = types.get(propertyName);
                    Component comp = components.get(index);
                    Object value = null;

                    if (type.equals("JComboBox")) {
                        JComboBox<?> comboBox = (JComboBox<?>) comp;
                        Object selectedItem = comboBox.getSelectedItem();
                        Method findMethod = findService.getClass().getMethod("find", String.class);
                        Optional<?> result = (Optional<?>) findMethod.invoke(findService, selectedItem);

                        if (result.isPresent()) {
                            Object relatedEntity = result.get();
                            Method getIdMethod = relatedEntity.getClass().getMethod("getId");
                            value = getIdMethod.invoke(relatedEntity);
                        } else {
                            throw new SQLException("Related entity not found for " + selectedItem.toString());
                        }
                    } else if (type.equals("JDateChooser")) {
                        JDateChooser dateJ = (JDateChooser) comp;
                        value = dateJ.getDate();
                    } else if (type.equals("TextField")) {
                        JTextField textField = (JTextField) comp;
                        value = textField.getText();
                    }

                    PropertyDescriptor pd = new PropertyDescriptor(propertyName, entity.getClass());
                    Method setter = pd.getWriteMethod();
                    setter.invoke(entity, value);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                index++;
            }
        }

        try {
            Method getter = selectedUpdate.get().getClass().getMethod("getId");
            int id = (int) getter.invoke(selectedUpdate.get());

            PropertyDescriptor pd = new PropertyDescriptor("Id", entity.getClass());
            Method setter = pd.getWriteMethod();
            setter.invoke(entity, id);

            Method updateMethod = updateService.getClass().getMethod("update", entity.getClass());
            updateMethod.invoke(updateService, entity);
            System.out.println("Entity updated: " + entity);

            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
