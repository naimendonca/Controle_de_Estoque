/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import model.bean.Categorias;
import model.dao.ProdutoDao;


/**
 *
 * @author Naine
 */
public class Produtos {
    private String codigo_produto;
    private String descricao_produto;
    private int unidade_produto;
    private Categorias categoria;

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public int getUnidade_produto() {
        return unidade_produto;
    }

    public void setUnidade_produto(int unidade_produto) {
        this.unidade_produto = unidade_produto;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
@Override
    public String toString(){
        return getDescricao_produto();
}
     public static void gerarPdfProduto(){
     ProdutoDao pdao = new ProdutoDao();
     Document doc = new Document();
     List <Produtos> listp = pdao.read();
     String arquivoPdf = "relatorioproduto.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Produtos");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(3);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código Produto"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Descrição Produto"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Categoria"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         
         for (Produtos produtos : listp){
         cel1 = new PdfPCell(new Paragraph (produtos.getCodigo_produto()+""));
         cel2 = new PdfPCell(new Paragraph (produtos.getDescricao_produto()+""));
         cel3 = new PdfPCell(new Paragraph (produtos.getCategoria()+""));
         
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
