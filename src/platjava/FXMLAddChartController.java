/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField titleTextField;
    @FXML
    private ComboBox<?> yAxisComboBox;
    @FXML
    private ComboBox<?> xAxisComboBox;
    
    private FXMLMissionControlController missionControlController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addButton(ActionEvent event) {
        this.missionControlController.addChart(this.titleTextField.getText(), (String) this.xAxisComboBox.getValue(),  (String) this.yAxisComboBox.getValue());
        ((Stage) this.anchor.getScene().getWindow()).close();
    }

    @FXML
    private void cancelButton(ActionEvent event) {
        ((Stage) this.anchor.getScene().getWindow()).close();
    }

    public void setProbeName(String probeName) {
        this.titleTextField.setText(probeName + " - untitled " + FXMLAddChartController.chartCount++);
    }

    public void setMissionControlController(FXMLMissionControlController missionControlController) {
        this.missionControlController = missionControlController;
    }
    
}
