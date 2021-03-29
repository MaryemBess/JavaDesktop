/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.Myconnection;
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
  String  roles ;
   
    @FXML
    private PieChart pieChart ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
//        String rq ="SELECT `roles` FROM `user`";
//        cnx = Myconnection.getInstance().getConnect() ;
//        try (ResultSet rs = cnx.createStatement().executeQuery(rq) ){
//            
//            while (rs.next()) {
//                roles = rs.getString("roles") ;
//                
//            }
//            
//        }
//        catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        Double st = Double.valueOf(roles)   ;    
        
       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        new PieChart.Data( "client" , 253 )  ,          
 new PieChart.Data( "client" ,  200 ),
        new PieChart.Data( "client" ,102 ),              
             new PieChart.Data( "client" ,152 )  
        );
        pieChart.setData(pieChartData);
    }
 }