package com.example.survey.infrastructure.controller;

import com.example.survey.application.FindSurveyByIdUC;
import com.example.survey.application.FindSurveyByNameUC;
import com.example.survey.application.ListSurveysUC;
import com.example.survey.domain.entity.Survey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class SurveyController {
    private FindSurveyByIdUC findSurveyByIdUC;
    private FindSurveyByNameUC findSurveyByNameUC;
    private ListSurveysUC listSurveysUC;

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
}
