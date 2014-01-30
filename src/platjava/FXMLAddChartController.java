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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;
    @FXML
    private Label colorLabel;

    private FXMLMissionControlController missionControlController;

    /**
     * Initializes the controller class.
     *
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

        ColorStyleChangeListener cscl = new ColorStyleChangeListener();
        this.redSlider.valueProperty().addListener(cscl);
        this.greenSlider.valueProperty().addListener(cscl);
        this.blueSlider.valueProperty().addListener(cscl);
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

            int red = (int) Math.floor(redSlider.getValue());
            int green = (int) Math.floor(greenSlider.getValue());
            int blue = (int) Math.floor(blueSlider.getValue());

            KSPChart chart = new KSPChart(this.uid, this.titleTextField.getText(), x, y, getColor());
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

    private class ColorStyleChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {

            StringBuilder sb = new StringBuilder();

            sb.append("-fx-border-width: 1px;");
            sb.append("-fx-border-style: solid;");
            sb.append("-fx-border-color: black;");
            sb.append("-fx-background-color: ");
            sb.append(getColor());
            sb.append(";");
            colorLabel.setStyle(sb.toString());
        }
    }

    private String getColor() {
        int red = (int) Math.floor(redSlider.getValue());
        int green = (int) Math.floor(greenSlider.getValue());
        int blue = (int) Math.floor(blueSlider.getValue());

        StringBuilder sb = new StringBuilder();

        sb.append("#");
        if (red < 0x10) {
            sb.append("0");
        }
        sb.append(Integer.toHexString(red));
        if (green < 0x10) {
            sb.append("0");
        }
        sb.append(Integer.toHexString(green));
        if (blue < 0x10) {
            sb.append("0");
        }
        sb.append(Integer.toHexString(blue));

        return sb.toString();
    }
}
