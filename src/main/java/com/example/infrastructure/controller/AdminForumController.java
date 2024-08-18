package com.example.infrastructure.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AdminForumController extends JFrame implements ActionListener {
    private static final String CREATE = "Create";
    private static final String LIST = "List";
    private static final String SEARCH = "Search";
    private static final String UPDATE = "Update";
    private static final String DELETE = "Delete";
    private static final String EXIT = "Exit";


    private Map<String, JPanel> panels;
    private Map<String, JButton> buttons;

    private JPanel mainMenuPanel;
    private JPanel contentPanel;

    public AdminForumController() {
        initializeController();
    }

    private void initializeController() {
        setTitle("Forum Menu");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panels = new HashMap<>();
        buttons = new HashMap<>();

        setLayout(new BorderLayout());

        mainMenuPanel = new JPanel(new GridLayout(0,2,25,50));
        mainMenuPanel.setBounds(50,25,750,450);
        initializeMainPanel();
        add(mainMenuPanel);

        contentPanel = new JPanel(new CardLayout());
        initializeSubPanels();
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setVisible(false);

        setVisible(true);
    }

    private void initializeMainPanel() {
        String[] mainOptions = {"Survey", "City", "Region", "Laboratory", "Customer", "ModeAdministration", "ActivePrinciple", "UnitMeasurement", "Farmacy", "Medicine", "Farmacy-Medicine"};
        for (String option : mainOptions) {
            addButton(mainMenuPanel, option, this);
        }
    }

    private void initializeSubPanels() {
        String[] entities = {"Survey", "City", "Region", "Laboratory", "Customer", "ModeAdministration", "ActivePrinciple", "UnitMeasurement", "Farmacy", "Medicine", "Farmacy-Medicine"};
        for (String entity : entities) {
            JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
            addEntityButtons(panel, entity);
            panels.put(entity, panel);
            contentPanel.add(panel, entity);
        }
    }

    private void addEntityButtons(JPanel panel, String entity) {
        addButton(panel, CREATE + " " + entity, this);
        addButton(panel, LIST + " " + entity, this);
        addButton(panel, SEARCH + " " + entity, this);
        addButton(panel, UPDATE + " " + entity, this);
        addButton(panel, DELETE + " " + entity, this);
        addButton(panel, EXIT, this);
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(Color.LIGHT_GRAY);

        ImageIcon icon = new ImageIcon();

        if (text.startsWith(CREATE)) {
            icon = new ImageIcon(new ImageIcon("src/images/create-svgrepo-com.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.startsWith(LIST)) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/create-svgrepo-com (2).svg").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.startsWith(SEARCH)) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/find-on-map-svgrepo-com.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.startsWith(UPDATE)) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/create-svgrepo-com.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.startsWith(DELETE)) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/icons8-basura.svg").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Survey")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/country-5.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("City")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/71-716950_the-night-sky-of-the-city-night-city.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Region")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/3056109.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Laboratory")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/clinical-laboratory.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Customer")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/381-3811230_client-people-business-customer-person-client-png-clipart.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("ModeAdministration")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/pngtree-capsule-and-pills-icon-picture-image_7988303.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("ActivePrinciple")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/9593134.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("UnitMeasurement")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/1589247.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.startsWith("Farmacy-Medicine")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/doctor-medical-mk-logo-A197A7A78F-seeklogo.com.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Farmacy")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/4899417.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Medicine")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/4599153.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        }

        button.setIcon(icon);
        button.addActionListener(listener);
        panel.add(button);
        buttons.put(text, button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
