/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import CONNECTION.Myconnection;
import Entity.e_book;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Kenza
 */
public class E_bookService {
    Connection cx = Myconnection.getInstance().getCnx();
    Statement st;
    ObservableList<e_book> oc = FXCollections.observableArrayList();
    private static final String FROM = "meliora.project2021@gmail.com";
    private static final String PASSWORD = "PiDev2021";

    public void ajouterBook(e_book book) {

        String rqt = "insert into e_books (auteur,genre,favoris,titre,evaluation,id_c,image) values(?,?,?,?,?,?,?)";//précompilé

        try {
            PreparedStatement pst = cx.prepareStatement(rqt);
            //pst.setInt(1, book.getId());
            pst.setString(1, book.getAuteur());
            pst.setString(2, book.getGenre());
            pst.setInt(3, book.getFav());
            pst.setString(4, book.getTitre());
            pst.setInt(5, book.getEvaluation());
            pst.setInt(6, book.getId_c());
            pst.setString(7, book.getImage());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR AJOUT");
            System.out.println(ex.getMessage());
        }

    }

    public void modifieBook(e_book book) {
        try {
            String req = "update e_books set id=?,auteur=?,genre=?,favoris=?,titre=?,evaluation=?,id_c=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setInt(1, book.getId());
            pst.setString(2, book.getAuteur());
            pst.setString(3, book.getGenre());
            pst.setInt(4, book.getFav());
            pst.setString(5, book.getTitre());
            pst.setInt(6, book.getEvaluation());
            pst.setInt(7, book.getId_c());
            pst.setInt(8, book.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiAuteur(int id, String auteur) {
        try {
            String req = "update e_books set auteur=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, auteur);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE Auteur");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiTitre(int id, String titre) {
        try {
            String req = "update e_books set titre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, titre);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE TITRE");
            System.out.println(ex.getMessage());
        }
    }

    public void modifiGenre(int id, String genre) {
        try {
            String req = "update e_books set genre=? where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
            pst.setString(1, genre);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR UPDATE GENRE");
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerBook(int id_book) {
        try {
            String req = "delete from e_books where Id =?";
            PreparedStatement ps = cx.prepareStatement(req);
            ps.setInt(1, id_book);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERREUR SUPPRESSION");
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList afficherBook() {
        String req = " select e.id ,e.auteur,e.genre ,e.favoris,e.titre,e.evaluation,e.id_c,e.image,c.auteur ,c.text from e_books e INNER JOIN citations c on e.id_c =c.id ";
        Statement st;
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e_book e = new e_book(1, "", "", "", 1, 1, 1, "", "");
                e.setId(rs.getInt(1));
                e.setAuteur(rs.getString(2));
                e.setGenre(rs.getString(3));
                e.setFav(rs.getInt(4));
                e.setTitre(rs.getString(5));
                e.setEvaluation(rs.getInt(6));
                e.setId_c(rs.getInt(7));
                e.setImage(rs.getString(8));
                e.setCitat(rs.getString(9) + " : " + rs.getString(10));
                oc.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("ERREUR AFFICHAGE");
            System.out.println(ex.getMessage());
        }
        return oc;
    }

    public ObservableList<e_book> gete_bookListBack() {
        ObservableList<e_book> list = FXCollections.observableArrayList();
        String req = "SELECT * from e_books";
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                e_book r = new e_book(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"), rs.getString("genre"), rs.getInt("id_c"), rs.getString("image"));
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Probléme list PDF");
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void nombreVue(e_book book) {
        try {

            String req = "update e_books set favoris = ( favoris + " + 1 + ") where id=?";
            PreparedStatement pst = cx.prepareStatement(req);
//            int x=book.getFav()+1;
//            pst.setInt(1, x);
            pst.setInt(1, book.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERREUR incrementer nombre de vue");
            System.out.println(ex.getMessage());
        }
//        JOptionPane.showMessageDialog(null, "increment DONE!");
    }

    public ObservableList<PieChart.Data> getDataStat() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        String req = "SELECT titre,evaluation, favoris from e_books ";
        try {
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("titre"), rs.getInt("favoris")));
            }
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public void QRcode() {
        try {

            String query = "SELECT * FROM e_books";
            st = cx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                E_bookService.generate_qr("Ebook_n°" + rs.getInt("id"), "e_book n " + rs.getInt("id") + " ,De " + rs.getString("auteur") + " le titre :" + rs.getString("titre") + " .");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void generate_qr(String image_name, String qrCodeData) {
        try {
            String filePath = "C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\document\\" + image_name + ".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map< com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel> hintMap = new HashMap< com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel>();
            hintMap.put(com.google.zxing.EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
            com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void e_bookPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException {
        Document document = new Document();
        E_bookService rm = new E_bookService();
        List<e_book> res = rm.gete_bookListBack();
        PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\document\\e_bookPDF.pdf")));
        document.open();

        for (e_book r : res) {
            Paragraph reservation = new Paragraph("E_book n°" + r.getId());
            reservation.setAlignment(Element.ALIGN_CENTER);
            document.add(reservation);
            Paragraph Auteur = new Paragraph("Auteur : " + r.getAuteur());
            Auteur.setAlignment(Element.ALIGN_LEFT);
            Paragraph Titre = new Paragraph("Titre : " + r.getTitre());
            Titre.setAlignment(Element.ALIGN_LEFT);
            Paragraph Genre = new Paragraph("Genre: " + r.getGenre());
            Genre.setAlignment(Element.ALIGN_LEFT);
            Paragraph image = new Paragraph("URL de l'image : " + r.getImage());
            image.setAlignment(Element.ALIGN_LEFT);
            Image img = Image.getInstance(r.getImage() + "");
            img.setBorderColor(BaseColor.BLACK);
            img.getIndentationRight();
            img.scaleToFit(50f, 800f);
            Paragraph citation = new Paragraph("numero de la citation : " + r.getId_c());
            citation.setAlignment(Element.ALIGN_LEFT);
            document.add(Auteur);
            document.add(Titre);
            document.add(Genre);
            document.add(citation);
            document.add(image);
            document.add(img);
        }
        document.close();
    }

    public void e_bookexcel() throws FileNotFoundException, DocumentException, MalformedURLException, IOException, SQLException, WriteException {
        WritableWorkbook myFirstWbook = null;
        String requete = "SELECT * FROM e_books";
        st = cx.createStatement();
        ResultSet rs = st.executeQuery(requete);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableWorkbook workbook = Workbook.createWorkbook(baos);

        // * Create Font ***//
        WritableFont fontBlue = new WritableFont(WritableFont.TIMES, 10);
        fontBlue.setColour(Colour.BLUE);

        WritableFont fontRed = new WritableFont(WritableFont.TIMES, 10);
        fontRed.setColour(Colour.RED);

        WritableFont fontWhite = new WritableFont(WritableFont.TIMES, 10);
        fontRed.setColour(Colour.WHITE);

        // * Sheet 1 ***//
        workbook = Workbook.createWorkbook(new File("C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\document\\Liste des e_books.xls"));
        WritableSheet ws1 = workbook.createSheet("Liste : ", 0);
        WritableCellFormat cellFormat3 = new WritableCellFormat();
        cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.WHITE);
        java.io.File imageFile = new java.io.File("C:\\Users\\Kenza\\Desktop\\Meliora-java-melioraa2\\Meliora-java-meliora\\src\\images\\images\\150182275_2876778865978663_6412466972648118753_n (1).png");
        BufferedImage input = ImageIO.read(imageFile);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(input, "PNG", bao);
        ws1.addImage(new WritableImage(0, 0, 4, 6, bao.toByteArray()));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(0, 0, "", cellFormat3));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(0, 1, "", cellFormat3));
        ws1.setColumnView(2, 10);
        ws1.addCell(new jxl.write.Label(0, 2, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(0, 3, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(0, 4, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(0, 5, "", cellFormat3));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(1, 0, "", cellFormat3));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(1, 1, "", cellFormat3));
        ws1.setColumnView(2, 10);
        ws1.addCell(new jxl.write.Label(1, 2, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(1, 3, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(1, 4, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(1, 5, "", cellFormat3));

        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(2, 0, "", cellFormat3));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(2, 1, "", cellFormat3));
        ws1.setColumnView(2, 10);
        ws1.addCell(new jxl.write.Label(2, 2, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(2, 3, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(2, 4, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(2, 5, "", cellFormat3));

        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(3, 0, "", cellFormat3));
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(3, 1, "", cellFormat3));
        ws1.setColumnView(2, 10);
        ws1.addCell(new jxl.write.Label(3, 2, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(3, 3, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(3, 4, "", cellFormat3));
        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(3, 5, "", cellFormat3));

        ///
        // * Header ***//
        WritableCellFormat cellFormat1 = new WritableCellFormat(fontWhite);
        cellFormat1.setBackground(Colour.TAN);
        cellFormat1.setAlignment(Alignment.CENTRE);
        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLUE2);

        // * Data ***//
        WritableCellFormat cellFormat2 = new WritableCellFormat(fontBlue);

        // cellFormat2.setWrap(true);
        cellFormat2.setBackground(Colour.WHITE);
        cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
        cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat2.setWrap(true);
        cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLUE2);

        // * Header ***//
        ws1.setColumnView(0, 10);
        ws1.addCell(new jxl.write.Label(0, 6, "Id", cellFormat1));

        ws1.setColumnView(1, 15);
        ws1.addCell(new jxl.write.Label(1, 6, "Titre", cellFormat1));

        ws1.setColumnView(2, 10);
        ws1.addCell(new jxl.write.Label(2, 6, "Genre", cellFormat1));

        ws1.setColumnView(3, 10);
        ws1.addCell(new jxl.write.Label(3, 6, "Auteur", cellFormat1));

        int iRows = 7;
        while ((rs != null) && (rs.next())) {
            ws1.addCell(new jxl.write.Label(0, iRows, rs.getString("id"), cellFormat2));
            ws1.addCell(new jxl.write.Label(1, iRows, rs.getString("titre"), cellFormat2));
            ws1.addCell(new jxl.write.Label(2, iRows, rs.getString("genre"), cellFormat2));
            ws1.addCell(new jxl.write.Label(3, iRows, rs.getString("auteur"), cellFormat2));

            ++iRows;
        }
        workbook.write();
        workbook.close();

//        System.out.println("Excel file created.");

    }

    public static void sendMail(String toMail, String subject, String message) throws AddressException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session mailSession = Session.getDefaultInstance(props, null);

        //enable the comment below to activate console debug
        mailSession.setDebug(true);

        Message mailMessage = new MimeMessage(mailSession);

        mailMessage.setFrom(new InternetAddress(FROM));
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        mailMessage.setContent(message, "text/html; charset=utf-8");
        mailMessage.setSubject(subject);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", FROM, PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

    }

    public static boolean textAlphabet(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isAlphabet = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-z A-Z]+")) {
            isAlphabet = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-z A-Z]"));
        return isAlphabet;

    }

    public static boolean textFieldIsNull(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;
        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;
        }
        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);
        inputLabel.setText(validationString);
        return isNull;
    }

    public void displayTray(String s) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();
        //If the icon is a file
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("image/flaviou.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Jardin d'enfant");
        tray.add(trayIcon);
        trayIcon.displayMessage("ajout avec succés", s, TrayIcon.MessageType.INFO);
    }
    
}
