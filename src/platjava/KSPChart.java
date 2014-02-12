/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.json.JSONException;

/**
 *
 * @author bruno
 */
public class KSPChart {
    
    final private XYChart.Series serie;
    final private LineChart chart;
    final private DataType x, y;
    final private String title;
    final private String uid;
    
    // Pattern décorateur pour manipuler des graphiques
    KSPChart(String uid, String title, DataType x, DataType y, String color) throws Exception {
        if (x.getType().equals(String.class)) throw new Exception("X is not a numeric value.");
        if (y.getType().equals(String.class)) throw new Exception("Y is not a numeric value.");
        
        this.uid = uid;
        this.title = title;
        this.x = x;
        this.y = y;

        NumberAxis xNumberAxis = new NumberAxis();
        xNumberAxis.setLabel(x.getLabel());

        NumberAxis yNumberAxis = new NumberAxis();
        yNumberAxis.setLabel(y.getLabel());

        this.serie = new XYChart.Series();
        this.serie.setName(title);
        
        this.chart = new LineChart(xNumberAxis, yNumberAxis);
        this.chart.setTitle(title);
        this.chart.getData().add(serie);
        this.chart.legendVisibleProperty().set(false);
        this.chart.setCreateSymbols(false);
        
        this.serie.getNode().setStyle("-fx-stroke: " + color + ";");

    }
    
        public LineChart getChart() {
        return this.chart;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void addData(Telemetry t) {
        try {
            if (this.uid.equals(String.valueOf(t.getData(DataType.UNIQUE_ID))))
                this.serie.getData().add(new XYChart.Data(t.getData(this.x), t.getData(this.y)));
        } catch (JSONException ex) {
            Logger.getLogger(KSPChart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
