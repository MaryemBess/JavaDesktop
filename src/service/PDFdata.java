/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entite.e_book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author Kenza
 */
public class PDFdata {

    public void e_bookPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException {
        Document document = new Document();
        e_bookCRUD rm = new e_bookCRUD();
        List<e_book> res = rm.gete_bookListBack();
        PdfWriter.getInstance(document, new FileOutputStream(new File("e_book.pdf")));
        document.open();
        PDFdata pdf = new PDFdata();
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
            Paragraph citation = new Paragraph("numero de la citation : " + r.getId_c());
            citation.setAlignment(Element.ALIGN_LEFT);
            document.add(Auteur);
            document.add(Titre);
            document.add(Genre);
            document.add(citation);
            document.add(image);

        }
        document.close();
    }

    
}
