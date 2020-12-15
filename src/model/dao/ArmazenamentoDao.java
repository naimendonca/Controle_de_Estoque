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
import model.bean.Fornecedores;
import model.bean.Produtos;
import model.bean.Saida;

/**
 *
 * @author naine
 */
public class ArmazenamentoDao {
     private Connection con = null;

    public ArmazenamentoDao() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create (Armazenamento armazenamento) {

        String sql = "INSERT INTO armazenamento (cod_armazenamento, qtd_produto, cod_produto, cod_fornecedor, datas) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, armazenamento.getCod_arm());
            stmt.setInt(2, armazenamento.getQtd());
            stmt.setString(3, armazenamento.getProduto().getCodigo_produto());
            stmt.setString(4, armazenamento.getFornecedor().getRazao_social());
            stmt.setString(5, armazenamento.getData());
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

   public List<Armazenamento> read() {

        String sql = "select * from armazenamento a inner join produto p, fornecedor f where p.cod_produto=a.cod_produto and f.cod_fornecedor=a.cod_fornecedor;";
        

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Armazenamento> armazenamento = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Armazenamento arm = new Armazenamento();

                arm.setCod_arm(rs.getString("cod_armazenamento"));
                arm.setQtd(rs.getInt("qtd_produto"));
                arm.setData(rs.getString("datas"));


                Produtos produto = new Produtos();
                produto.setCodigo_produto(rs.getString("cod_produto"));
                produto.setDescricao_produto(rs.getString("descricao"));
                arm.setProduto(produto);
                
                Fornecedores fornecedor = new Fornecedores();
                fornecedor.setRazao_social(rs.getString("cod_fornecedor"));
                fornecedor.setInsc_estadual(rs.getString("cnpj"));
                arm.setFornecedor(fornecedor);
                
                armazenamento.add(arm);

            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }*/

       return armazenamento;
   }   public boolean update(Armazenamento armazenamento) {
       
        String sql = "UPDATE armazenamento SET qtd_produto = ?, datas= ? WHERE cod_armazenamento = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, armazenamento.getQtd());
            //stmt.setObject(2, armazenamento.getProduto());
           // stmt.setObject(3, armazenamento.getFornecedor());
            stmt.setString(2, armazenamento.getData());
            stmt.setString(3, armazenamento.getCod_arm());
            
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
   
      /* public boolean  AtualizaArmazenamento(Armazenamento armazenamento) {

        String sql = "UPDATE armazenamento INNER JOIN saida ON armazenamento.cod_armazenamento =? = saida.cod_armazenamento =? SET armazenamento.qtd_produto =?  = armazenamento.qtd_produto=? - saida.qtd_saida=? where saida.cod_saida =?";

        PreparedStatement stmt = null;
        Saida saida = new Saida();
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
    //}
   
  /*  public boolean  AtualizaArmazenamento(Armazenamento armazenamento) {

        String sql = "UPDATE armazenamento INNER JOIN saida ON armazenamento.cod_armazenamento =? = saida.cod_armazenamento =? SET armazenamento.qtd_produto =?  = armazenamento.qtd_produto=? - saida.qtd_saida=? where saida.cod_saida =?";

        PreparedStatement stmt = null;
        Saida saida = new Saida();
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
    //}

        }
