/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class StatController implements Initializable {
    Connection cnx ;
    int id  ;
    String tlf ;
@FXML
private PieChart pieChart ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        
//        Striwwng rq ="SELECT `id` , `tel` FROM `user`";
//        cnx = DataSource.getInstance().getConnect() ;
//        try (ResultSet rs = cnx.createStatement().executeQuery(rq) ){
//            
//            while (rs.next()) {
//                id = rs.getInt("id") ;
//                tlf = rs.getString("tel") ;
//            }
//            
//        }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        
//        
        
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data( "client" ,102 )  ,          
 new PieChart.Data( "client" ,102 ),
        new PieChart.Data( "client" ,102 ),              
             new PieChart.Data( "client" ,102 )  
        );
        pieChart.setData(pieChartData);
    }
 }