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
import model.dao.FornecedorDao;

/**
 *
 * @author Naine
 */
public class Fornecedores {
    private String razao_social;
    private String cnpj;
    private String nome_fornecedor;
    private String insc_estadual;
    private String cep;
    private String telefone;

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInsc_estadual() {
        return insc_estadual;
    }

    public void setInsc_estadual(String insc_estadual) {
        this.insc_estadual = insc_estadual;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString(){
        return getRazao_social();
}
    
     public static void gerarPdfFornecedores(){
     FornecedorDao fdao = new FornecedorDao();
     Document doc = new Document();
     List <Fornecedores> listf = fdao.read();
     String arquivoPdf = "relatoriofornecedores.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Fornecedores");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(6);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Razão Social"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Cnpj"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Nome"));
         PdfPCell cel4 = new PdfPCell(new Paragraph ("Ins Estadual"));
         PdfPCell cel5 = new PdfPCell(new Paragraph ("Telefone"));
         PdfPCell cel6 = new PdfPCell(new Paragraph ("CEP"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         table.addCell(cel6);
         
         for (Fornecedores fornecedores : listf){
         cel1 = new PdfPCell(new Paragraph (fornecedores.getRazao_social()+""));
         cel2 = new PdfPCell(new Paragraph (fornecedores.getCnpj()+""));
         cel3 = new PdfPCell(new Paragraph (fornecedores.getNome_fornecedor()+""));
         cel4 = new PdfPCell(new Paragraph (fornecedores.getInsc_estadual()+""));
         cel5 = new PdfPCell(new Paragraph (fornecedores.getTelefone()+""));
         cel6 = new PdfPCell(new Paragraph (fornecedores.getCep()+""));
         
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         table.addCell(cel6);
         }
         doc.add(table);
         doc.close();
         Desktop.getDesktop().open(new File(arquivoPdf));
         
     }catch (Exception e){
     
     }
     
}
}
