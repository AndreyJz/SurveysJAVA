package com.example.survey.infrastructure.controller;

import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;
import com.example.survey.domain.entity.Survey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class SurveyController extends JFrame implements ActionListener {
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private ListSurveysUC listSurveysUC;

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JButton backButton;
    private JButton nextButton;
    private JButton cancelButton;
    private JButton sendButton;
    private JPanel sectionPanel;
    private JButton toggleButton;
    private JComboBox<String> selectComboBox;

    public void fillSurvey() {
        setTitle("Fill Surveys");
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

        JLabel selectLabel = new JLabel("Select the Survey");
        selectLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        selectLabel.setForeground(new Color(236, 224, 220));

        selectComboBox = new JComboBox<>();
        List<Survey> surveys = listSurveysUC.list();

        surveys.forEach(survey -> {
            selectComboBox.addItem(survey.getName());
        });

        nextButton = new JButton("Next ->");
        nextButton.addActionListener(this);
        backButton = new JButton("<- Go Back");
        backButton.addActionListener(this);

        mainPanel.add(selectLabel);
        mainPanel.add(selectComboBox);
        mainPanel.add(backButton);
        mainPanel.add(nextButton);

        mainPanel.setVisible(true);
        add(mainPanel);

        setVisible(true);
    }

    public Optional<Survey> FindSurveyByID() {
        try {
            int idCountry = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert The Code of The Survey you're looking for: "));
            Optional<Survey> survey = findSurveyByIdUC.find(idCountry);
            if (survey.isPresent()) {
                JOptionPane.showMessageDialog(null, "Survey founded:\nID: " + survey.get().getId() + "\nCrated At: " + survey.get().getCreatedAt() + "\nUpdated At: " + survey.get().getUpdatedAt() + "\nDescription: " + survey.get().getDescription() + "\nNombre: " + survey.get().getName(),
                        "Survey's info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Survey not funded", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return Optional.of(survey.get());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Please enter a valid type of data!", JOptionPane.ERROR_MESSAGE);
        }
        return Optional.empty();
    }

    private void generateSurvey () {
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
        contentPanel.setBounds(35, 60, 425, 470);

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        cancelButton = new JButton("Exit");
        cancelButton.addActionListener(this);



        contentPanel.add(cancelButton);
        contentPanel.add(sendButton);

        contentPanel.setVisible(true);
        add(contentPanel);
    }

    public void ExpandableSection(String title, JPanel content) {
        content.setLayout(new BorderLayout());

        // Create the header with the toggle button
        toggleButton = new JButton(title);
        toggleButton.addActionListener(new ToggleAction());

        // Add the header (button) to the top
        add(toggleButton, BorderLayout.NORTH);

        // Add the content panel (initially hidden)
        sectionPanel = content;
        sectionPanel.setVisible(false);
        add(sectionPanel, BorderLayout.CENTER);
    }

    private class ToggleAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isVisible = contentPanel.isVisible();
            contentPanel.setVisible(!isVisible);

            revalidate();
            repaint();
        }
    }

    public void generateChapter() {

    }

    public void getInfo(Survey survey) {
        survey.getId();
        survey.getName();
    }

    public List<Survey> ListSurveys() {
        List<Survey> surveys =  listSurveysUC.list();
        showSurveysTable(surveys);
        return surveys;
    }

    public static void showSurveysTable(List<Survey> surveys) {
        String[] columns = {"ID", "Created At", "Updated At", "Description", "Name"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        surveys.forEach(survey -> {
            Object[] row = {survey.getId(), survey.getCreatedAt(), survey.getUpdatedAt(), survey.getDescription(), survey.getName()};
            model.addRow(row);
        });

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scrollPane);

        JOptionPane.showMessageDialog(null, panel, "Surveys List", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
