/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class FXMLAddChartController implements Initializable {
    
    public static final String TITLE = "Add chart";
    public static final String FXML_RESOURCE = "FXMLAddChart.fxml";
    private static int chartCount = 0;
    private String uid;
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField titleTextField;
    @FXML
    private ComboBox<String> yAxisComboBox;
    @FXML
    private ComboBox<String> xAxisComboBox;
    
    private FXMLMissionControlController missionControlController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> labels = FXCollections.observableArrayList();
        for (DataType d : DataType.values()) {
            if (!d.getType().equals(String.class)) {
               labels.add(d.getLabel());
            }
        }

         this.xAxisComboBox.setItems(labels);
         this.yAxisComboBox.setItems(labels);
        
        
    }    

    @FXML
    private void addButton(ActionEvent event) {
        try {
            DataType x = null, y = null;
            for (DataType d : DataType.values()) {
                if (this.xAxisComboBox.getValue().equals(d.getLabel())) {
                    x = d;
                }
                
                if (this.yAxisComboBox.getValue().equals(d.getLabel())) {
                    y = d;
                }
            }
            KSPChart chart = new KSPChart(this.uid, this.titleTextField.getText(), x, y);
            this.missionControlController.addChart(this.uid, chart);
            ((Stage) this.anchor.getScene().getWindow()).close();
        } catch (Exception ex) {
            Logger.getLogger(FXMLAddChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        ((Stage) this.anchor.getScene().getWindow()).close();
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public void setProbeName(String probeName) {
        this.titleTextField.setText(probeName + " - untitled " + FXMLAddChartController.chartCount++);
    }

    public void setMissionControlController(FXMLMissionControlController missionControlController) {
        this.missionControlController = missionControlController;
    }
    
}
