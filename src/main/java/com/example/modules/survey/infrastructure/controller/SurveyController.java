package com.example.modules.survey.infrastructure.controller;

import com.example.UI.infrastructure.controller.*;
import com.example.modules.categoriescatalog.domain.entity.CategoriesCatalog;
import com.example.modules.chapter.domain.entity.Chapter;
import com.example.modules.responseoptions.application.ListResponseOptionsByParentIdUC;
import com.example.modules.responseoptions.application.ListResponseOptionsByQuestionIdUC;
import com.example.modules.responseoptions.domain.entity.ResponseOptions;
import com.example.modules.subresponseoptions.application.ListSubresponseOptionsByResponseOptionsIdUC;
import com.example.modules.subresponseoptions.domain.entity.SubresponseOptions;
import com.example.modules.survey.application.*;
import com.example.modules.chapter.application.*;
import com.example.modules.question.application.*;
import com.example.modules.survey.domain.entity.Survey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SurveyController extends JFrame implements ActionListener {
    private CreateController createController;
    private UpdateController updateController;
    private DeleteController deleteController;
    private ListController listController;
    private SearchController searchController;
    private CreateSurveyUC createSurveyUC;
    private UpdateSurveyUC updateSurveyUC;
    private DeleteSurveyUC deleteSurveyUC;
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private ListSurveysUC listSurveysUC;
    private ListChapterBySurveyIdUC listChapterBySurveyIdUC;
    private ListQuestionsByChapterIdUC listQuestionsByChapterIdUC;
    private ListResponseOptionsByQuestionIdUC listResponseOptionsByQuestionIdUC;
    private ListResponseOptionsByParentIdUC listResponseOptionsByParentIdUC;
    private ListSubresponseOptionsByResponseOptionsIdUC listSubresponseOptionsByResponseOptionsIdUC;

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
    Map<String, List<Object>> mapOfList;

    public SurveyController(ListSurveysUC listSurveysUC) {
        this.listSurveysUC = listSurveysUC;
    }

    public SurveyController(FindSurveyByIdUC findSurveyByIdUC) {
        this.findSurveyByIdUC = findSurveyByIdUC;
    }

    public SurveyController(CreateSurveyUC createSurveyUC) {
        this.createSurveyUC = createSurveyUC;
    }

    public SurveyController(UpdateSurveyUC updateSurveyUC, ListSurveysUC listSurveysUC, FindSurveyByNameUC findSurveyByNameUC, FindSurveyByIdUC findSurveyByIdUC) {
        this.updateSurveyUC = updateSurveyUC;
        this.listSurveysUC = listSurveysUC;
        this.findSurveyByNameUC = findSurveyByNameUC;
        this.findSurveyByIdUC = findSurveyByIdUC;
    }

    public SurveyController(DeleteSurveyUC deleteSurveyUC, ListSurveysUC listSurveysUC, FindSurveyByNameUC findSurveyByNameUC) {
        this.deleteSurveyUC = deleteSurveyUC;
        this.listSurveysUC = listSurveysUC;
        this.findSurveyByNameUC = findSurveyByNameUC;
    }

    public SurveyController(ListSurveysUC ls, FindSurveyByNameUC fs, ListChapterBySurveyIdUC lc, ListQuestionsByChapterIdUC lq, ListResponseOptionsByQuestionIdUC lro, ListResponseOptionsByParentIdUC lrp, ListSubresponseOptionsByResponseOptionsIdUC lsr) {
        this.listSurveysUC = ls;
        this.findSurveyByNameUC = fs;
        this.listChapterBySurveyIdUC = lc;
        this.listQuestionsByChapterIdUC = lq;
        this.listResponseOptionsByQuestionIdUC = lro;
        this.listResponseOptionsByParentIdUC = lrp;
        this.listSubresponseOptionsByResponseOptionsIdUC = lsr;
    }

    public void createSurvey() {
        Survey survey = new Survey();
        mapOfList = new LinkedHashMap<>();
        this.createController = new CreateController(survey, createSurveyUC, mapOfList);
    }

    public void updateSurvey() {
        Survey survey = new Survey();
        mapOfList = new LinkedHashMap<>();
        this.updateController = new UpdateController(survey, updateSurveyUC, listSurveysUC, findSurveyByNameUC, mapOfList);
    }

    public void findSurveyByID() {
        Survey survey = new Survey();
        this.searchController = new SearchController(survey, findSurveyByIdUC);
    }

    public void listSurveys() {
        Survey survey = new Survey();
        this.listController = new ListController(survey, listSurveysUC);
    }

    public void deleteSurvey() {
        Survey survey = new Survey();
        this.deleteController = new DeleteController(survey, deleteSurveyUC, listSurveysUC, findSurveyByNameUC);
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

    private void generateSurvey (int surveyId) {
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(0x123456));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBounds(25, 40, 450, 600);

        chapter = new JPanel();
        chapter.setLayout(new BoxLayout(chapter, BoxLayout.Y_AXIS));
        chapter.setBackground(new Color(0x123456));
        List<Chapter> chapters = listChapterBySurveyIdUC.list(surveyId);
        chapters.forEach(chapter -> {
            String title = chapter.getChapterTitle();
            generateChapter(title,chapter.getId());
        });

        contentPanel.add(chapter, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(chapter);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x123456));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        cancelButton = new JButton("Exit");
        cancelButton.addActionListener(this);

        buttonPanel.add(sendButton);
        buttonPanel.add(cancelButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.setVisible(true);
        add(contentPanel);
    }

    public void generateChapter(String title,int chapterId) {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        content.setBackground(new Color(0x123456));

        // Create the header with the toggle button
        chapterPanels = new ArrayList<>();
        toggleButton = new JButton(title);

        // Add the header (button) to the top
        sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.add(toggleButton, BorderLayout.NORTH);
        sectionPanel.setBackground(new Color(0x123456));
        sectionPanel.add(content);
        sectionPanel.setVisible(true);

        content.setBounds(sectionPanel.getX(), sectionPanel.getY(), sectionPanel.getWidth(), sectionPanel.getHeight());
        chapter.add(sectionPanel);

        chapterPanels.add(content);

        generateQuestion(content,chapterId,gbc);

        content.setVisible(true);
        toggleButton.addActionListener(new ToggleAction(content));

    }

    public void generateQuestion(JPanel content, int chapterId, GridBagConstraints gbc) {
        AtomicInteger row = new AtomicInteger(0);
        listQuestionsByChapterIdUC.list(chapterId).forEach(question -> {
            JLabel questionLabel = new JLabel("Q" + question.getQuestionNumber() + ": " + question.getQuestionText());
            questionLabel.setForeground(new Color(236, 224, 220));
            gbc.gridy = row.getAndIncrement();
            gbc.gridx = 1;
            content.add(questionLabel, gbc);

            if (question.getResponseType().equals("radio") || question.getResponseType().equals("checkbox")) {
                ButtonGroup group = new ButtonGroup();

                listResponseOptionsByQuestionIdUC.list(question.getId()).forEach(responseOption -> {
                    if (responseOption.getParentResponseId() == 0) {
                        JRadioButton response = new JRadioButton(responseOption.getOptionText() + ".");
                        response.setBackground(new Color(0x123456));
                        response.setForeground(new Color(236, 224, 220));
                        gbc.gridy = row.getAndIncrement();
                        gbc.gridx = 1;
                        group.add(response);
                        content.add(response, gbc);

//                        List<ResponseOptions> responseOptionsSons = listResponseOptionsByParentIdUC.list(responseOption.getParentResponseId());
                        List<JRadioButton> responseOptionsSons = new ArrayList<>();
                        ButtonGroup group2 = new ButtonGroup();
                        listResponseOptionsByParentIdUC.list(responseOption.getId()).forEach(responseOption2 -> {
                            if (responseOption2.getTypeComponentHtml().equals("radio") || responseOption2.getTypeComponentHtml().equals("checkbox")) {
                                System.out.println(responseOption2.getOptionText());
                                JRadioButton response2 = new JRadioButton(responseOption2.getOptionText() + ".");
                                response2.setVisible(false);
                                response2.setBackground(new Color(0x123456));
                                response2.setForeground(new Color(236, 224, 220));
                                gbc.gridy = row.getAndIncrement();
                                gbc.gridx = 2;
                                content.add(response2, gbc);
                                responseOptionsSons.add(response2);
                                group2.add(response2);

                                ButtonGroup group3 = new ButtonGroup();
                                List<JRadioButton> subResponseButtons = new ArrayList<>();
                                List<SubresponseOptions> subResponseOptions = listSubresponseOptionsByResponseOptionsIdUC.list(responseOption2.getId());
                                for (SubresponseOptions subResponseOption : subResponseOptions) {
                                    System.out.println(subResponseOption.getSubresponseText());
                                    JRadioButton response3 = new JRadioButton(subResponseOption.getSubresponseText() + ".");
                                    response3.setVisible(false);
                                    response3.setBackground(new Color(0x123456));
                                    response3.setForeground(new Color(236, 224, 220));
                                    gbc.gridy = row.getAndIncrement();
                                    gbc.gridx = 3;
                                    content.add(response3, gbc);
                                    subResponseButtons.add(response3);
                                    group3.add(response3);
                                }

                                response2.addItemListener(e -> {
                                    boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                    for (JRadioButton subOptionButton : subResponseButtons) {
                                        subOptionButton.setVisible(selected);
                                        group3.clearSelection();
                                    }
                                });

                                response.addItemListener(e -> {
                                    boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                                    for (JRadioButton responseOptionsSon : responseOptionsSons) {
                                        responseOptionsSon.setVisible(selected);
                                        group2.clearSelection();
                                    }
                                });

                            } else {
                                JLabel responseLabel = new JLabel(responseOption2.getOptionText() + ".");
                                responseLabel.setForeground(new Color(236, 224, 220));
                                responseLabel.setBackground(new Color(0x123456));
                                JTextField response2 = new JTextField();
                                response2.setForeground(new Color(236, 224, 220));
                            }
                        });

                        List<JRadioButton> subResponseButtons = new ArrayList<>();
                        List<SubresponseOptions> subresponseOptions = listSubresponseOptionsByResponseOptionsIdUC.list(responseOption.getId());
                        for (SubresponseOptions subResponseOption : subresponseOptions) {
                            JRadioButton response2 = new JRadioButton(subResponseOption.getSubresponseText() + ".");
                            response2.setVisible(false);
                            response2.setBackground(new Color(0x123456));
                            response2.setForeground(new Color(236, 224, 220));
                            gbc.gridy = row.getAndIncrement();
                            gbc.gridx = 2;
                            group2.add(response2);
                            subResponseButtons.add(response2);
                            content.add(response2, gbc);
                        }

                        response.addItemListener(e -> {
                            boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                            for (JRadioButton subOptionButton : subResponseButtons) {
                                subOptionButton.setVisible(selected);
                                group2.clearSelection();
                            }
                        });
                    }
                });
            } else {
                JTextField response = new JTextField();
                gbc.gridy = row.getAndIncrement();
                gbc.gridx = 1;
                content.add(response, gbc);
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
