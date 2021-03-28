/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import utils.myconnexion;

/**
 *
 * @author Kenza
 */
public class QRcode {
      
    public void QRcode(){
    try {
        Connection cx =myconnexion.getInstance().getCnx();
        String query = "SELECT * FROM e_books";
        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
            	QRcode.generate_qr("Ebook_n°"+rs.getInt("id"), "e_book n "+rs.getInt("id")+" ,De "+rs.getString("auteur")+" le titre :"+rs.getString("titre")+" .");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\Users\\Kenza\\Desktop\\cours\\semestre2\\PIdEV\\Meliora-java\\meliora\\Document\\"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > hintMap = new HashMap < com.google.zxing.EncodeHintType, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel > ();
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
}
