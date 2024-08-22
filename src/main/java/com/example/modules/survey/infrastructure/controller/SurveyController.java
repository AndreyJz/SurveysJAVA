package com.example.modules.survey.infrastructure.controller;

import com.example.modules.chapter.domain.entity.Chapter;
import com.example.modules.survey.application.*;
import com.example.modules.chapter.application.*;
import com.example.modules.question.application.*;
import com.example.modules.survey.domain.entity.Survey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyController extends JFrame implements ActionListener {
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private ListSurveysUC listSurveysUC;
    private ListChapterBySurveyIdUC listChapterBySurveyIdUC;
    private ListQuestionsByChapterIdUC listQuestionsByChapterIdUC;

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel chapter;
    private JButton backButton;
    private JButton nextButton;
    private JButton cancelButton;
    private JButton sendButton;
    private JPanel sectionPanel;
    private JButton toggleButton;
    private JComboBox<String> selectComboBox;
    private List<JPanel> chapterPanels;

    public SurveyController(ListSurveysUC ls, FindSurveyByNameUC fs, ListChapterBySurveyIdUC lc, ListQuestionsByChapterIdUC lq) {
        this.listSurveysUC = ls;
        this.findSurveyByNameUC = fs;
        this.listChapterBySurveyIdUC = lc;
        this.listQuestionsByChapterIdUC = lq;
    }

    public void fillSurvey() {
        setTitle("Fill Surveys");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void generateSurvey (int surveyId) {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setBounds(35, 60, 425, 470);

        chapter = new JPanel(new GridLayout(0, 1, 10, 10));
        chapter.setBackground(new Color(0x123456));
        List<Chapter> chapters = listChapterBySurveyIdUC.list(surveyId);
        chapters.forEach(chapter -> {
            String title = chapter.getChapterTitle();
            generateChapter(title,chapter.getId());
        });

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        cancelButton = new JButton("Exit");
        cancelButton.addActionListener(this);


        contentPanel.add(chapter);
        contentPanel.add(cancelButton);
        contentPanel.add(sendButton);

        contentPanel.setVisible(true);
        add(contentPanel);
    }

    public void generateChapter(String title,int chapterId) {
        JPanel content = new JPanel();

        // Create the header with the toggle button
        chapterPanels = new ArrayList<>();
        toggleButton = new JButton(title);

        // Add the header (button) to the top
        sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.add(toggleButton, BorderLayout.NORTH);
        sectionPanel.setBackground(new Color(0x123456));
        sectionPanel.add(content);
        sectionPanel.setVisible(true);

        chapter.add(sectionPanel);

        chapterPanels.add(content);

        generateInfo(content,chapterId);

        content.setVisible(false);
        toggleButton.addActionListener(new ToggleAction(content));

    }

    public void generateInfo(JPanel content, int chapterId) {
        listQuestionsByChapterIdUC.list(chapterId).forEach(question -> {
            JLabel questionLabel = new JLabel("Q" + question.getQuestionNumber() + ": " + question.getQuestionText());
            content.add(questionLabel);
            if (question.getResponseType().equals("radio")) {

                JRadioButton response = new JRadioButton();
                content.add(response);
            } else {
                JTextField response = new JTextField();
                content.add(response);
            }
        });
    }

    private class ToggleAction implements ActionListener {
        private JPanel content;

        public ToggleAction(JPanel content) {
            this.content = content;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isVisible = content.isVisible();
            content.setVisible(!isVisible);

            revalidate();
            repaint();
        }

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
        if (e.getSource() == cancelButton) {
            mainPanel.setVisible(true);
            contentPanel.setVisible(false);
        } else if (e.getSource() == nextButton) {
            mainPanel.setVisible(false);
            String selectedItem = (String) selectComboBox.getSelectedItem();
            Optional<Survey> survey = findSurveyByNameUC.find(selectedItem);
            generateSurvey(survey.get().getId());
        } else if (e.getSource() == backButton) {
            dispose();
        } else if (e.getSource() == sendButton) {
//            mainPanel.setVisible(false);
//            String selected = (String) selectComboBox.getSelectedItem();
//            try {
//                Method filterMethod = findServiceEntity.getClass().getMethod("find", String.class);
//                selectedUpdate = (Optional<?>) filterMethod.invoke(findServiceEntity, selected);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }

//            generateUpdateForm(selectedUpdate.get());
        }
    }
}
