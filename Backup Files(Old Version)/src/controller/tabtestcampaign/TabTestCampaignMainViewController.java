/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.tabtestcampaign;

import DB.TestCampaign;
import DB.TestCase;
import controller.TATFrameController;
import controller.util.CommonFunctions;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import model.currentTab;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Thomas M.
 */
public class TabTestCampaignMainViewController implements Initializable {

    private static TabTestCampaignRepositoryController RepositoryController;
    private final ArrayList<currentTab> currentViewTab = new ArrayList<>();
    private final Hashtable<Tab, TabTestCampaignNewController> storeNewControler = new Hashtable<>();
    @FXML
    private AnchorPane anchorPanelCampaignRepository;
    @FXML
    private TabPane tabPaneTestCampaign;
    private Tab tabRepository;
    private TabTestCampaignViewController viewController;
    private TabTestCampaignNewController newController;
    private TATFrameController mainFrameController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Creation of the tab campaign repository
        this.tabRepository = new Tab("Repository");
        this.tabRepository.setStyle("-fx-background-color :  #ffc107;"
                + "-fx-background-insets : transparent;");
        this.tabPaneTestCampaign.getTabs().add(0, tabRepository);
        this.tabRepository.setClosable(false);
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            AnchorPane repositoryPane = fxmlLoader.load(getClass().getResource("/assets/view/testcampaign/TabTestCampaignRepository.fxml").openStream());
            this.tabRepository.setContent(repositoryPane);
            RepositoryController = (TabTestCampaignRepositoryController) fxmlLoader.getController();
            RepositoryController.init(this);
        } catch (IOException ex) {
            CommonFunctions.debugLog.error("", ex);
        }
        this.tabPaneTestCampaign.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);

        /*
         *  Listener to update the table of testCampaign when the tab repository is foscused
         */
//        this.tabPaneTestCampaign.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener<Tab>() {
//
//                    @Override
//                    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
//
//                        if (newValue == tabRepository) {
//                           // System.out.println("UPDATE !");
//                            updateRepository();
//                        }
//                    }
//
//                }
//        );
    }

    /**
     * Method to open a tab in order to create a new campaign
     */
    public void displayNewTestCampaign() {
        Tab newTestCampaign = new Tab("New Campaign");
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            AnchorPane addPane = fxmlLoader.load(getClass().getResource("/assets/view/testcampaign/TabTestCampaignNew.fxml").openStream());
            newTestCampaign.setContent(addPane);
        } catch (Exception e) {
            CommonFunctions.debugLog.error("Exception caught when opening TestCampaignNew", e);
        }
        newController = fxmlLoader.getController();
        //System.out.println("INIT");
        newController.init(this);
        storeNewControler.put(newTestCampaign, newController);
        newTestCampaign.setText("New Test Campaign");
        this.tabPaneTestCampaign.getTabs().add(newTestCampaign);
        newTestCampaign.setClosable(true);
        this.tabPaneTestCampaign.getSelectionModel().select(newTestCampaign);
        newTestCampaign.setOnCloseRequest((Event event) -> {
            storeNewControler.get(newTestCampaign).closePopUp();
            storeNewControler.remove(newTestCampaign);
        });
        newTestCampaign.selectedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue) {
                storeNewControler.get(newTestCampaign).focusOnPopUp();
            }
        });
    }

    /**
     * Methode to remove a tab from the list of tabpane when the tab should be
     * closed
     */
    public void closeTab() {
        this.tabPaneTestCampaign.getTabs().remove(this.tabPaneTestCampaign.getSelectionModel().getSelectedItem());
    }

    /**
     * Method to create a tab in order to assets.view the details of a test campaign
     *
     * @param campaign the campaign to display
     */
    public void displayViewTab(TestCampaign campaign) {

        if (!searchPanel(currentViewTab, campaign.getIdtestCampaign())) {
            try {
                Tab view = new Tab();
                FXMLLoader fxmlLoader = new FXMLLoader();
                AnchorPane editPane = fxmlLoader.load(getClass().getResource("/assets/view/testcampaign/TabTestCampaignView.fxml").openStream());
                view.setContent(editPane);
                viewController = fxmlLoader.getController();
                viewController.init(this);
                view.setText("View : " + campaign.getReference());
                this.tabPaneTestCampaign.getTabs().add(view);
                viewController.constructInformation(campaign);
                view.setClosable(true);
                currentTab viewCurrent = new currentTab(campaign.getIdtestCampaign(), view);
                currentViewTab.add(viewCurrent);
                this.tabPaneTestCampaign.getSelectionModel().select(view);
                view.setOnClosed((Event t) -> {
                    t.consume();
                    currentViewTab.remove(viewCurrent);
                    CommonFunctions.reportLog.info("User cancel viewing " + campaign.getIdtestCampaign());
                });
            } catch (IOException ex) {
                CommonFunctions.debugLog.error("", ex);
            }
        }
    }

    /**
     * Method to search if the campaign given in parameter is already open for
     * viewing
     *
     * @param currentTab the arraylist of tab opened
     * @param ID         the ID of the campaign to search in the arraylist
     * @return if the campaign is in the tab
     */
    private boolean searchPanel(ArrayList<currentTab> currentTab, int ID) {
        boolean found = false;
        int i = 0;
        while (i < currentTab.size() && !found) {
            if (currentTab.get(i).getID() == ID) {
                found = true;
                this.tabPaneTestCampaign.getSelectionModel().select(currentTab.get(i).getTab());
            }
            i++;
        }
        return found;
    }

    /**
     * Method to initialize the global variable mainFrameController with the
     * TATFrameController given as param
     *
     * @param mainController the TATFrameController to associate
     */
    public void init(TATFrameController mainController) {
        this.mainFrameController = mainController;
    }

    /**
     * Method in order to call the method assets.view test case from the mainFrame
     *
     * @param testCase the test case to display
     */
    public void viewTestCase(TestCase testCase) {
        this.mainFrameController.callViewToTestCase(testCase);
    }

    /**
     * Update of the campaign repository
     */
    public void updateRepository() {
        TabTestCampaignRepositoryController.updateRepository();
    }

    /**
     * getter of the mainFrameController
     *
     * @return the mainFrameController
     */
    public TATFrameController getMainController() {
        return this.mainFrameController;
    }

    /**
     *
     */
    public void focusRepository() {
        this.tabPaneTestCampaign.getSelectionModel().select(0);
    }
}
