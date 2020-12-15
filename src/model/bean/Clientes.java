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
import model.dao.ClienteDao;
import model.dao.ProdutoDao;

/**
 *
 * @author Naine
 */
public class Clientes {
    private String nome_cliente;
    private String cnpj;
    private String cod_cliente;

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

@Override
    public String toString(){
        return getNome_cliente();
    }
     
    public static void gerarPdfClientes(){
     ClienteDao cdao = new ClienteDao();
     Document doc = new Document();
     List <Clientes> listc = cdao.read();
     String arquivoPdf = "relatorioclientes.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Clientes");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(3);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código Cliente"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Nome"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Cnpj"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         
         for (Clientes clientes : listc){
         cel1 = new PdfPCell(new Paragraph (clientes.getCod_cliente()+""));
         cel2 = new PdfPCell(new Paragraph (clientes.getNome_cliente()+""));
         cel3 = new PdfPCell(new Paragraph (clientes.getCnpj()+""));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         }
         doc.add(table);
         doc.close();
         Desktop.getDesktop().open(new File(arquivoPdf));
         
     }catch (Exception e){
     
     }
     
}
}
