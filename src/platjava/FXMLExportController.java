/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platjava;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.write.WriteException;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author bruno
 */
public class FXMLExportController implements Initializable {

    public static final String TITLE = "Export";
    public static final String FXML_RESOURCE = "FXMLExport.fxml";

    @FXML
    private ToggleGroup formatToogleGroup;
    @FXML
    private TextField fileTextField;
    @FXML
    private AnchorPane anchorPane;

    private File file;
    private ArrayList<Telemetry> data;
    @FXML
    private RadioButton xlsButtonRadio;
    @FXML
    private RadioButton csvButtonRadio;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void exportButton(ActionEvent event) {
        // L'utilsateur veut exporter les données en csv ou en xls
        
        try {
            if (this.file != null) {

                
                 if (this.csvButtonRadio.isSelected())
                    CSVGenerator.generateCsvFile(this.file, this.data);
                 else if (this.xlsButtonRadio.isSelected())
                    XcelGenerator.generateXcelFile(this.file, this.data);

                ((Stage) this.anchorPane.getScene().getWindow()).close();
            }
        } catch (JSONException | IOException | WriteException ex) {
            Logger.getLogger(FXMLExportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void cancelButton(ActionEvent event) {
        // L'utilisateur annule l'exportation
        
        ((Stage) this.anchorPane.getScene().getWindow()).close();
    }

    @FXML
    private void saveAsButton(ActionEvent event) {
        // Permet à l'utilisateur de choisir l'emplacement du fichier d'export
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("export");

        // L'extension du fichier et le filtre du filechooser dépend du format d'export voulu (csv ou xls)
        if (this.csvButtonRadio.isSelected())
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comma-separated values (*.csv)", "*.csv"));
        else if (this.xlsButtonRadio.isSelected())
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel (*.xls)", "*.xls"));
                
        this.file = fileChooser.showSaveDialog(null);

        if (this.file != null) {
            this.fileTextField.setText(this.file.getPath());
        }
    }

    public File getFile() {
        return this.file;
    }

    // Les data à exporter
    public void setData(ArrayList<Telemetry> data) {
        this.data = data;
    }

}
