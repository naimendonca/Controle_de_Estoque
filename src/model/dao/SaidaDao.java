/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Armazenamento;
import model.bean.Clientes;
import model.bean.Produtos;
import model.bean.Saida;

/**
 *
 * @author naine
 */
public class SaidaDao {
     private Connection con = null;

    public SaidaDao() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean create (Saida saida) {

        String sql = "INSERT INTO saida (cod_saida, cod_armazenamento, cod_cliente, qtd_saida, datas) VALUES (?,?,?,?,?)";
        

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, saida.getCod_saida());
            stmt.setObject(2, saida.getArmazenamento().getCod_arm());
            stmt.setString(3, saida.getCliente().getCod_cliente());
            stmt.setInt(4, saida.getQtd());
            stmt.setString(5, saida.getData());
          
           stmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
            return false;
        }/* finally {
            ConnectionFactory.closeConnection(con, stmt);
        }*/

    }

   public List<Saida> read() {

        String sql = "select * from saida s inner join armazenamento a, cliente c where s.cod_armazenamento=a.cod_armazenamento and s.cod_cliente=c.cod_cliente;";
        

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Saida> saida = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Saida sai= new Saida();

                sai.setCod_saida(rs.getString("cod_saida"));
                sai.setQtd(rs.getInt("qtd_saida"));
                sai.setData(rs.getString("datas"));


                Armazenamento armazenamento = new Armazenamento();
                armazenamento.setCod_arm(rs.getString("cod_armazenamento"));
                armazenamento.setProduto((Produtos) rs.getObject("cod_produto"));
                armazenamento.setQtd(rs.getInt("qtd_produto"));
                armazenamento.setQtd(rs.getInt("qtd_produto"));
                

                sai.setArmazenamento(armazenamento);
                
                Clientes cliente = new Clientes();
                cliente.setCod_cliente(rs.getString("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_empresa"));

                sai.setCliente(cliente);

                saida.add(sai);
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }*/

       return saida;
   }
  public boolean  AtualizaArmazenamento(Saida saida) {

        String sql = "UPDATE armazenamento INNER JOIN saida ON armazenamento.cod_armazenamento =? = saida.cod_armazenamento =? SET armazenamento.qtd_produto =?  = armazenamento.qtd_produto=? - saida.qtd_saida=? where saida.cod_saida =?";

        PreparedStatement stmt = null;
        Armazenamento armazenamento = new Armazenamento();
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, armazenamento.getCod_arm());
            stmt.setString(2, saida.getArmazenamento().getCod_arm());
            stmt.setInt(3, armazenamento.getQtd());
            stmt.setInt(4, armazenamento.getQtd());
            stmt.setInt(5, saida.getQtd());
            stmt.setString(6, saida.getCod_saida());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
            return false;
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt);
        }   */
    }

   
   /* public void AtualizaArmazenamento() throws SQLException{
    String sql = "select * from saida s inner join armazenamento a, cliente c where s.cod_armazenamento=a.cod_armazenamento and s.cod_cliente=c.cod_cliente;";
            PreparedStatement stmt = null;
            ResultSet rs = null;

            List<Saida> saida = new ArrayList<>();
          

                Saida sai= new Saida();
                Armazenamento armazenamento = new Armazenamento();
                sai.setQtd(rs.getInt("qtd_saida"));
                armazenamento.setQtd(rs.getInt("qtd_produto"));
                
                Integer atual = armazenamento.getQtd() - sai.getQtd();
                armazenamento.setQtd(atual);
            
            
    }*/
   
   
}
