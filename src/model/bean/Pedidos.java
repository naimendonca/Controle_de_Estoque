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
import model.dao.ArmazenamentoDao;
import model.dao.PedidoDao;

/**
 *
 * @author naine
 */
public class Pedidos {
    private String cod_pedido;
    private Produtos produto;
    private Clientes cliente;
    private Integer qtd;
    private String data;

    public String getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(String cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
     public static void gerarPdfPedido(){
     PedidoDao pdao = new PedidoDao();
     Document doc = new Document();
     List <Pedidos> lista = pdao.read();
     String arquivoPdf = "relatorioPedidos.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Pedidos");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(5);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código Pedido"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Produto"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Cliente"));
         PdfPCell cel4 = new PdfPCell(new Paragraph ("Quantidade"));
         PdfPCell cel5 = new PdfPCell(new Paragraph ("Data"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         
         for (Pedidos pedido : lista){
         cel1 = new PdfPCell(new Paragraph (pedido.getCod_pedido()+""));
         cel2 = new PdfPCell(new Paragraph (pedido.getProduto()+""));
         cel3 = new PdfPCell(new Paragraph (pedido.getCliente()+""));
         cel4 = new PdfPCell(new Paragraph (pedido.getQtd()+""));
         cel5 = new PdfPCell(new Paragraph (pedido.getData()+""));
         
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
