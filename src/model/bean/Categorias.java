/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import model.dao.CategoriaDao;
import model.dao.ClienteDao;

/**
 *
 * @author Naine
 */
public class Categorias {
    private String codigo_categoria;
    private String nome_categoria;

    public String getCodigo_categoria() {
        return codigo_categoria;
    }

    public void setCodigo_categoria(String codigo_categoria) {
        this.codigo_categoria = codigo_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public void add(Categorias categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString(){
        return getNome_categoria();
}
     public static void gerarPdfCategorias(){
     CategoriaDao cdao = new CategoriaDao();
     Document doc = new Document();
     List <Categorias> listc = cdao.read();
     String arquivoPdf = "relatoriocategorias.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Categorias");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(2);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código Categoria"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Nome"));
         
         table.addCell(cel1);
         table.addCell(cel2);

         for (Categorias categorias : listc){
         cel1 = new PdfPCell(new Paragraph (categorias.getCodigo_categoria()+""));
         cel2 = new PdfPCell(new Paragraph (categorias.getNome_categoria()+""));
         
         table.addCell(cel1);
         table.addCell(cel2);
         }
         doc.add(table);
         doc.close();
         Desktop.getDesktop().open(new File(arquivoPdf));
         
     }catch (Exception e){
     
     }
     
}
}
