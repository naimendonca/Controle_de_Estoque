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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.dao.ArmazenamentoDao;
import model.dao.ProdutoDao;


/**
 *
 * @author naine
 */
public class Armazenamento {
    private String cod_arm;
    private Produtos produto;
    private Fornecedores fornecedor;
    private Integer qtd;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    public String getCod_arm() {
        return cod_arm;
    }

    public void setCod_arm(String cod_arm) {
        this.cod_arm = cod_arm;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Fornecedores getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedores fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }


    /*public String produtoToString(){
        return getDescricao-();
    }*/
     @Override
    public String toString(){
        return getCod_arm()+" - "+getProduto()+" - Qtd:" +getQtd();
    }
    
   
 public static void gerarPdfEstoque(){
     ArmazenamentoDao adao = new ArmazenamentoDao();
     Document doc = new Document();
     List <Armazenamento> lista = adao.read();
     String arquivoPdf = "relatorioEstoque.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Estoque");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(5);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código Estoque"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Produto"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Fornecedor"));
         PdfPCell cel4 = new PdfPCell(new Paragraph ("Quantidade"));
         PdfPCell cel5 = new PdfPCell(new Paragraph ("Data"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         
         for (Armazenamento armazenamento : lista){
         cel1 = new PdfPCell(new Paragraph (armazenamento.getCod_arm()+""));
         cel2 = new PdfPCell(new Paragraph (armazenamento.getProduto()+""));
         cel3 = new PdfPCell(new Paragraph (armazenamento.getFornecedor()+""));
         cel4 = new PdfPCell(new Paragraph (armazenamento.getQtd()+""));
         cel5 = new PdfPCell(new Paragraph (armazenamento.getData()+""));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         }
         doc.add(table);
         doc.close();
         Desktop.getDesktop().open(new File(arquivoPdf));
         
     }catch (Exception e){
     
     }
     
}
}
