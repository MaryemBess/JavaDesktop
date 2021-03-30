/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.Myconnection;
import Entity.Reclamation;
import static Services.Service_Admin.currentUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maryem
 */
public class ReclamFront2Controller implements Initializable {

    @FXML
    private JFXButton refresh_btn;
    @FXML
    private Button supp;
    @FXML
    private Button ajout;
    @FXML
    private JFXTextField tfsearchbyid;
    private JFXButton reclam1;
    private JFXButton reclam;
    @FXML
    private TableView<Reclamation> Table_reclam;
    @FXML
    private TableColumn<?, ?> id_col;
    @FXML
    private TableColumn<?, ?> sujet_col;
    @FXML
    private TableColumn<?, ?> statu_col;
    @FXML
    private TableColumn<?, ?> desc_col;
    @FXML
    private TableColumn<?, ?> date_col;

    private Stage stage;
        private Myconnection conn;
    public ObservableList<Reclamation> reclamations;
    private ResultSet rs;
        private PreparedStatement pst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      conn = Myconnection.getInstance();
        
        try {
            InitReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(PageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      

    public void InitReclamation() throws SQLException {
        conn = Myconnection.getInstance();
               

        try {

            reclamations = FXCollections.observableArrayList();
            String sql = "SELECT * FROM reclamation WHERE statu='Traitée' ORDER BY dateReclamation ASC";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = Myconnection.getInstance();
                //data.addAll(data);
                       {

                reclamations.add(new Reclamation(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }}
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        sujet_col.setCellValueFactory(new PropertyValueFactory<>("sujetReclamation"));
        statu_col.setCellValueFactory(new PropertyValueFactory<>("statu"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("descriptionReclamation"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));

        //Table_reclam.setItems(null);
        Table_reclam.setItems(reclamations);
    }
   @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/gui/ReclamFront.fxml"));
        Scene c = new Scene(root);

        s1.setScene(c);
        s1.centerOnScreen();
        s1.show();

    }



    @FXML
   public void search(ActionEvent t) {
                ObservableList data = Table_reclam.getItems();
        tfsearchbyid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                Table_reclam.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Reclamation> subentries = FXCollections.observableArrayList();

            long count = Table_reclam.getColumns().stream().count();
            for (int i = 0; i < Table_reclam.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + Table_reclam.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(Table_reclam.getItems().get(i));
                        break;
                    }
                }
            }
            Table_reclam.setItems(subentries);

        });
    }

    @FXML
    public void ajouteclamation(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) ajout.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/gui/ReclamAjout.fxml")); //
            Parent root = (Parent) Loader.load();
            //scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("ADD");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    public boolean DeleteReclam(ActionEvent t) throws SQLException {
  conn = Myconnection.getInstance();

        int selectedIndex = Table_reclam.getSelectionModel().getSelectedIndex();
        Reclamation m = (Reclamation) Table_reclam.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getId()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = Myconnection.getInstance();
                String query1 = "DELETE FROM reclamation WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_reclam.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }


    

    public void recl(ActionEvent t) throws SQLException {

        try {
           
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/gui/ReclamFront.fxml")); //
            Parent root =Loader.load();
            ReclamFrontController r = Loader.getController();
            reclam.getScene().setRoot(root);
        
           
        } catch (IOException ex) {
            Logger.getLogger(PageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
