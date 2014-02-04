/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platjava;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONException;

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
    @FXML
        private Menu addChartMenu;
    @FXML
        private Menu exportProbeMenu;
    @FXML
        private Tab tabProbes;

    private Thread server = null;
    private ArrayList<Telemetry> data = null;
    private HashMap<String, String> probeNames = null;
    private HashMap<String, ArrayList<KSPChart>> charts = null;
    private HashMap<String, Tab> probeTabs = null;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.server = new Thread(new Server());
        this.server.setDaemon(true); // Pour que la JVM puisse fermer l'application
        this.server.start();
        this.data = new ArrayList<>();
        this.probeNames = new HashMap<>();
        this.charts = new HashMap<>();
        this.probeTabs = new HashMap<>();
    }

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

    public void addProbe(final String uid, final String probeName) {
        // Menu item pour créer des charts
        final FXMLMissionControlController missionController = this;
        MenuItem mi = new MenuItem();
        mi.setText(probeName);
        mi.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                try {
                    Stage stage = new Stage();
                    stage.setTitle(FXMLAddChartController.TITLE);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLAddChartController.FXML_RESOURCE));
                    Parent root = (Parent) loader.load();

                    FXMLAddChartController controller = (FXMLAddChartController) loader.getController();
                    controller.setProbeName(probeName);
                    controller.setUid(uid);
                    controller.setMissionControlController(missionController);

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        this.addChartMenu.getItems().add(mi);

        // Menu item pour exporter les data
        mi = new MenuItem();
        mi.setText(probeName);
        mi.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                try {
                    Stage stage = new Stage();
                    stage.setTitle(FXMLExportController.TITLE);
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLExportController.FXML_RESOURCE));
                    Parent root = (Parent) loader.load();

                    FXMLExportController controller = (FXMLExportController) loader.getController();
                    
                    ArrayList<Telemetry> dt = new ArrayList<>();
                    
                    
                    for (Telemetry tl : data) {
                        if (String.valueOf(tl.getData(DataType.UNIQUE_ID)).equals(uid))
                            dt.add(tl);
                    }
                    
                    System.out.println(dt.size());
                    controller.setData(dt);
                    
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.exportProbeMenu.getItems().add(mi);
        
       final Tab tab = new Tab();
       tab.setText(probeName + " - Telemetry");
       
        this.probeTabs.put(uid, tab);
        this.charts.put(uid, new ArrayList<KSPChart>());
        this.probeNames.put(uid, probeName);
        
        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            tabPane.getTabs().add(tab);
                            updateTabProbes();
                        }
                     });
    }

    public void addChart(final String uid, final KSPChart chart) {
        // On ajoute l'onglet
        final Tab tab = new Tab();
        tab.setText(chart.getTitle());
        tab.setContent(chart.getChart());

        this.tabPane.getTabs().add(tab);

        // On ajoute une entrée au menu pour supprimer l'onglet
        final MenuItem menuItem = new MenuItem();
        menuItem.setText(chart.getTitle());
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tabPane.getTabs().remove(tab);
                removeChartMenu.getItems().remove(menuItem);
                charts.get(uid).remove(chart);
            }
        });

        this.removeChartMenu.getItems().add(menuItem);
        this.charts.get(uid).add(chart);
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

    private void updateChart(Telemetry t) {        
        try {
            for (KSPChart c : charts.get(String.valueOf(t.getData(DataType.UNIQUE_ID)))) {
                c.addData(t);
            }

        } catch (JSONException ex) {
            Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateTabProbes() {
        VBox vb = new VBox();
        vb.setPadding(new Insets(12, 12, 12, 12));
        
        vb.getChildren().add(new Label("Connected probes :"));
        
        for (String s : this.probeNames.values()) {
            vb.getChildren().add(new Label(s));
        }
        
        this.tabProbes.setContent(vb);
    }
    
    private void updateTelemetryTab(Telemetry t) {
        try {
            VBox vb = new VBox();
            vb.setPadding(new Insets(12, 12, 12, 12));
            
            ObservableList<Label> infos = FXCollections.observableArrayList();
            for (DataType d : DataType.values()) {
                infos.add(new Label(String.format("%s : %s %s", d.getLabel(), t.getData(d), d.getUnity())));
            }
            vb.getChildren().addAll(infos);
            
            this.probeTabs.get(String.valueOf(t.getData(DataType.UNIQUE_ID))).setContent(vb);
            
            
            
            //if (p == this.tabbedPane.getSelectedComponent()) p.revalidate();
        } catch (JSONException ex) {
            Logger.getLogger(FXMLMissionControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class Server implements Runnable {

        private final static int BUFFER_SIZE = 1024;
        private final static int PORT = 8000;

        @Override
        public void run() {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(PORT);

                byte[] buffer = new byte[BUFFER_SIZE];

                while (true) {
                    DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(datagramPacket);

                    final Telemetry t = new Telemetry(datagramPacket);
                    data.add(t);
                    
                    if (!probeNames.containsKey(String.valueOf((Long) t.getData(DataType.UNIQUE_ID)))) {
                        addProbe(String.valueOf((Long) t.getData(DataType.UNIQUE_ID)), (String) t.getData(DataType.VESSEL_NAME));
                    }

                    //updateTelemetryPanel(t);
                    
                     Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateChart(t);
                            updateTelemetryTab(t);
                        }
                     });
                    
                    
                }
            } catch (JSONException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
