package Controllers;

import CONNECTION.DataSource;
import Entity.Admin;

import Entity.Coach;
import Entity.Client;
import Services.SendingMail;
import Services.Service_Admin;
import static Services.Service_Admin.currentUser;
import Services.Service_Client;
import Services.Service_Coach;
import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.BoxBlur;

import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author maryem
 */
public class SignUpController implements Initializable {

    private JFXTextField firstName;
    private JFXTextField lastName;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phoneNumber;
    @FXML
    private DatePicker birthDate;
    private JFXTextField city;
    private JFXTextField address;
    private JFXTextField zipCode;
    @FXML
    private JFXPasswordField password;
    @FXML
    private TextField filename;
    @FXML
    private Label passwordStrength;
    private Label Error_output;
//    private static final Effect frostEffect
//            = new BoxBlur(10, 10, 3);
    private Circle Avatar;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXButton btnback;
    @FXML
    private JFXTextField tfidU;
    @FXML
    private JFXTextField tfname;
    private DataSource conn;
    private PreparedStatement pst = null;
    // private Connection conn;
    private String sql;
    ResultSet rs;
    @FXML
    private ImageView aaa;
    private FileInputStream fis;
    @FXML
    private Label ImagePath;
    @FXML
    private ComboBox<String> cbAS;
    private ObservableList<String> edit = FXCollections.observableArrayList("Client", "Coach", "Admin");
    @FXML
    private JFXTextField addressTf;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbAS.setItems(edit);
        cbAS.getSelectionModel().selectFirst();
        conn = DataSource.getInstance();
        password.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                updatePasswordStrength(newValue);
            } else {
                passwordStrength.setText("");
            }
        });
        filename.setEditable(false);
        birthDate.setEditable(false);

        try {
            autoOrderNOP();

            //  tfidU.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tfidU.setText("" + autoOrderNOP());
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static boolean copyFileUsingStream(File source, File dest) throws Exception {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
        return true;
    }

    @FXML
    private void chooseProfilePicture(ActionEvent event) throws Exception {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagepath = file.toURI().toURL().toString();
            System.out.println("file:" + imagepath);
            Image image = new Image(imagepath);
            System.out.println("height:" + image.getHeight() + "\nWidth:" + image.getWidth());
            aaa.setImage(image);
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
          
            alert.showAndWait();
        }

    }
    private static final String[] VALID_EXTENSIONS = new String[]{".png", ".jpg", "jpeg", "gif", "bmp"};

    final String newStyle = "-fx-background-radius: 50em;\n"
            + "    -fx-min-width: 50px;\n"
            + "    -fx-min-height: 50px; \n"
            + "    -fx-max-width: 50px;\n"
            + "    -fx-max-height: 50px;\n"
            + "    -fx-background-color: #6fb52c;\n"
            + "    -fx-text-fill: #ffffff;\n"
            + "    -fx-border-color: #ffffff;\n"
            + "    -fx-border-width: 2px; \n"
            + "    -fx-background-insets:0;"
            + "    -fx-border-radius: 50em;";

    private boolean isPasswordValid(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    private boolean isEmailValid(String email) {
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return email.matches(pattern);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String pattern = "^[0-9]{8}$";
        return phoneNumber.matches(pattern);
    }

    private boolean isFormFilled() {
        if (tfidU.getText().equals("") || tfname.getText().equals("") || password.getText().equals("")
                || email.getText().equals("") || phoneNumber.getText().equals("") || birthDate.getValue() == null
                || addressTf.getText().equals("") || filename.getText().equals("")) {
            return false;
        }
        return true;
    }

    private int calculatePasswordStrength(String password) {

        //total score of password
        int iPasswordScore = 0;

        if (password.length() < 8) {
            return 0;
        } else if (password.length() >= 10) {
            iPasswordScore += 2;
        } else {
            iPasswordScore += 1;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            iPasswordScore += 2;
        }

        return iPasswordScore;

    }

    private boolean isFormValid() {
        String errorLog = "";
        boolean flag = true;
        if (!isFormFilled()) {
            errorLog += "* You must fill all required fields ( * ) !\n";
            flag = false;
        } else {
            if (!isPasswordValid(password.getText())) {
                errorLog += "* Invalid password !\n";
                flag = false;
            }
            if (!isEmailValid(email.getText())) {
                errorLog += "* Invalid email !\n";
                flag = false;
            }
            if (!isPhoneNumberValid(phoneNumber.getText())) {
                errorLog += "* Invalid phone number !\n";
                flag = false;
            }
        }
        Error_output.setText(errorLog);
        return flag;
    }

    private void updatePasswordStrength(String value) {
        if (calculatePasswordStrength(value) < 5) {
            passwordStrength.setText("( weak )");
            passwordStrength.setTextFill(Color.web("#ff0505"));
        } else if (calculatePasswordStrength(value) == 5) {
            passwordStrength.setText("( average )");
            passwordStrength.setTextFill(Color.web("#ed701b"));
        } else if (calculatePasswordStrength(value) >= 8) {
            passwordStrength.setText("( strong )");
            passwordStrength.setTextFill(Color.web("#6fb52c"));
        }
    }

    public boolean validateNomP() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(tfname.getText());
        if (m.find() && m.group().equals(tfname.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillP() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validatePasswordP() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (password.getText().matches(pattern)) {

            if (password.getText().equals(password.getText())) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Check your password confirmation");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateDateP() {
        if (birthDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;
        }

        Date date = java.sql.Date.valueOf(birthDate.getValue());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Calendar c = Calendar.getInstance();
        if (c.getTime().after(date) == true && c.getTime().equals(date) == false) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;

        }
    }

    public boolean validatePasswordC() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (password.getText().matches(pattern) && password.getText().equals(password.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }

    }

    @FXML
    private void CreateAccount(ActionEvent event) throws SQLException, FileNotFoundException, MalformedURLException, IOException, MessagingException {
        SendingMail sm = new SendingMail();
        conn = DataSource.getInstance();
        String img = ImagePath.getText();
        img = img.replace("//", "////");
        String aa = "not yet";
        String sql = "insert into user (id,username,email,password,roles,dateNai,adresse,tel) values (?,?,?,?,?,?,?,?)";
        //  if(isFormFilled()){
        if (isFormFilled() == true && validateEmaillP() || validateDateP() == true || isPhoneNumberValid(newStyle) || validateNomP() || validatePasswordC()) {
            Integer.valueOf(tfidU.getText());
            String name = tfname.getText();
            String mail = email.getText();
            String pswd = password.getText();
        pswd = BCrypt.hashpw(pswd, BCrypt.gensalt(13));
        pswd= pswd.replaceFirst("a", "y");
        
            Integer.valueOf(phoneNumber.getText());

            // String birthDate = this.birthDate.getText();
            String address = addressTf.getText();
            Date.valueOf(birthDate.getValue());

            try {
                pst = conn.getCnx().prepareStatement(sql);
                pst.setInt(1, Integer.valueOf(tfidU.getText()));
                pst.setString(2, name);
           
                pst.setString(3, mail);

                pst.setString(4, pswd);
                pst.setInt(8, Integer.valueOf(phoneNumber.getText()));
                pst.setDate(6, Date.valueOf(birthDate.getValue()));
                pst.setString(7, address);
                //pst.setString(10, img);
                // pst.setString(10,String.valueOf(cbAS.getSelectionModel().getSelectedItem()) );
                if (cbAS.getSelectionModel().getSelectedIndex() == 0) {
                    pst.setString(5, "Client");
                } else if (cbAS.getSelectionModel().getSelectedIndex() == 1) {
                    pst.setString(5, "Coach");
                } else {
                    pst.setString(5, "Admin");

                }

                int i = pst.executeUpdate();
                if (i == 1) {

                    sm.SendEmail(mail);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registration confirmation");
                    alert.setHeaderText("Successful registration");
                    alert.setContentText("A verification email has been sent, please verify your account before logging in");
                    alert.showAndWait();

                    Services.Service_Client c = new Service_Client();
                    Services.Service_Coach f = new Service_Coach();
                    Services.Service_Admin a = new Service_Admin();
                    Coach f1 = new Coach();
                    Client c1 = new Client();
                    Admin a1 = new Admin();
                    Services.SendingMail ssm = new SendingMail();
                    String MAIL = email.getText();
                    String pswd1 = password.getText();
                    f1.setEmail(MAIL);
                    f1.setPassword(pswd1);
                    c1.setEmail(MAIL);
                    c1.setPassword(pswd);
                    a1.setEmail(MAIL);
                    a1.setPassword(pswd);
                    int verif = c.SingIn(c1);
                    int verif1 = f.SingIn(f1);
                    int verif2 = a.SingIn(a1);
                    TextInputDialog dialog = new TextInputDialog("Votre code");

                    dialog.setTitle("Verification code");
                    dialog.setHeaderText("Please insert the code  \n that was sent to your mailbox ");
                    dialog.setContentText("Please enter your code:");
                    Optional<String> result = dialog.showAndWait();

                    if (result.isPresent()) {
                        System.out.println("Your code: " + result.get());
                        if (f.VerifierCompte(f1, result.get()) == 1) {
                            System.out.println("********" + f.VerifierCompte(f1, result.get()));
                            Alert A1 = new Alert(Alert.AlertType.CONFIRMATION, "Activate" + currentUser.getUsername() + "?");
                            A1.setTitle("Account information");
                            A1.setHeaderText("Compte activé");
                            A1.setContentText("Please enter your details again to log in");
                            A1.showAndWait();
                            dialog.close();
                        } else if (c.VerifierCompte(c1, result.get()) == 1) {
                            Alert A1 = new Alert(Alert.AlertType.CONFIRMATION, "Activate" + currentUser.getUsername() + "?");
                            A1.setTitle("Account information");
                            A1.setHeaderText("Activation account");
                            A1.setContentText("Please enter your details again to log in");
                            A1.showAndWait();
                            dialog.close();
                        } else if (a.VerifierCompte(a1, result.get()) == 1) {
                            Alert A1 = new Alert(Alert.AlertType.CONFIRMATION, "Activate" + currentUser.getUsername() + "?");
                            A1.setTitle("Account information");
                            A1.setHeaderText("Activation account");
                            A1.setContentText("Please enter your details again to log in");
                            A1.showAndWait();
                            dialog.close();
                        }

                    }
                } else {
                    Services.Service_Client chasseur = new Service_Client();
                    Services.Service_Coach pecheur = new Service_Coach();
                    Services.Service_Admin admin = new Service_Admin();
                    Admin ad = new Admin();
                    Coach chas = new Coach();
                    Client pecheur1 = new Client();
                    int verif = chasseur.SingIn(pecheur1);
                    int verif1 = pecheur.SingIn(chas);
                    int verif2=admin.SingIn(ad);
                    
                    if (verif == 1 || verif1 == 1 || verif2 == 1) {
                        String mm = email.getText();
                        String pass = password.getText();
                        String requete = "SELECT * FROM user Where password =? and roles= ? and email = ?  ";
                        pst = conn.getCnx().prepareStatement(requete);

                        pst.setString(1, pass);
                        pst.setString(2, currentUser.getRoles());
                        pst.setString(3, mm);
                        rs = pst.executeQuery();
                        while (rs.next()) {
                            rs.getString(1);
                            rs.getInt(8);
                        }

                        if (rs.getInt(6) == 1) {
                            Alert A2 = new Alert(Alert.AlertType.INFORMATION);
                            A2.setTitle("Account Information");
                            A2.setHeaderText("Account already activated");
                            A2.setContentText("connect directly");
                            A2.showAndWait();
                        } else {
                            System.out.println("hhhhhhhhhh");
                        }

            rs = pst.executeQuery();
                    }

                }
            } catch (SQLException ex) {
                System.out.println(ex);

            } finally {
                pst.close();
                BackAutomaticly(event);
                //}
            }

        }
    }

    private void clearFields() {
        tfidU.setText(null);
        tfname.setText(null);
        email.setText(null);
        password.setText(null);
        birthDate.setValue(null);
        addressTf.setText(null);
        phoneNumber.setText(null);

    }

    private int autoOrderNOP() throws SQLException {
        int nop = 0;
        try {
            conn = DataSource.getInstance();

            String sql2 = "select max(id) from user";
            pst = conn.getCnx().prepareStatement(sql2);
            rs = pst.executeQuery();
            if (rs.next()) {
                nop = rs.getInt(1);

            }

            nop++;
            pst.close();
            rs.close();

        } catch (SQLException e) {
        }
        return nop;

    }

    private void BackAutomaticly(ActionEvent event) throws IOException {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/GUI/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex2) {
            //TODO:handle exception 
            System.out.println("Error :" + ex2.getMessage());
        }
    }


}
