package com.example.UI.infrastructure.controller;

import com.example.modules.categoriescatalog.application.CreateCategoriesCatalogUC;
import com.example.modules.categoriescatalog.application.FindCategoriesCatalogByNameUC;
import com.example.modules.categoriescatalog.application.ListCategoriesCatalogsUC;
import com.example.modules.chapter.application.*;
import com.example.modules.chapter.domain.service.ChapterService;
import com.example.modules.chapter.infrastructure.controller.ChapterController;
import com.example.modules.chapter.infrastructure.repository.ChapterRepository;
import com.example.modules.question.application.CreateQuestionUC;
import com.example.modules.question.application.FindQuestionByNameUC;
import com.example.modules.question.application.ListQuestionsUC;
import com.example.modules.question.domain.service.QuestionService;
import com.example.modules.question.infrastructure.controller.QuestionController;
import com.example.modules.question.infrastructure.repository.QuestionRepository;
import com.example.modules.responseoptions.application.CreateResponseOptionsUC;
import com.example.modules.responseoptions.application.DeleteResponseOptionsUC;
import com.example.modules.responseoptions.application.FindResponseOptionsByNameUC;
import com.example.modules.responseoptions.application.ListResponseOptionsUC;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;
import com.example.modules.responseoptions.infrastructure.controller.ResponseOptionsController;
import com.example.modules.responseoptions.infrastructure.repository.ResponseOptionsRepository;
import com.example.modules.survey.application.FindSurveyByIdUC;
import com.example.modules.survey.application.FindSurveyByNameUC;
import com.example.modules.survey.application.ListSurveysUC;
import com.example.modules.survey.domain.service.SurveySercive;
import com.example.modules.survey.infrastructure.repository.SurveyRepository;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;
import com.example.modules.categoriescatalog.infrastructure.controller.CategoriesCatalogController;
import com.example.modules.categoriescatalog.infrastructure.repository.CategoriesCatalogRepository;

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
        String[] mainOptions = {"Survey", "Chapter", "Question", "CategoriesCatalog", "ResponseOptions", "ModeAdministration", "ActivePrinciple", "UnitMeasurement", "Farmacy", "Medicine", "Farmacy-Medicine"};
        for (String option : mainOptions) {
            addButton(mainMenuPanel, option, this);
        }
    }

    private void initializeSubPanels() {
        String[] entities = {"Survey", "Chapter", "Question", "CategoriesCatalog", "ResponseOptions", "ModeAdministration", "ActivePrinciple", "UnitMeasurement", "Farmacy", "Medicine", "Farmacy-Medicine"};
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
        } else if (text.contains("Chapter")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/71-716950_the-night-sky-of-the-city-night-city.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("Question")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/3056109.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("CategoriesCatalog")) {
            icon = new ImageIcon(new ImageIcon("src/main/resources/images/clinical-laboratory.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        } else if (text.contains("ResponseOptions")) {
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
        String command = e.getActionCommand();
        if (panels.containsKey(command)) {
            showPanel(command);
        } else if (command.startsWith(CREATE)) {
            handleCreate(command.split(" ")[1]);
        } else if (command.startsWith(LIST)) {
            handleList(command.split(" ")[1]);
        } else if (command.startsWith(SEARCH)) {
            handleSearch(command.split(" ")[1]);
        } else if (command.startsWith(UPDATE)) {
            handleUpdate(command.split(" ")[1]);
        } else if (command.startsWith(DELETE)) {
            handleDelete(command.split(" ")[1]);
        } else if (command.equals(EXIT)) {
            showPanel("MAIN");  // Volver al panel principal en lugar de salir
            contentPanel.setVisible(false);
            mainMenuPanel.setVisible(true);
        }
    }

    private void showPanel(String panelName) {
        contentPanel.setVisible(true);
        mainMenuPanel.setVisible(false);
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
        System.out.println("Showing panel: " + panelName);  // Depuración
    }

    QuestionService qs = new QuestionRepository();
    ChapterService cs = new ChapterRepository();
    SurveySercive ss = new SurveyRepository();
    CategoriesCatalogService ccs = new CategoriesCatalogRepository();
    ResponseOptionsService rs = new ResponseOptionsRepository();
//    UnitMeasurementService us = new UnitMeasurementRepository();
//    ActivePrincipleService as = new ActivePrincipleRepository();
//    ModeadministrationService ms = new ModeAdministrationRepository();
//    FarmacyService fs = new FarmacyRepository();
//    ResponseOptionsService ccss = new ResponseOptionsRepository();
//    MedicineService mss = new MedicineRepository();
//    FarmacyMedicineService fms = new FarmacyMedicineRepository();

    private void handleCreate(String entity) {
        if (entity.equals("Survey")) {
//            CreateSurveyUC cc = new CreateSurveyUC(ccs);
//            SurveyController ccc = new SurveyController(cc);
//            ccc.CreateSurvey();
        } else if (entity.equals("Chapter")) {
            CreateChapterUC cc = new CreateChapterUC(cs);
            ListSurveysUC ls = new ListSurveysUC(ss);
            FindSurveyByNameUC fsn = new FindSurveyByNameUC(ss);
            ChapterController ccc = new ChapterController(cc,ls,fsn);
            ccc.createChapter();
        } else if (entity.equals("Question")) {
            CreateQuestionUC uq = new CreateQuestionUC(qs);
            ListChaptersUC lc = new ListChaptersUC(cs);
            FindChapterByNameUC fc = new FindChapterByNameUC(cs);
            QuestionController c = new QuestionController(uq,lc,fc);
            c.createQuestion();
        } else if (entity.equals("CategoriesCatalog")) {
            CreateCategoriesCatalogUC ccat = new CreateCategoriesCatalogUC(ccs);
            CategoriesCatalogController ccc = new CategoriesCatalogController(ccat);
            ccc.createCategoriesCatalog();
//            CreateCategoriesCatalogUC cl = new CreateCategoriesCatalogUC(ls);
//            FindCitiesUC fc = new FindCitiesUC(cs);
//            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
//            CategoriesCatalogController lc = new CategoriesCatalogController(cl, fc, fcn);
//            lc.createCategoriesCatalog();
        } else if (entity.equals("ResponseOptions")) {
            CreateResponseOptionsUC cr = new CreateResponseOptionsUC(rs);
            ListCategoriesCatalogsUC lc = new ListCategoriesCatalogsUC(ccs);
            FindCategoriesCatalogByNameUC fcn = new FindCategoriesCatalogByNameUC(ccs);
            ListResponseOptionsUC lr = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fr = new FindResponseOptionsByNameUC(rs);
            ListQuestionsUC lq = new ListQuestionsUC(qs);
            FindQuestionByNameUC fq = new FindQuestionByNameUC(qs);
            ResponseOptionsController c = new ResponseOptionsController(cr,lc,fcn,lr,fr,lq,fq);
            c.createResponseOptions();
        } else if (entity.equals("ModeAdministration")) {
//            CreateModeadministrationUC cm = new CreateModeadministrationUC(ms);
//            ModeAdministrationController mc = new ModeAdministrationController(cm);
//            mc.createModeAdministration();
        } else if (entity.equals("ActivePrinciple")) {
//            CreateActivePrincipleUC ca = new CreateActivePrincipleUC(as);
//            ActivePrincipleController ac = new ActivePrincipleController(ca);
//            ac.CreateActivePrinciple();
        } else if (entity.equals("UnitMeasurement")) {
//            UnitMeasurementService us = new UnitMeasurementRepository();
//            CreateUnitMeasurementUC cu = new CreateUnitMeasurementUC(us);
//            UnitMeasurementController uc = new UnitMeasurementController(cu);
//            uc.createUnitMeasurement();
        } else if (entity.equals("Farmacy")) {
//            CreateFarmacyUC cf = new CreateFarmacyUC(fs);
//            FindCitiesUC fc = new FindCitiesUC(cs);
//            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
//            FarmacyController c = new FarmacyController(cf,fc,fcn);
//            c.createFarmacy();
        } else if (entity.equals("Medicine")) {
//            CreateMedicineUC mc = new CreateMedicineUC(mss);
//            ListModeadministrationsUC lm = new ListModeadministrationsUC(ms);
//            ListUnitMeasurementsUC lu = new ListUnitMeasurementsUC(us);
//            FindActivePrinciplesUC fp = new FindActivePrinciplesUC(as);
//            FindLaboratoriesUC fl = new FindLaboratoriesUC(ls);
//            FindModeadministrationByNameUC fmn = new FindModeadministrationByNameUC(ms);
//            FindUnitMeasurementByNameUC fumn = new FindUnitMeasurementByNameUC(us);
//            FindActivePrincipleByNameUC fapn = new FindActivePrincipleByNameUC(as);
//            FindLaboratoryByNameUC fln = new FindLaboratoryByNameUC(ls);
//            MedicineController c = new MedicineController(mc,lm,lu,fp,fl,fmn,fumn,fapn,fln);
//            c.createMedicine();
        } else if (entity.equals("Farmacy-Medicine")) {
//            CreateFarmacyMedicineUC cfm = new CreateFarmacyMedicineUC(fms);
//            ListMedicinesUC lm = new ListMedicinesUC(mss);
//            ListAllFarmaciesUC lf = new ListAllFarmaciesUC(fs);
//            FindMedicineByNameUC fmn = new FindMedicineByNameUC(mss);
//            FindFarmacyByNameUC ffn = new FindFarmacyByNameUC(fs);
//            FarmacyMedicineController c = new FarmacyMedicineController(cfm,lm,lf,fmn,ffn);
//            c.createFarmacyMedicine();
        }
    }

    private void handleList(String entity) {
        if (entity.equals("Survey")) {
//            ListAllCountriesUC fcsuc = new ListAllCountriesUC(ss);
//            SurveyController uiSurvey = new SurveyController(fcsuc);
//            uiSurvey.ListCountries();
        } else if (entity.equals("Chapter")) {
            ListChaptersUC lc = new ListChaptersUC(cs);
            ChapterController cc = new ChapterController(lc);
            cc.listChapters();
        } else if (entity.equals("Question")) {
            ListQuestionsUC fnsuc = new ListQuestionsUC(qs);
//            QuestionController uiQuestion = new QuestionController(fnsuc);
//            uiQuestion.ListQuestions();
        } else if (entity.equals("Laboratory")) {
//            FindLaboratoriesUC fl = new FindLaboratoriesUC(ls);
//            LaboratoryController lc = new LaboratoryController(fl);
//            lc.ListLaboratories();
        } else if (entity.equals("ResponseOptions")) {
//            ListAllResponseOptionssUC fcsuc = new ListAllResponseOptionssUC(ccss);
//            ResponseOptionsController c = new ResponseOptionsController(fcsuc);
//            c.ListResponseOptionss();
        } else if (entity.equals("ModeAdministration")) {
//            ModeadministrationService ms = new ModeAdministrationRepository();
//            ListModeadministrationsUC fcsuc = new ListModeadministrationsUC(ms);
//            ModeAdministrationController uiSurvey = new ModeAdministrationController(fcsuc);
//            uiSurvey.listModeAdministrations();
        } else if (entity.equals("ActivePrinciple")) {
//            FindActivePrinciplesUC fa = new FindActivePrinciplesUC(as);
//            ActivePrincipleController ac = new ActivePrincipleController(fa);
//            ac.ListActivePrinciples();
        } else if (entity.equals("UnitMeasurement")) {
//            ListUnitMeasurementsUC lu = new ListUnitMeasurementsUC(us);
//            UnitMeasurementController uc = new UnitMeasurementController(lu);
//            uc.ListUnitMeasurement();
        } else if (entity.equals("Farmacy")) {
//            ListAllFarmaciesUC fcsuc = new ListAllFarmaciesUC(fs);
//            FarmacyController c = new FarmacyController(fcsuc);
//            c.ListFarmacies();
        } else if (entity.equals("Medicine")) {
//            ListMedicinesUC lm = new ListMedicinesUC(mss);
//            MedicineController mc = new MedicineController(lm);
//            mc.ListMedicines();
        } else if (entity.equals("Farmacy-Medicine")) {
//            ListFarmacyMedicinesUC lfm = new ListFarmacyMedicinesUC(fms);
//            FarmacyMedicineController c = new FarmacyMedicineController(lfm);
//            c.ListFarmacyMedicines();
        }
    }

    private void handleSearch(String entity) {
        if (entity.equals("Survey")) {
//            FindSurveyByIdUC fcsuc = new FindSurveyByIdUC(ss);
//            SurveyController uiSurvey = new SurveyController(fcsuc);
//            uiSurvey.FindSurveyByID();
        } else if (entity.equals("Chapter")) {
            FindChapterByIdUC fci = new FindChapterByIdUC(cs);
            ChapterController cc = new ChapterController(fci);
            cc.findChapterById();
        } else if (entity.equals("Question")) {
//            FindQuestionByIdDC fcsuc = new FindQuestionByIdDC(qs);
//            QuestionController uiQuestion = new QuestionController(fcsuc);
//            uiQuestion.FindQuestionByID();
        } else if (entity.equals("Laboratory")) {
//            FindLaboratoryByIdUC fli = new FindLaboratoryByIdUC(ls);
//            LaboratoryController lc = new LaboratoryController(fli);
//            lc.FindLaboratoryByID();
        } else if (entity.equals("ResponseOptions")) {
//            FindResponseOptionssByIdUC fcuc = new FindResponseOptionssByIdUC(ccss);
//            ResponseOptionsController c = new ResponseOptionsController(fcuc);
//            c.FindResponseOptionsByID();
        } else if (entity.equals("ModeAdministration")) {
//            ModeadministrationService ms = new ModeAdministrationRepository();
//            FindModeadministrationByIdUC fcsuc = new FindModeadministrationByIdUC(ms);
//            ModeAdministrationController uiSurvey = new ModeAdministrationController(fcsuc);
//            uiSurvey.findModeAdministrationById();
        } else if (entity.equals("ActivePrinciple")) {
//            FindActivePrincipleByIdUC fai = new FindActivePrincipleByIdUC(as);
//            ActivePrincipleController ac = new ActivePrincipleController(fai);
//            ac.FindActivePrincipleByID();
        } else if (entity.equals("UnitMeasurement")) {
//            FindUnitMeasurementByIdUC fu = new FindUnitMeasurementByIdUC(us);
//            UnitMeasurementController uc = new UnitMeasurementController(fu);
//            uc.findUnitMeasurementById();
        } else if (entity.equals("Farmacy")) {
//            FindFarmaciesByIdUC fcuc = new FindFarmaciesByIdUC(fs);
//            FarmacyController c = new FarmacyController(fcuc);
//            c.FindFarmacyByID();
        } else if (entity.equals("Medicine")) {
//            FindMedicineByIdUC fmi = new FindMedicineByIdUC(mss);
//            MedicineController c = new MedicineController(fmi);
//            c.FindMedicineByID();
        } else if (entity.equals("Farmacy-Medicine")) {
//            FindFarmacyMedicineByIdUC ffmi = new FindFarmacyMedicineByIdUC(fms);
//            FarmacyMedicineController c = new FarmacyMedicineController(ffmi);
//            c.FindFarmacyMedicineByID();
        }
    }

    private void handleUpdate(String entity) {
        if (entity.equals("Survey")) {
//            ListAllCountriesUC fcsuc = new ListAllCountriesUC(ss);
//            FindSurveyByNameUC fciduc = new FindSurveyByNameUC(ss);
//            UpdateSurveyUC ucuc = new UpdateSurveyUC(ss);
//            SurveyController uiSurvey = new SurveyController(ucuc, fcsuc, fciduc);
//            uiSurvey.UpdateSurvey();
        } else if (entity.equals("Chapter")) {
            ChapterService cs = new ChapterRepository();
            SurveySercive ss = new SurveyRepository();
            UpdateChapterUC uc = new UpdateChapterUC(cs);
            ListChaptersUC lc = new ListChaptersUC(cs);
            ListSurveysUC ls = new ListSurveysUC(ss);
            FindChapterByNameUC fc = new FindChapterByNameUC(cs);
            FindSurveyByIdUC fsi = new FindSurveyByIdUC(ss);
            FindSurveyByNameUC fs = new FindSurveyByNameUC(ss);
            ChapterController c = new ChapterController(uc, lc, ls, fc, fsi, fs);
            c.updateChapter();
        } else if (entity.equals("Question")) {
//            UpdateQuestionUC uc = new UpdateQuestionUC(qs);
//            FindSurveyByNameUC fc = new FindSurveyByNameUC(ss);
//            ListAllCountriesUC lc = new ListAllCountriesUC(ss);
//            FindSurveyByIdUC fcc = new FindSurveyByIdUC(ss);
//            FindQuestionByNameUC fr = new FindQuestionByNameUC(qs);
//            ListQuestionsUC lac = new ListQuestionsUC(qs);
//            QuestionController c = new QuestionController(uc,fr,lac,fc,lc);
//            c.updateQuestion();
        } else if (entity.equals("Laboratory")) {
//            UpdateLaboratoryUC uc = new UpdateLaboratoryUC(ls);
//            FindLaboratoriesUC fl = new FindLaboratoriesUC(ls);
//            FindLaboratoryByNameUC fln = new FindLaboratoryByNameUC(ls);
//            FindCitiesUC fc = new FindCitiesUC(cs);
//            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
//            LaboratoryController lc = new LaboratoryController(uc,fl,fln,fc,fcn);
//            lc.updateLaboratory();
        } else if (entity.equals("ResponseOptions")) {
//            UpdateResponseOptionsUc cc = new UpdateResponseOptionsUc(ccss);
//            ListAllResponseOptionssUC lc = new ListAllResponseOptionssUC(ccss);
//            FindResponseOptionsByNameUC fccn = new FindResponseOptionsByNameUC(ccss);
//            FindCitiesUC fc = new FindCitiesUC(cs);
//            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
//            FindChapterByIdUC fcnd = new FindChapterByIdUC(cs);
//            ResponseOptionsController c = new ResponseOptionsController(cc,lc,fccn,fc,fcn,fcnd);
//            c.updateResponseOptions();
        } else if (entity.equals("ModeAdministration")) {
//            ListModeadministrationsUC fcsuc = new ListModeadministrationsUC(ms);
//            FindModeadministrationByNameUC fciduc = new FindModeadministrationByNameUC(ms);
//            UpdateModeadministrationUC ucuc = new UpdateModeadministrationUC(ms);
//            ModeAdministrationController uiSurvey = new ModeAdministrationController(ucuc, fciduc, fcsuc);
//            uiSurvey.updateModeAdministration();
        } else if (entity.equals("ActivePrinciple")) {
//            UpdateActivePrincipleUC ua = new UpdateActivePrincipleUC(as);
//            FindActivePrinciplesUC fa = new FindActivePrinciplesUC(as);
//            FindActivePrincipleByNameUC fan = new FindActivePrincipleByNameUC(as);
//            ActivePrincipleController ac = new ActivePrincipleController(ua,fa,fan);
//            ac.UpdateActivePrinciple();
        } else if (entity.equals("UnitMeasurement")) {
//            UpdateUnitMeasurementUC du = new UpdateUnitMeasurementUC(us);
//            ListUnitMeasurementsUC lu = new ListUnitMeasurementsUC(us);
//            FindUnitMeasurementByNameUC fu = new FindUnitMeasurementByNameUC(us);
//            UnitMeasurementController uc = new UnitMeasurementController(du,lu,fu);
//            uc.updateUnitMeasurement();
        } else if (entity.equals("Farmacy")) {
//            UpdateFarmacyUC uf = new UpdateFarmacyUC(fs);
//            ListAllFarmaciesUC lf = new ListAllFarmaciesUC(fs);
//            FindFarmacyByNameUC ffn = new FindFarmacyByNameUC(fs);
//            FindCitiesUC fc = new FindCitiesUC(cs);
//            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
//            FindChapterByIdUC fcnd = new FindChapterByIdUC(cs);
//            FarmacyController c = new FarmacyController(uf,lf,ffn,fc,fcn,fcnd);
//            c.updateFarmacy();
        } else if (entity.equals("Medicine")) {
//            UpdateMedicineUC um = new UpdateMedicineUC(mss);
//            ListMedicinesUC lmm = new ListMedicinesUC(mss);
//            ListModeadministrationsUC lm = new ListModeadministrationsUC(ms);
//            ListUnitMeasurementsUC lu = new ListUnitMeasurementsUC(us);
//            FindActivePrinciplesUC fp = new FindActivePrinciplesUC(as);
//            FindLaboratoriesUC fl = new FindLaboratoriesUC(ls);
//            FindMedicineByNameUC fmmn = new FindMedicineByNameUC(mss);
//            FindModeadministrationByNameUC fmn = new FindModeadministrationByNameUC(ms);
//            FindUnitMeasurementByNameUC fumn = new FindUnitMeasurementByNameUC(us);
//            FindActivePrincipleByNameUC fapn = new FindActivePrincipleByNameUC(as);
//            FindLaboratoryByNameUC fln = new FindLaboratoryByNameUC(ls);
//            FindModeadministrationByIdUC fmi = new FindModeadministrationByIdUC(ms);
//            FindUnitMeasurementByIdUC fumi = new FindUnitMeasurementByIdUC(us);
//            FindActivePrincipleByIdUC fapi = new FindActivePrincipleByIdUC(as);
//            FindLaboratoryByIdUC fli = new FindLaboratoryByIdUC(ls);
//            MedicineController c = new MedicineController(um,lmm,lm,lu,fp,fl,fmmn,fmn,fumn,fapn,fln,fmi,fumi,fapi,fli);
//            c.updateMedicine();
        } else if (entity.equals("Farmacy-Medicine")) {
//            UpdateFarmacyMedicineUC ufm = new UpdateFarmacyMedicineUC(fms);
//            FindFarmacyMedicineByIdUC ffmi = new FindFarmacyMedicineByIdUC(fms);
//            ListAllFarmaciesUC lf = new ListAllFarmaciesUC(fs);
//            ListMedicinesUC lmm = new ListMedicinesUC(mss);
//            FindFarmacyByNameUC ffn = new FindFarmacyByNameUC(fs);
//            FindMedicineByNameUC fmmn = new FindMedicineByNameUC(mss);
//            FarmacyMedicineController c = new FarmacyMedicineController(ufm,ffmi,lf,lmm,ffn,fmmn);
//            c.updateFarmacyMedicine();
        }
    }

    private void handleDelete(String entity) {
        if (entity.equals("Survey")) {
//            ListAllCountriesUC fcsuc = new ListAllCountriesUC(ss);
//            FindSurveyByNameUC fciduc = new FindSurveyByNameUC(ss);
//            DeleteSurveyUC dcuc = new DeleteSurveyUC(ss);
//            SurveyController uiSurvey = new SurveyController(dcuc, fcsuc, fciduc);
//            uiSurvey.DeleteSurvey();
        } else if (entity.equals("Chapter")) {
            DeleteChapterUC deleteChapterUC = new DeleteChapterUC(cs);
            ListChaptersUC listChaptersUC = new ListChaptersUC(cs);
            FindChapterByNameUC findChapterByNameUC = new FindChapterByNameUC(cs);
            ChapterController cc = new ChapterController(deleteChapterUC, listChaptersUC, findChapterByNameUC);
            cc.deleteChapter();
        } else if (entity.equals("Question")) {
//            DeleteQuestionUC dr = new DeleteQuestionUC(qs);
//            FindQuestionByNameUC fr = new FindQuestionByNameUC(qs);
//            ListQuestionsUC lac = new ListQuestionsUC(qs);
//            QuestionController rc = new QuestionController(dr,fr,lac);
//            rc.deleteQuestion();
        } else if (entity.equals("Laboratory")) {
//            DeleteLaboratoryUC dl = new DeleteLaboratoryUC(ls);
//            FindLaboratoriesUC fl = new FindLaboratoriesUC(ls);
//            FindLaboratoryByNameUC fln = new FindLaboratoryByNameUC(ls);
//            LaboratoryController lc = new LaboratoryController(dl,fl,fln);
//            lc.deleteLaboratory();
        } else if (entity.equals("ResponseOptions")) {
            DeleteResponseOptionsUC dr = new DeleteResponseOptionsUC(rs);
            ListResponseOptionsUC lr = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fr = new FindResponseOptionsByNameUC(rs);
            ResponseOptionsController cc = new ResponseOptionsController(dr,lr,fr);
            cc.deleteResponseOptions();
        } else if (entity.equals("ModeAdministration")) {
//            ModeadministrationService ms = new ModeAdministrationRepository();
//            ListModeadministrationsUC fcsuc = new ListModeadministrationsUC(ms);
//            FindModeadministrationByNameUC fciduc = new FindModeadministrationByNameUC(ms);
//            DeleteModeadministrationUC dcuc = new DeleteModeadministrationUC(ms);
//            ModeAdministrationController uiSurvey = new ModeAdministrationController(dcuc, fciduc, fcsuc);
//            uiSurvey.deleteModeAdministration();
        } else if (entity.equals("ActivePrinciple")) {
//            DeleteActivePrincipleUC da = new DeleteActivePrincipleUC(as);
//            FindActivePrinciplesUC fa = new FindActivePrinciplesUC(as);
//            FindActivePrincipleByNameUC fan = new FindActivePrincipleByNameUC(as);
//            ActivePrincipleController ac = new ActivePrincipleController(da,fa,fan);
//            ac.DeleteActivePrinciple();
        } else if (entity.equals("UnitMeasurement")) {
//            DeleteUnitMeasurementUC du = new DeleteUnitMeasurementUC(us);
//            ListUnitMeasurementsUC lu = new ListUnitMeasurementsUC(us);
//            FindUnitMeasurementByNameUC fu = new FindUnitMeasurementByNameUC(us);
//            UnitMeasurementController uc = new UnitMeasurementController(du,lu,fu);
//            uc.deleteUnitMeasurement();
        } else if (entity.equals("Farmacy")) {
//            DeleteFarmacyUC dcuc = new DeleteFarmacyUC(fs);
//            ListAllFarmaciesUC fcsuc = new ListAllFarmaciesUC(fs);
//            FindFarmacyByNameUC fciduc = new FindFarmacyByNameUC(fs);
//            FarmacyController c = new FarmacyController(dcuc, fcsuc, fciduc);
//            c.deleteFarmacy();
        } else if (entity.equals("Medicine")) {
//            DeleteMedicineUC dm = new DeleteMedicineUC(mss);
//            ListMedicinesUC fcsuc = new ListMedicinesUC(mss);
//            FindMedicineByNameUC fcn = new FindMedicineByNameUC(mss);
//            MedicineController c = new MedicineController(dm,fcsuc,fcn);
//            c.deleteMedicine();
        } else if (entity.equals("Farmacy-Medicine")) {
//            DeleteFarmacyMedicineUC dfm = new DeleteFarmacyMedicineUC(fms);
//            ListAllFarmaciesUC lf = new ListAllFarmaciesUC(fs);
//            ListMedicinesUC lm = new ListMedicinesUC(mss);
//            FindFarmacyByNameUC ffn = new FindFarmacyByNameUC(fs);
//            FindMedicineByNameUC fmn = new FindMedicineByNameUC(mss);
//            FarmacyMedicineController c = new FarmacyMedicineController(dfm,lf,lm,ffn,fmn);
//            c.deleteFarmacyMedicine();
        }
    }
}
