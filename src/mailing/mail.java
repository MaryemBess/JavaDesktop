/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailing;

//import com.sun.xml.internal.bind.v2.Messages;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author SAFA
 */
public class mail {

    public static void SendMail(String recipient,String videoTitle, int videodislikes) throws Exception {
        System.out.println("preparing to send mail");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="safa97kaabi@gmail.com";
        String password= "09629895safa";
        
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(myAccountEmail,password); //To change body of generated methods, choose Tools | Templates.
            }
    });
    Message message= prepareMessage(session,myAccountEmail,recipient,videoTitle,videodislikes);   

        Transport.send(message);
        System.out.println(" mail sent successfuly");
    }

    private static Message prepareMessage(Session session , String myAccountEmail,String recipient,String videoTitle,int videodislikes) {
    
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Warning !");
            message.setText("Veuillez s'il vous améliorer votre contenu pour satisfaire nos clients.\n"
                    + "Votre video intitulée "+videoTitle+" a eu "+videodislikes+" dislikes");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
}