/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.DocumentException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
import utils.myconnexion;

/**
 *
 * @author Kenza
 */
public class excel {

    public void e_bookexcel() throws FileNotFoundException, DocumentException, MalformedURLException, IOException, SQLException, WriteException {
        WritableWorkbook myFirstWbook = null;
        String requete = "SELECT * FROM e_books";
        Connection cx = myconnexion.getInstance().getCnx();
        Statement st = cx.createStatement();
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
        workbook = Workbook.createWorkbook(new File("Liste des e_books.xls"));
        WritableSheet ws1 = workbook.createSheet("Liste : ", 0);
        WritableCellFormat cellFormat3 = new WritableCellFormat();
        cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.WHITE);
        java.io.File imageFile = new java.io.File("C:\\Users\\Kenza\\Desktop\\cours\\semestre2\\PIdEV\\Meliora-java\\meliora\\meliora\\src\\images\\150182275_2876778865978663_6412466972648118753_n (1).png");
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

        System.out.println("Excel file created.");

    }
}
