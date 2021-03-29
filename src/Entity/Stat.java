/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import services.CoaachCrud;

/**
 *
 * @author asus
 */
public class Stat {

    private ObservableList<Coaach> list;

    public void statistics() {
        list = CoaachCrud.showcoachEnd();
        Stage stage = new Stage();
        stage.setTitle("Coachs graph");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Coach's name");

        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Coachs graph");

        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        for (Coaach c : list) {
            series.getData().add(new XYChart.Data(c.getNom(), c.getRating()));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

}
