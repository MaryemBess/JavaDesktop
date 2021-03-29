/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CONNECTION.Myconnection;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author MaryemBessrour
 */
public class sendingCode {
int randomCode;
    public sendingCode() {
    }
    
    public void Sendcode(String email) throws AddressException, MessagingException{
        PreparedStatement pst, pst2;
        ResultSet rs, rs2;
        Myconnection con;
        
        
            String req = "SELECT * from user where email=? ";
            try {
                pst2 = Myconnection.getInstance().getCnx().prepareStatement(req);
                pst2.setInt(1, randomCode);
                pst2.setString(2, email);
                pst2.executeQuery();

            } catch (SQLException ex) {
                Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);

Random rand = new Random();
randomCode=rand.nextInt(999999);
System.out.println("code " + randomCode + " ");
            String host = "smtp.gmail.com";
            String user = "mimibessrour@gmail.com";
            String pass = "ALLinone1723";
            String to = email;
            String from = "mimibessrour@gmail.com";
            String messageText = "This is confirmation number for your expert programming account. Please insert this number to activate your account = " + randomCode + " Thanks for being one of us ";
            String subject = "Fixit Email Confirmation ";
            boolean sessionDebug = true;

            Properties props = System.getProperties();
 props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
}
        
        
        
        
    }

