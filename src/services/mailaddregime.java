/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Aliment;
import Entity.Regime;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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
import java.lang.Object;
import javafx.collections.ObservableList;





/**
 *
 * @author ASUS
 */
public class mailaddregime {
    
    public static void sendMail(String recepient,Aliment al) throws Exception{
        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "465");

                       Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {

                                return new PasswordAuthentication("testindicateur@gmail.com", "dorrafiras");
                            }
                        }
                        );
                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("testindicateur@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
                            message.setSubject("Aliment Ajouter!");
                            message.setText("MAIL BODY !");
                            Multipart emailContent = new MimeMultipart();

                            MimeBodyPart textBodyPart = new MimeBodyPart();
                            textBodyPart.setText("merci pour avoir ajouter un aliment\n merci pur votre contribution");
                            
                            
                            pdfGenerator a = new pdfGenerator();
                            a.genPdf(al);
                             MimeBodyPart pdfAttachment = new MimeBodyPart();
                            pdfAttachment.attachFile("D:\\esprit\\piedev\\meliora\\pdf\\pdfaliment.pdf");
                            emailContent.addBodyPart(textBodyPart);
                            emailContent.addBodyPart(pdfAttachment);
                            message.setContent(emailContent);
                            Transport.send(message);   
                            System.out.println("email creer");
                            
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
    }
    
    public static void sendMailRegime(String recepient,Regime rg,ObservableList als) throws Exception{
        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "465");

                       Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {

                                return new PasswordAuthentication("testindicateur@gmail.com", "dorrafiras");
                            }
                        }
                        );
                        try {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("testindicateur@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
                            message.setSubject("Regime Ajouter!");
                            message.setText("MAIL BODY !");
                            Multipart emailContent = new MimeMultipart();

                            MimeBodyPart textBodyPart = new MimeBodyPart();
                            textBodyPart.setText("merci pour avoir ajouter un Regime\n merci pur votre contribution");
                            
                            
                            pdfGenerator a = new pdfGenerator();
                            a.genPdf2(rg,als);
                             MimeBodyPart pdfAttachment = new MimeBodyPart();
                            pdfAttachment.attachFile("D:\\esprit\\piedev\\meliora\\pdf\\pdfaliment.pdf");
                            emailContent.addBodyPart(textBodyPart);
                            emailContent.addBodyPart(pdfAttachment);
                            message.setContent(emailContent);
                            Transport.send(message);   
                            System.out.println("email creer");
                            
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
    }
}