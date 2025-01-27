/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.macro;

import DB.Script;
import DBcontroller.sessionFactorySingleton;
import controller.macroActions.PreviewMacro;
import controller.macroActions.ScriptLineTableMacroController;
import controller.macroActions.TableActionCreationController;
import controller.util.CommonFunctions;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author tmartinez
 */
public class TabMacroEditController implements Initializable {

    private static TabMacroMainViewController mainController;
    private final PreviewMacro controllerPreviewMacro = new PreviewMacro();
    private final int textfieldMacroNameMaxLength = 60;
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    @FXML
    private AnchorPane anchorPanelEditMacro;
    @FXML
    private GridPane gridPaneMacroEdit;
    @FXML
    private Button buttonValid;
    @FXML
    private GridPane gridPaneTableAction;
    @FXML
    private Text labelActionTitle;
    @FXML
    private Button buttonAddAction;
    @FXML
    private GridPane gridPaneLabelCaseEdit;
    @FXML
    private Label labelMacroNameEdit;
    @FXML
    private Label labelMacroEditionDate;
    @FXML
    private Label labelMacroCreationDate;
    @FXML
    private Label labelMacroVersion;
    @FXML
    private Label labelMacroObjectives;
    @FXML
    private TextField jtextfieldMacroNameEdit;
    @FXML
    private TextField jtextfieldMacroEdit;
    @FXML
    private TextField jtextfieldMacroCreationDateEdit;
    @FXML
    private TextField jtextfieldTypeMacroEditionDateEdit;
    @FXML
    private TextArea jtextareaObjectivesMacroEdit;
    @FXML
    private ScrollPane scrollPanePreview;
    @FXML
    private Label labelIsStimuli;
    @FXML
    private CheckBox stimuliCheckBoxEdit;
    @FXML
    private AnchorPane anchorHeader;
    @FXML
    private TableActionCreationController controllerTableAction;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initButtons();
        this.constructTableStep();
        this.loadPreviewMacro();

        jtextfieldMacroNameEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            changeColorTextMacroName(newValue.trim().isEmpty());
            activationValidButton();
            if (CommonFunctions.displayWarningIncorrectInputFormat("Macro name", textfieldMacroNameMaxLength, newValue.length() > textfieldMacroNameMaxLength)) {
                jtextfieldMacroNameEdit.setText(oldValue);
            }
        });

        jtextareaObjectivesMacroEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            changeColorTextDescription(newValue.trim().isEmpty());
            activationValidButton();
        });

        this.controllerTableAction.collectionControllerScript.addListener((ListChangeListener) c -> activationValidButton());
    }

    /**
     * @param mainController
     */
    public void init(TabMacroMainViewController mainController) {
        TabMacroEditController.mainController = mainController;
        controllerTableAction.initMacroEdit(this);
    }

    /**
     *
     */
    private void initButtons() {
        this.buttonValid.setDisable(true);
        this.buttonAddAction.setOnAction((ActionEvent e) -> controllerTableAction.addAction());

        this.buttonValid.setOnAction((ActionEvent e) -> {
            try {
                this.editMacro();
            } catch (ParseException ex) {
                CommonFunctions.debugLog.error("Cannot edit Macro: ", ex);
            }
        });

    }

    /**
     * @return
     */
    public PreviewMacro getControllerPreview() {
        return this.controllerPreviewMacro;
    }

    private void editMacro() throws ParseException {
        StringBuilder reportLogMsg = new StringBuilder();
        SessionFactory factory = sessionFactorySingleton.getInstance();
        Session session = factory.openSession();
        ObservableList<ScriptLineTableMacroController> observableScripts = controllerTableAction.getCollectionControllerScript();
        int numberScript = observableScripts.size();
        Script macro = null;
        try {
            macro = constructMacro();
        } catch (ParseException ex) {
            CommonFunctions.debugLog.error("Macro Construct Function Exception", ex);
        }
        session.save(macro);
        int i = 0;
        boolean missingPurpose = false;
        reportLogMsg.append("After Editing Macro :").append(Objects.requireNonNull(macro).getName()).append(System.lineSeparator());
        //This part is responsible for saving new macro object. Correct. 
        while ((i < numberScript) && !missingPurpose) {
            if ("".equals(observableScripts.get(i).getScriptControllerAction().getHashParamScriptMacro().get(0).getValue())) {   //Each script should has purpose. First parameter of each script. If satisfied, save it.
                CommonFunctions.displayAlert(AlertType.ERROR, "Missing Purpose", "There is something wrong with script" + observableScripts.get(i).getScriptControllerAction().getCurrentScript().getName(), "A script purpose is missing in your macro");
                missingPurpose = true;
            } else {
                //Save each script to sesson. Set parameters of each observableScript.
                observableScripts.get(i).getScriptControllerAction().getScriptMacro().setScriptByScriptIdScript(macro);
                observableScripts.get(i).getScriptControllerAction().getScriptMacro().setScriptOrder((byte) i);
                observableScripts.get(i).getScriptControllerAction().getScriptMacro().setScriptByScriptIdScript1(observableScripts.get(i).getScriptControllerAction().getCurrentScript());
                session.save(observableScripts.get(i).getScriptControllerAction().getScriptMacro());
                reportLogMsg.append("\tScript ").append(i + 1).append(": ").append(observableScripts.get(i).getScriptControllerAction().getCurrentScript().getName()).append(System.lineSeparator());
                i++;
            }
        }
        if (!missingPurpose) {
            try {
                session.beginTransaction().commit();
                mainController.updateRepository();
                mainController.closeTab();
                mainController.focusLibrary();
                CommonFunctions.reportLog.info(reportLogMsg);
                CommonFunctions.reportLog.info("Successfully saved Macro: " + macro.getName());
            } catch (Exception ex) {
                CommonFunctions.debugLog.error("Error saving modified macro" + ex);
                throw new ParseException("In Macro", 1);
            }
        }
        session.close();
    }

    private Script constructMacro() throws ParseException {
        Script editScript = new Script();
        editScript.setDesciption(jtextareaObjectivesMacroEdit.getText());
        editScript.setName(jtextfieldMacroNameEdit.getText());
        editScript.setScriptVersion(1);
        editScript.setCreationDate(df.format(new Date()));
        editScript.setEditionDate(df.format(new Date()));
        editScript.setIsMacro((byte) (1));
        editScript.setIsStimuli((byte) (stimuliCheckBoxEdit.isSelected() ? 1 : 0));
        return editScript;
    }

    private void constructTableStep() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            this.gridPaneTableAction.add(fxmlLoader.load(getClass().getResource("/assets/view/macroActions/tableActionCreation.fxml").openStream()), 0, 1, 1, 5);
        } catch (IOException ex) {
            CommonFunctions.debugLog.error("Cannot construct table step: ", ex);
        }
        controllerTableAction = fxmlLoader.getController();
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        try {
            AnchorPane paneTest = fxmlLoader2.load(getClass().getResource("/assets/view/macroActions/headerTableAction.fxml").openStream());
            this.gridPaneTableAction.add(paneTest, 0, 0, 1, 1);
        } catch (IOException ex) {
            CommonFunctions.debugLog.error("Cannot construct table step: ", ex);
        }
    }

    /**
     * Init settings for TextAreas & CheckBoxes
     *
     * @param script
     */
    void displayMacro(Script script) {
        buttonAddAction.setVisible(true);
        buttonValid.setVisible(true);

        jtextareaObjectivesMacroEdit.setText(script.getDesciption());
        jtextfieldMacroEdit.setId("displayStyle");
        jtextfieldMacroNameEdit.setText(script.getName());
        jtextfieldMacroNameEdit.setDisable(false);
        jtextfieldMacroNameEdit.setEditable(true);
        jtextfieldMacroEdit.setId("displayStyle");
        jtextfieldMacroEdit.setText(script.getScriptVersion().toString());
        jtextfieldMacroEdit.setDisable(false);
        jtextfieldMacroEdit.setEditable(true);
        jtextfieldMacroCreationDateEdit.setText(script.getCreationDate());
        jtextfieldMacroCreationDateEdit.setDisable(false);
        jtextfieldMacroCreationDateEdit.setEditable(true);
        jtextfieldTypeMacroEditionDateEdit.setText(script.getEditionDate());
        jtextfieldTypeMacroEditionDateEdit.setDisable(false);
        jtextfieldTypeMacroEditionDateEdit.setEditable(true);
        stimuliCheckBoxEdit.setSelected(script.getIsStimuli() != 0);

        controllerTableAction.displayScriptAndStepEdit(script);     //Contruct most of the assets.view.
        controllerPreviewMacro.updateGridPaneCreationView(script);
        this.anchorPanelEditMacro.getStylesheets().add("/assets/view/testcampaign/cssViewCampaign.css");

    }

    private void loadPreviewMacro() {
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        try {
            AnchorPane paneTest = fxmlLoader2.load(getClass().getResource("/assets/view/macroActions/headerPreviewMacro.fxml").openStream());
            AnchorPane.setTopAnchor(paneTest, 0.0);
            AnchorPane.setRightAnchor(paneTest, 0.0);
            AnchorPane.setLeftAnchor(paneTest, 0.0);
            AnchorPane.setBottomAnchor(paneTest, 0.0);
            this.anchorHeader.getChildren().add(paneTest);
            //this.hBoxHeader.getChildren().add(paneTest);
            //this.gridPaneLabelCaseNew.add(paneTest, 5, 0, 1, 1);
        } catch (IOException ex) {
            CommonFunctions.debugLog.error("Cannot load preview Macro: ", ex);
        }

        try {
            this.controllerPreviewMacro.initialize(scrollPanePreview);
        } catch (Exception ex) {
            CommonFunctions.debugLog.error("Cannot load preview Macro: ", ex);
        }
    }

    private void changeColorTextMacroName(boolean color) {
        labelMacroNameEdit.setTextFill(color ? Color.RED : Color.BLACK);
    }

    private void changeColorTextDescription(boolean color) {
        labelMacroObjectives.setTextFill(color ? Color.RED : Color.BLACK);
    }

    private void activationValidButton() {
        if (!jtextareaObjectivesMacroEdit.getText().isEmpty() && !jtextfieldMacroNameEdit.getText().isEmpty() && this.controllerTableAction.getCollectionControllerScript().size() != 0) {
            this.buttonValid.setDisable(false);
        } else {
            this.buttonValid.setDisable(true);
        }
    }
}
