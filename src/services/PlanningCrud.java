/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author htc
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entity.Planning;

import CONNECTION.Myconnection;
import Entity.Planningcreer;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author htc
 */
public class PlanningCrud {

    public ObservableList<Planning> lister(int c) {
        ObservableList<Planning> list1 = FXCollections.observableArrayList();
        String requete = "SELECT id_p,id_u,date_ajout,nom_planning,nb_tache FROM planning_user where id_u='"+c+"'";
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list1.add(new Planning(rs.getInt("id_p"), rs.getInt("id_u"), rs.getDate("date_ajout"), rs.getString("nom_planning"), rs.getInt("nb_tache")));
            }
            //ObservableList<Planning> listfinale=list1;
            System.out.println("lister planning");
        } catch (SQLException ex) {
            System.out.println("xxxxxCRUD PLANNING 1 ");
            System.out.println(ex.getMessage());
        } 
        for( Planning a : list1)
System.out.println(a.getNum_planning()+"/"+a.getId_u()+"/"+a.getDate_ajout()+"/"+ a.getNom_planning() +"/"+ a.getNb_tache());
         
        return list1;
    }

    public int getnbr_tache(int x) {
        int nbr_tache = 0;
        String requete = "SELECT count(*) FROM `liste_taches` WHERE `id_p` ='" + x + "'";
        try {
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            rs.next();
            nbr_tache = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("ereur calcul nbr tache");
            System.out.println(ex.getMessage());
        }

        return nbr_tache;
    }
 public int addPlanningc(Planningcreer p2,int idc_c) {
        int last_inserted_id = 0;
        int idc=idc_c;
        try {
            String requete1 = "INSERT INTO planning (id_U,description,id_c,date_creation) "
                    + "VALUES (1,?,'"+idc+"',CURRENT_DATE())";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete1, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, p2.getDescription());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                System.out.println("aa");
                last_inserted_id = rs.getInt(1);
                System.out.println(last_inserted_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("errerur ajout planning du coach");
        }

        return last_inserted_id;

    }
    public void supprimerp(int x) {
        try {
            String query = "DELETE FROM planning_user where id_p =" + x + "";
            PreparedStatement pst = new Myconnection().cnx.prepareStatement(query);
            System.out.println("baed l delete ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("aaaa delete");
        }

    }

    public int addPlanning(Planningcreer p2, Planning p1) {
        int last_inserted_id = 0;
        try {
            String requete1 = "INSERT INTO planning (id_U,description,date_creation) "
                    + "VALUES (1,?,CURRENT_DATE())";
            PreparedStatement pst
                    = new Myconnection().cnx.prepareStatement(requete1, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, p2.getDescription());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                last_inserted_id = rs.getInt(1);
            }
            String requete2 = "INSERT INTO planning_user (id_p,id_u,nom_planning,date_ajout) "
                    + "VALUES (" + last_inserted_id + ",1,?,CURRENT_DATE())";
            PreparedStatement pst2
                    = new Myconnection().cnx.prepareStatement(requete2);
            pst2.setString(1, p1.getNom_planning());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("errerur ajout planning");
        }

        return last_inserted_id;

    }
    public void addPlanning1(int x, Planning p1) {
        try {

            String requete2 = "INSERT INTO planning_user (id_p,id_u,nom_planning,date_ajout) "
                    + "VALUES (" + x + ",1,?,CURRENT_DATE())";
            PreparedStatement pst2
                    = new Myconnection().cnx.prepareStatement(requete2);
            pst2.setString(1, p1.getNom_planning());
            pst2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("add p nom errerur ajout planning");
        }

    }

    public String getmail(int b) throws DocumentException, SQLException, IOException {
        String x = "mail not sended";
int id_u = 0;
String email ="";
        // PFdddddddddffffffffffffffffffffffffffff
        try {
            String filename = "D:\\pdf\\dd.pdf";
            Document doucment = new Document();
            PdfWriter.getInstance(doucment, new FileOutputStream(filename));

            doucment.open();
             String req="select nom_tache,type_tache,date,id_t from liste_taches where id_p='"+b+"'";
            
            int i = 0;
             PreparedStatement pst = new Myconnection().cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
             rs=pst.executeQuery();
             //Req du get id_u
              String req2="select id_u from planning_user where id_p='"+b+"'";
              PreparedStatement pst2 = new Myconnection().cnx.prepareStatement(req2);
            ResultSet rs2=pst2.executeQuery();
             rs2=pst2.executeQuery();
             rs2.next();
            id_u= rs2.getInt(1);
            //Req du get  Email
             String req3="select email from user where id='"+id_u+"'";
              PreparedStatement pst3 = new Myconnection().cnx.prepareStatement(req3);
            ResultSet rs3=pst3.executeQuery();
             rs3=pst3.executeQuery();
             rs3.next();
            email= rs3.getString(1);
              while(rs.next()){
                  i++;
           
                  Paragraph para=new Paragraph(i+" : -->"+"nom du tache: "+rs.getString(1)+"----->Type du tache:"+rs.getString(2)+" /// date:"+rs.getTimestamp(3)+" /// numero:"+rs.getInt(4));
                doucment.add(para);
              }
         // Paragraph para = new Paragraph("aaaaaaaaaaaaaaa");
          

            doucment.close();
            JOptionPane.showMessageDialog(null, "ajout termine");
        } catch (FileNotFoundException ex) {
            System.out.println("erreur creation pdf");
        }
        System.out.println("aaaaaaaaaa");
        final String username = "khaled.abdelmoumen98@gmail.com";
        final String password = "21091520aAa";
        String fromEmail = "JAVA";
        String toEmail = email;

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
        System.out.println("aaaaaaaa2222aa");
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("ton programme que tu as creer maitenant");
            msg.setText("envoyé par l'application meliora");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Ton planning :");

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("D:\\pdf\\dd.pdf");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
            x = "mail sent ";
        } catch (MessagingException e) {
            System.out.println("errerur mail");
        }

        return x;
    }
    
    
    public void displayTray(String s ) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("image/flaviou.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Jardin d'enfant");
        tray.add(trayIcon);
        
        
        

        trayIcon.displayMessage("ajout avec succés", s, MessageType.INFO);
    }

}
