package com.example.UI.infrastructure.controller;

import com.example.modules.chapter.application.*;
import com.example.modules.chapter.domain.service.ChapterService;
import com.example.modules.chapter.infrastructure.controller.ChapterController;
import com.example.modules.chapter.infrastructure.repository.ChapterRepository;
import com.example.modules.question.application.CreateQuestionUC;
import com.example.modules.question.application.DeleteQuestionUC;
import com.example.modules.question.application.FindQuestionByIdUC;
import com.example.modules.question.application.FindQuestionByNameUC;
import com.example.modules.question.application.ListQuestionsUC;
import com.example.modules.question.application.UpdateQuestionUC;
import com.example.modules.question.domain.service.QuestionService;
import com.example.modules.question.infrastructure.controller.QuestionController;
import com.example.modules.question.infrastructure.repository.QuestionRepository;
import com.example.modules.responseoptions.application.CreateResponseOptionsUC;
import com.example.modules.responseoptions.application.DeleteResponseOptionsUC;
import com.example.modules.responseoptions.application.FindResponseOptionsByIdUC;
import com.example.modules.responseoptions.application.FindResponseOptionsByNameUC;
import com.example.modules.responseoptions.application.ListResponseOptionsUC;
import com.example.modules.responseoptions.application.UpdateResponseOptionsUC;
import com.example.modules.responseoptions.domain.service.ResponseOptionsService;
import com.example.modules.responseoptions.infrastructure.controller.ResponseOptionsController;
import com.example.modules.responseoptions.infrastructure.repository.ResponseOptionsRepository;
import com.example.modules.responsequestions.application.CreateResponseQuestionUC;
import com.example.modules.responsequestions.application.DeleteResponseQuestionUC;
import com.example.modules.responsequestions.application.FindResponseQuestionByIdUC;
import com.example.modules.responsequestions.application.FindResponseQuestionByNameUC;
import com.example.modules.responsequestions.application.ListResponseQuestionsUC;
import com.example.modules.responsequestions.application.UpdateResponseQuestionUC;
import com.example.modules.responsequestions.domain.service.ResponseQuestionService;
import com.example.modules.responsequestions.infrastructure.controller.ResponseQuestionController;
import com.example.modules.responsequestions.infrastructure.repository.ResponseQuestionRepository;
import com.example.modules.survey.application.CreateSurveyUC;
import com.example.modules.survey.application.DeleteSurveyUC;
import com.example.modules.survey.application.FindSurveyByIdUC;
import com.example.modules.survey.application.FindSurveyByNameUC;
import com.example.modules.survey.application.ListSurveysUC;
import com.example.modules.survey.application.UpdateSurveyUC;
import com.example.modules.survey.domain.service.SurveySercive;
import com.example.modules.survey.infrastructure.controller.SurveyController;
import com.example.modules.survey.infrastructure.repository.SurveyRepository;
import com.example.modules.categoriescatalog.application.FindCategoriesCatalogByNameUC;
import com.example.modules.categoriescatalog.application.ListCategoriesCatalogsUC;
import com.example.modules.categoriescatalog.application.UpdateCategoriesCatalogUC;
import com.example.modules.categoriescatalog.application.CreateCategoriesCatalogUC;
import com.example.modules.categoriescatalog.application.DeleteCategoriesCatalogUC;
import com.example.modules.categoriescatalog.application.FindCategoriesCatalogByIdUC;
import com.example.modules.categoriescatalog.domain.service.CategoriesCatalogService;
import com.example.modules.categoriescatalog.infrastructure.controller.CategoriesCatalogController;
import com.example.modules.categoriescatalog.infrastructure.repository.CategoriesCatalogRepository;
import com.example.modules.subresponseoptions.application.*;
import com.example.modules.subresponseoptions.domain.service.SubresponseOptionsService;
import com.example.modules.subresponseoptions.infrastructure.controller.SubresponseOptionsController;
import com.example.modules.subresponseoptions.infrastructure.repository.SubresponseOptionsRepository;

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
        String[] mainOptions = {"Survey", "Chapter", "Question", "CategoriesCatalog", "ResponseOptions", "SubresponseOptions", "ResponseQuestion"};
        for (String option : mainOptions) {
            addButton(mainMenuPanel, option, this);
        }
    }

    private void initializeSubPanels() {
        String[] entities = {"Survey", "Chapter", "Question", "CategoriesCatalog", "ResponseOptions", "SubresponseOptions", "ResponseQuestion"};
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
        } else if (text.contains("SubresponseOptions")) {
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
    SubresponseOptionsService sop = new SubresponseOptionsRepository();
    ResponseQuestionService rqs = new ResponseQuestionRepository();

    private void handleCreate(String entity) {
        // TODO, survey create
        if (entity.equals("Survey")) {
            CreateSurveyUC cc = new CreateSurveyUC(ss);
            SurveyController ccc = new SurveyController(cc);
            ccc.createSurvey();
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
        } else if (entity.equals("SubresponseOptions")) {
            CreateSubresponseOptionsUC cso = new CreateSubresponseOptionsUC(sop);
            ListResponseOptionsUC lr = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fr = new FindResponseOptionsByNameUC(rs);
            ListSubresponseOptionsUC ls = new ListSubresponseOptionsUC(sop);
            FindSubresponseOptionsByIdUC fs = new FindSubresponseOptionsByIdUC(sop);
            SubresponseOptionsController cc = new SubresponseOptionsController(cso, lr, fr, ls, fs);
            cc.createSubresponseOptions();
        } else if (entity.equals("ResponseQuestion")) {
            CreateResponseQuestionUC crq = new CreateResponseQuestionUC(rqs);
            ListResponseQuestionsUC lrq = new ListResponseQuestionsUC(rqs);
            FindResponseQuestionByNameUC frqn = new FindResponseQuestionByNameUC(rqs);
            ListResponseOptionsUC lro = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fron = new FindResponseOptionsByNameUC(rs);
            ListSubresponseOptionsUC lso = new ListSubresponseOptionsUC(sop);
            FindSubresponseOptionsBySubresponseTextUC fson = new FindSubresponseOptionsBySubresponseTextUC(sop);
            ResponseQuestionController cc = new ResponseQuestionController(crq, lrq, frqn, lro, fron, lso, fson);
            cc.createResponseQuestion();
        }
    }

    private void handleList(String entity) {
        // TODO, Survey list
        if (entity.equals("Survey")) {
            ListSurveysUC ls = new ListSurveysUC(ss);
            SurveyController ccc = new SurveyController(ls);
            ccc.listSurveys();
        } else if (entity.equals("Chapter")) {
            ListChaptersUC lc = new ListChaptersUC(cs);
            ChapterController ccc = new ChapterController(lc);
            ccc.listChapters();
        } else if (entity.equals("Question")) {
            ListQuestionsUC lq = new ListQuestionsUC(qs);
            QuestionController c = new QuestionController(lq);
            c.listQuestions();
        } else if (entity.equals("CategoriesCatalog")) {
            ListCategoriesCatalogsUC lcat = new ListCategoriesCatalogsUC(ccs);
            CategoriesCatalogController ccc = new CategoriesCatalogController(lcat);
            ccc.listCategoriesCatalogs();
        } else if (entity.equals("ResponseOptions")) {
            ListResponseOptionsUC lr = new ListResponseOptionsUC(rs);
            ResponseOptionsController c = new ResponseOptionsController(lr);
            c.listResponseOptions();
        } else if (entity.equals("SubresponseOptions")) {
            ListSubresponseOptionsUC lso = new ListSubresponseOptionsUC(sop);
            SubresponseOptionsController cc = new SubresponseOptionsController(lso);
            cc.listSubresponseOptions();
        } else if (entity.equals("ResponseQuestion")) {
            ListResponseQuestionsUC lrq = new ListResponseQuestionsUC(rqs);
            ResponseQuestionController c = new ResponseQuestionController(lrq);
            c.listResponseQuestions();
        }
    }

    private void handleSearch(String entity) {
        // TODO, Survey Search
        if (entity.equals("Survey")) {
            FindSurveyByIdUC fs = new FindSurveyByIdUC(ss);
            SurveyController ccc = new SurveyController(fs);
            ccc.findSurveyByID();
        } else if (entity.equals("Chapter")) {
            FindChapterByIdUC fc = new FindChapterByIdUC(cs);
            ChapterController ccc = new ChapterController(fc);
            ccc.findChapterById();
        } else if (entity.equals("Question")) {
            FindQuestionByIdUC fq = new FindQuestionByIdUC(qs);
            QuestionController c = new QuestionController(fq);
            c.searchQuestions();
        } else if (entity.equals("CategoriesCatalog")) {
            FindCategoriesCatalogByIdUC fcc = new FindCategoriesCatalogByIdUC(ccs);
            CategoriesCatalogController ccc = new CategoriesCatalogController(fcc);
            ccc.findCategoriesCatalogById();
        } else if (entity.equals("ResponseOptions")) {
            FindResponseOptionsByIdUC fr = new FindResponseOptionsByIdUC(rs);
            ResponseOptionsController c = new ResponseOptionsController(fr);
            c.findResponseOptionsById();
        } else if (entity.equals("SubresponseOptions")) {
            FindSubresponseOptionsByIdUC fso = new FindSubresponseOptionsByIdUC(sop);
            SubresponseOptionsController cc = new SubresponseOptionsController(fso);
            cc.findSubresponseOptionsById();
        } else if (entity.equals("ResponseQuestion")) {
            FindResponseQuestionByIdUC frqi = new FindResponseQuestionByIdUC(rqs);
            ResponseQuestionController c = new ResponseQuestionController(frqi);
            c.findResponseQuestionById();
        }
    }

    private void handleUpdate(String entity) {
        // TODO, Survey Search
        if (entity.equals("Survey")) {
            UpdateSurveyUC uq = new UpdateSurveyUC(ss);
            ListSurveysUC ls = new ListSurveysUC(ss);
            FindSurveyByNameUC fsn = new FindSurveyByNameUC(ss);
            FindSurveyByIdUC fid = new FindSurveyByIdUC(ss);
            SurveyController cc = new SurveyController(uq,ls,fsn,fid);
            cc.updateSurvey();
        } else if (entity.equals("Chapter")) {
            UpdateChapterUC uc = new UpdateChapterUC(cs);
            ListChaptersUC lc = new ListChaptersUC(cs);
            ListSurveysUC ls = new ListSurveysUC(ss);
            FindChapterByNameUC fc = new FindChapterByNameUC(cs);
            FindSurveyByIdUC fsi = new FindSurveyByIdUC(ss);
            FindSurveyByNameUC fs = new FindSurveyByNameUC(ss);
            ChapterController c = new ChapterController(uc, lc, ls, fc, fsi, fs);
            c.updateChapter();
        } else if (entity.equals("Question")) {
            UpdateQuestionUC uc = new UpdateQuestionUC(qs);
            ListQuestionsUC lq = new ListQuestionsUC(qs);
            ListChaptersUC lc = new ListChaptersUC(cs);
            FindQuestionByNameUC fqn = new FindQuestionByNameUC(qs);
            FindChapterByIdUC fci = new FindChapterByIdUC(cs);
            FindChapterByNameUC fcn = new FindChapterByNameUC(cs);
            QuestionController cccccc = new QuestionController(uc, lq, lc, fqn, fci, fcn);
            cccccc.updateQuestion();
        } else if (entity.equals("CategoriesCatalog")) {
            UpdateCategoriesCatalogUC ucc = new UpdateCategoriesCatalogUC(ccs);
            ListCategoriesCatalogsUC lcc = new ListCategoriesCatalogsUC(ccs);
            FindCategoriesCatalogByNameUC fccn = new FindCategoriesCatalogByNameUC(ccs);
            FindCategoriesCatalogByIdUC fcci = new FindCategoriesCatalogByIdUC(ccs);
            CategoriesCatalogController cc = new CategoriesCatalogController(ucc, lcc, fccn, fcci);
            cc.updateCategoriesCatalog();
        } else if (entity.equals("ResponseOptions")) {
            UpdateResponseOptionsUC uro = new UpdateResponseOptionsUC(rs);
            ListResponseOptionsUC lro = new ListResponseOptionsUC(rs);
            ListQuestionsUC lq = new ListQuestionsUC(qs);
            ListCategoriesCatalogsUC lcc = new ListCategoriesCatalogsUC(ccs);
            FindResponseOptionsByIdUC froi = new FindResponseOptionsByIdUC(rs);
            FindResponseOptionsByNameUC fron = new FindResponseOptionsByNameUC(rs);
            FindQuestionByIdUC fqi = new FindQuestionByIdUC(qs);
            FindQuestionByNameUC fqn = new FindQuestionByNameUC(qs);
            FindCategoriesCatalogByIdUC fcci = new FindCategoriesCatalogByIdUC(ccs);
            FindCategoriesCatalogByNameUC fccn = new FindCategoriesCatalogByNameUC(ccs);
//            ResponseOptionsController c = new ResponseOptionsController(uro, lro, lq, lcc, froi, fron, fqi, fqn, fcci, fccn);
//            c.updateResponseOptions();
        } else if (entity.equals("SubresponseOptions")) {
            UpdateSubresponseOptionsUC uso = new UpdateSubresponseOptionsUC(sop);
            ListSubresponseOptionsUC lso = new ListSubresponseOptionsUC(sop);
            ListResponseOptionsUC lro = new ListResponseOptionsUC(rs);
            FindSubresponseOptionsByIdUC fsoi = new FindSubresponseOptionsByIdUC(sop);
            FindSubresponseOptionsBySubresponseTextUC fson = new FindSubresponseOptionsBySubresponseTextUC(sop);
            FindResponseOptionsByIdUC froi = new FindResponseOptionsByIdUC(rs);
            FindResponseOptionsByNameUC fron = new FindResponseOptionsByNameUC(rs);
            SubresponseOptionsController cccc = new SubresponseOptionsController(uso, lso, lro, fsoi, fson, froi, fron);
            cccc.updateSubresponseOptions();
        } else if (entity.equals("ResponseQuestion")) {
            UpdateResponseQuestionUC drq = new UpdateResponseQuestionUC(rqs);
            ListResponseQuestionsUC lrq = new ListResponseQuestionsUC(rqs);
            FindResponseQuestionByNameUC frqn = new FindResponseQuestionByNameUC(rqs);
            ListResponseOptionsUC lro = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fron = new FindResponseOptionsByNameUC(rs);
            ListSubresponseOptionsUC lso = new ListSubresponseOptionsUC(sop);
            FindSubresponseOptionsBySubresponseTextUC fson = new FindSubresponseOptionsBySubresponseTextUC(sop);
            ResponseQuestionController cc = new ResponseQuestionController(drq, lrq, frqn, lro, fron, lso, fson);
            cc.updateResponseQuestion();
        }
    }

    private void handleDelete(String entity) {
        // TODO, survey create
        if (entity.equals("Survey")) {
            DeleteSurveyUC ds = new DeleteSurveyUC(ss);
            ListSurveysUC lss = new ListSurveysUC(ss);
            FindSurveyByNameUC fsn = new FindSurveyByNameUC(ss);
            SurveyController ccc = new SurveyController(ds, lss, fsn);
            ccc.deleteSurvey();
        } else if (entity.equals("Chapter")) {
            DeleteChapterUC dc = new DeleteChapterUC(cs);
            ListChaptersUC ls = new ListChaptersUC(cs);
            FindChapterByNameUC fci = new FindChapterByNameUC(cs);
            ChapterController ccc = new ChapterController(dc,ls,fci);
            ccc.deleteChapter();
        } else if (entity.equals("Question")) {
            DeleteQuestionUC dq = new DeleteQuestionUC(qs);
            ListChaptersUC lc = new ListChaptersUC(cs);
            FindChapterByNameUC fc = new FindChapterByNameUC(cs);
            QuestionController c = new QuestionController(dq,lc,fc);
            c.deleteQuestion();
        } else if (entity.equals("CategoriesCatalog")) {
            DeleteCategoriesCatalogUC dcat = new DeleteCategoriesCatalogUC(ccs);
            ListCategoriesCatalogsUC lcc = new ListCategoriesCatalogsUC(ccs);
            FindCategoriesCatalogByNameUC fcc = new FindCategoriesCatalogByNameUC(ccs);
            CategoriesCatalogController ccc = new CategoriesCatalogController(dcat, lcc, fcc);
            ccc.deleteCategoriesCatalog();
        } else if (entity.equals("ResponseOptions")) {
            DeleteResponseOptionsUC dr = new DeleteResponseOptionsUC(rs);
            ListResponseOptionsUC lr = new ListResponseOptionsUC(rs);
            FindResponseOptionsByNameUC fr = new FindResponseOptionsByNameUC(rs);
            ResponseOptionsController cc = new ResponseOptionsController(dr,lr,fr);
            cc.deleteResponseOptions();
        } else if (entity.equals("SubresponseOptions")) {
            DeleteSubresponseOptionsUC dso = new DeleteSubresponseOptionsUC(sop);
            ListSubresponseOptionsUC ls = new ListSubresponseOptionsUC(sop);
            FindSubresponseOptionsByIdUC fs = new FindSubresponseOptionsByIdUC(sop);
            SubresponseOptionsController cc = new SubresponseOptionsController(dso, ls, fs);
            cc.deleteSubresponseOptions();
        } else if (entity.equals("ResponseQuestion")) {
            DeleteResponseQuestionUC drq = new DeleteResponseQuestionUC(rqs);
            ListResponseQuestionsUC lrq = new ListResponseQuestionsUC(rqs);
            FindResponseQuestionByNameUC frqn = new FindResponseQuestionByNameUC(rqs);
            ResponseQuestionController c = new ResponseQuestionController(drq, lrq, frqn);
            c.deleteResponseQuestion();
        }
    }
}
