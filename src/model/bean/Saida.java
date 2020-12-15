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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.dao.ArmazenamentoDao;
import model.dao.SaidaDao;

/**
 *
 * @author naine
 */
public class Saida {
    private String cod_saida;
    private Armazenamento armazenamento;
    private Clientes cliente;
    private Integer qtd;
    private String data;

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public String getCod_saida() {
        return cod_saida;
    }

    public void setCod_saida(String cod_saida) {
        this.cod_saida = cod_saida;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
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
    
 
    /*public void ConferirEstoque() throws Exception{
        if(getQtd() <= armazenamento.getQtd()){
           this.qtd = qtd;
       }else {
           //JOptionPane.showMessageDialog(null, "Sem quantidade em estoque");
           throw new Exception("Sem quantidade em estoque");
           
       }
}  */
    public void ConferirEstoque() throws Exception{
        if(getQtd() >armazenamento.getQtd()){
            JOptionPane.showMessageDialog(null, "Sem quantidade em estoque");
            throw new Exception( "Sem quantidade em estoque");
        }
    
    }
    
     public static void gerarPdfsSaida(){
     SaidaDao adao = new SaidaDao();
     Document doc = new Document();
     List <Saida> lista = adao.read();
     String arquivoPdf = "relatorioSaida.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Saídas de Estoque");
         Paragraph p2 = new Paragraph ("                  ");
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         PdfPTable table = new PdfPTable(5);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Código"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Estoque retirado"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Cliente"));
         PdfPCell cel4 = new PdfPCell(new Paragraph ("Quantidade"));
         PdfPCell cel5 = new PdfPCell(new Paragraph ("Data"));

         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         table.addCell(cel4);
         table.addCell(cel5);
         
         for (Saida saida : lista){
         cel1 = new PdfPCell(new Paragraph (saida.getCod_saida()+""));
         cel2 = new PdfPCell(new Paragraph (saida.getArmazenamento()+""));
         cel3 = new PdfPCell(new Paragraph (saida.getCliente()+""));
         cel4 = new PdfPCell(new Paragraph (saida.getQtd()+""));
         cel5 = new PdfPCell(new Paragraph (saida.getData()+""));

         
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
