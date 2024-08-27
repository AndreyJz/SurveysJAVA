package com.example.modules.survey_archive.infrastructure.controller;

import com.example.UI.infrastructure.controller.ListController;
import com.example.modules.responsequestions.domain.entity.ResponseQuestion;
import com.example.modules.survey_archive.application.CreateSurvey_ArchiveUC;
import com.example.modules.survey_archive.application.FindSurvey_ArchiveByIdUC;
import com.example.modules.survey_archive.application.ListSurveys_ArchiveUC;
import com.example.modules.survey_archive.domain.entity.Survey_Archive;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Survey_ArchiveController extends JFrame {
    private CreateSurvey_ArchiveUC createSurvey_ArchiveUC;
    private FindSurvey_ArchiveByIdUC findSurvey_ArchiveByIdUC;
    private ListSurveys_ArchiveUC listSurveys_ArchiveUC;

    public Survey_ArchiveController(ListSurveys_ArchiveUC listSurveys_ArchiveUC) {
        this.listSurveys_ArchiveUC = listSurveys_ArchiveUC;
    }

    public Survey_ArchiveController(CreateSurvey_ArchiveUC createSurvey_ArchiveUC) {
        this.createSurvey_ArchiveUC = createSurvey_ArchiveUC;
    }

    public void listSurveys_Archive() {
        setTitle("List of Surveys_Archives");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x123456));
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);


        showItemsTable(listSurveys_ArchiveUC.list());

    }

    private void showItemsTable(List<Survey_Archive> surveyArchives) {
        Vector<String> columns = new Vector<>();
        columns.add("ID");
        columns.add("CreatedAt");
        columns.add("SurveyID");

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        int surveyID = 0;

        for (Survey_Archive surveyArchive : surveyArchives) {
            List<Object> row = new ArrayList<>();
            surveyID = surveyArchive.getId();
            row.add(surveyID);
            row.add(surveyArchive.getCreatedAt());
            row.add(surveyArchive.getSurveyId());
            model.addRow(row.toArray());
        }

        JLabel title = new JLabel("Surveys_Archives");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setForeground(new Color(236, 224, 220));
        title.setBounds(250, 23, 300, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = surveyArchives.size();
        panel.add(scrollPane, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1;
        for (int i = 0; i < surveyArchives.size(); i++) {
            int archiveId = surveyArchives.get(i).getId();
            JButton button = new JButton("Responses of " + archiveId);
            gbc.gridy = i;
            panel.add(button, gbc);

            int finalI = i;
            button.addActionListener(e -> {
                Survey_Archive surveyArchive = surveyArchives.get(finalI);
                JOptionPane.showMessageDialog(panel, formatPayload(surveyArchive.getPayload()));
            });
        }

        JLabel label = new JLabel("Surveys_Archives");
        label.setFont(new Font("Calibri", Font.BOLD, 35));
        label.setForeground(new Color(236, 224, 220));
        label.setBounds(250, 23, 300, 50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        panel.setBounds(45, 80, 700, 450);
        add(panel);

        setVisible(true);
    }

    private String formatPayload(String payload) {
        // Assuming the payload uses semicolons to separate parts
        String[] parts = payload.split(",");

        // Join the parts with a newline for better readability
        return String.join("\n", parts);
    }

    public void createSurvey_Archive(int surveyID, Map<Integer, List<String>> responsesOP, Map<Integer,List<String>> subResponses, Map<Integer,List<String>> parentResponses) {
        List<String> payload = new ArrayList<String>();
        responsesOP.forEach((id, info) -> {
            final String[] payloadString = {"QUESTION_ID: " + id + ", RESPONSE: " + info.get(1)};
            parentResponses.forEach((parentId, parentInfo) -> {
                if (parentId.equals(Integer.parseInt(info.get(0)))) {
                    subResponses.forEach((resId, subinfo) -> {
                        if (resId.equals(Integer.parseInt(parentInfo.get(0)))) {
                            payloadString[0] = ("QUESTION_ID: " + id + ", PARENT_RES: " + info.get(1) + " -- RESPONSE: " + parentInfo.get(1) + " -- SUB: " + subinfo.get(1));
                        }
                    });
                }
            });
            subResponses.forEach((resId, subinfo) -> {
                if (resId.equals(Integer.parseInt(info.get(0)))) {
                    payloadString[0] += " -- SUB: " + subinfo.get(1);
                }
            });
            payload.add(payloadString[0]);
            System.out.println(payload.toString());
        });
        Survey_Archive survey_archive = new Survey_Archive();
        System.out.println(surveyID);
        survey_archive.setSurveyId(surveyID);
        survey_archive.setPayload(payload.toString());
        System.out.println(survey_archive.toString());
        createSurvey_ArchiveUC.create(survey_archive);
    }
}
