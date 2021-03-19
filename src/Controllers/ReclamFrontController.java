/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import CONNECTION.DataSource;
import Entity.Reclamation;
import Services.ServiceReclamation;
import static Services.Service_Admin.currentUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.ComboBox;
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
public class ReclamFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Reclamation> Table_reclam;
    @FXML
    private TableColumn<?, ?> sujet_col;
    @FXML
    private TableColumn<?, ?> statu_col;
    @FXML
    private TableColumn<?, ?> desc_col;
    @FXML
    private Button ajout;
        private DataSource conn;
    private PreparedStatement pst;
    private List<Reclamation> listrecl;
    private ResultSet rs;
    private Stage stage;

    
    public ObservableList<Reclamation> reclamations;
    @FXML
    private TableColumn<?, ?> date_col;
    @FXML
    private JFXButton refresh_btn;
    @FXML
    private JFXTextField tfsearchbyid;

    @FXML
    private Button supp;
    @FXML
    private Button modif;
   
    @FXML
    public void ajouteclamation(ActionEvent t) throws SQLException {

        try {
            stage = (Stage) ajout.getScene().getWindow(); //yhell window ki tenzel aal bouton
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/GUI/ReclamAjout.fxml")); //
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
  conn = DataSource.getInstance();

        int selectedIndex = Table_reclam.getSelectionModel().getSelectedIndex();
        Reclamation m = (Reclamation) Table_reclam.getSelectionModel().getSelectedItem();
        int tempItemTag = m.getId();

        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + m.getId()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                conn = DataSource.getInstance();
                String query1 = "DELETE FROM reclamation WHERE id=?";
                pst = conn.getCnx().prepareStatement(query1);
                pst.setInt(1, tempItemTag);
                pst.execute();
                Table_reclam.getItems().remove(selectedIndex);
            }
        }
        return true;
    
    }
  
   
    public void InitReclamation() throws SQLException {
        conn = DataSource.getInstance();
               

        try {

            reclamations = FXCollections.observableArrayList();
            String sql = "SELECT * FROM reclamation WHERE statu='En attente' ORDER BY dateReclamation ASC";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
                conn = DataSource.getInstance();
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
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
      conn = DataSource.getInstance();
        
        try {
            InitReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(PageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void refresh(ActionEvent event) throws IOException {
        
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage s1 = new Stage();
        FXMLLoader L = new FXMLLoader();
        Pane root = L.load(getClass().getResource("/GUI/ReclamFront.fxml"));
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




}
