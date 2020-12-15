/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import model.dao.CategoriaDao;
import model.dao.UsuarioDao;
//import javax.swing.JFrame;

/**
 *
 * @author Naine
 */
public class Usuario {
    private String nome_usuario;
    private String login;
    private String senha;
    private String perfil;

    //private List<JFrame> telasPermitidas;

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
  /*
 }  */
     public static void gerarPdfUsuarios(){
     UsuarioDao udao = new UsuarioDao();
     Document doc = new Document();
     List <Usuario> listu = udao.read();
     String arquivoPdf = "relatoriousuario.pdf";
     try{
         PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf) );
         doc.open();
         Paragraph p = new Paragraph ("Relatorio Usuários");
         Paragraph p2 = new Paragraph ("                  ");
         

        // Image imagem = Image.getInstance("");
        // imagem.scaleToFit(400, 200);
            
         
         p.setAlignment(1);
         doc.add(p);
         doc.add(p2);
         p = new Paragraph("");
         doc.add(p);
         doc.add(p2);
         
         
         PdfPTable table = new PdfPTable(3);
         PdfPCell cel1 = new PdfPCell(new Paragraph ("Nome"));
         PdfPCell cel2 = new PdfPCell(new Paragraph ("Login"));
         PdfPCell cel3 = new PdfPCell(new Paragraph ("Perfil"));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);

         for (Usuario usuarios : listu){
         cel1 = new PdfPCell(new Paragraph (usuarios.getNome_usuario()+""));
         cel2 = new PdfPCell(new Paragraph (usuarios.getLogin()+""));
         cel3 = new PdfPCell(new Paragraph (usuarios.getPerfil()+""));
         
         table.addCell(cel1);
         table.addCell(cel2);
         table.addCell(cel3);
         }
         doc.add(table);
         //doc.add(imagem);
         doc.close();
         Desktop.getDesktop().open(new File(arquivoPdf));
         
     }catch (Exception e){
     
     }
     
}

}
