/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Aliment;
import Entity.Regime;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class pdfGenerator {
    public void genPdf(Aliment al)
    {
       
    
        try {
            String filename="D:\\esprit\\piedev\\meliora\\pdf\\pdfaliment.pdf";
            Document document = new Document();
                 
            PdfWriter.getInstance(document, new FileOutputStream(filename));
        //document start here
            document.open();
            
            Paragraph para = new Paragraph("Votre Aliment a ete creer avec success \n la communite vous remerci pour votre contribution"+al.getLibelle()+"\n"+al.getRecette()+"\n"+al.getCalorie()
            +"\n"+al.getCarbs()+"\n"+al.getGras());
            document.add(para);
            document.close();
            System.out.println("document created console");
        //end of document
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
         public void genPdf2(Regime rg , ObservableList<Aliment> als)
    {
       
    
        try { 
            String filename="D:\\esprit\\piedev\\meliora\\pdf\\pdfaliment.pdf";
            Document document = new Document();
                 
            PdfWriter.getInstance(document, new FileOutputStream(filename));
        //document start here
            document.open();
            String p ="";
            for(Aliment al:als){
            p= " /n Libelle des Aliments :  "+ al.getLibelle()+"- \n Recette:"+al.getRecette()+"- \n"+String.valueOf(al.getCalorie())+"- \n"+String.valueOf(al.getCarbs())+"- \n"+String.valueOf(al.getGras())+"- \n";
            Paragraph par = new Paragraph(p);
            document.add(par);
            }
            Paragraph para = new Paragraph("Votre Regime a ete creer avec success \n la communite vous remerci pour votre contribution \n");
            document.add(para);
            document.close();
            System.out.println("document created console");
        //end of document
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(pdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
