/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platjava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class FXMLMissionControlController implements Initializable {
    
    /*
    TODO right-clic sur les tab pour delete
    */
    public static final String TITLE = "KSP Mission Control Center";
    public static final String FXML_RESOURCE = "FXMLMissionControl.fxml";

    @FXML
    private TabPane tabPane;
    @FXML
    private Menu removeChartMenu;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.addProbe("Voyager 1"); // pour checker le dev
    }

    @FXML
    private void exportMenuItem(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle(FXMLExportController.TITLE);

            Parent root = FXMLLoader.load(getClass().getResource(FXMLExportController.FXML_RESOURCE));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeMenuItem(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addChartMenuItem(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle(FXMLAddChartController.TITLE);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLAddChartController.FXML_RESOURCE));
            Parent root = (Parent) loader.load();
             
            FXMLAddChartController controller = (FXMLAddChartController)loader.getController();
            controller.setProbeName("Voyager 1");
            controller.setMissionControlController(this);
            
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addProbe(String probeName) {

    }
    
    public void addChart(String title, String xAxis, String yAxis) {
        NumberAxis xNumberAxis = new NumberAxis();
        xNumberAxis.setLabel(xAxis);
        
        NumberAxis yNumberAxis = new NumberAxis();
        yNumberAxis.setLabel(yAxis);
        
        Chart chart = new LineChart(xNumberAxis, yNumberAxis);
        chart.setTitle(title);
        
        // On ajoute l'onglet
        final Tab tab = new Tab();
        tab.setText(title);
        tab.setContent(chart);
        
        this.tabPane.getTabs().add(tab);

        // On ajoute une entrée au menu pour supprimer l'onglet
        final MenuItem menuItem = new MenuItem();
        menuItem.setText(title);
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabPane.getTabs().remove(tab);
                removeChartMenu.getItems().remove(menuItem);
            }
        });
        this.removeChartMenu.getItems().add(menuItem);
    }

    @FXML
    private void aboutMenuItem(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle(FXMLAboutController.TITLE);

            Parent root = FXMLLoader.load(getClass().getResource(FXMLAboutController.FXML_RESOURCE));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
