/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Categorias;
import model.bean.Produtos;

/**
 *
 * @author naine
 */
public class ProdutoDao{
       private Connection con = null;

    public ProdutoDao() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create (Produtos produto) {

        String sql = "INSERT INTO produto (cod_produto, descricao, cod_categoria) VALUES (?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo_produto());
            stmt.setString(2, produto.getDescricao_produto());
            stmt.setString(3, produto.getCategoria().getCodigo_categoria());
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

    public List<Produtos> read() {

        String sql = "select * from produto p inner join categoria c on c.cod_categoria=p.cod_categoria;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> produtos = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos produto = new Produtos();

                produto.setCodigo_produto(rs.getString("cod_produto"));
                produto.setDescricao_produto(rs.getString("descricao"));
              //  produto.setUnidade_produto(rs.getInt("unid_produto"));
                //produto.setCategoria(rs.getString("cod_categoria")).toString();


                Categorias categoria = new Categorias();
                categoria.setCodigo_categoria(rs.getString("cod_categoria"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));

                produto.setCategoria(categoria);

                produtos.add(produto);
            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }*/

        return produtos;
    }

    public boolean update(Produtos produto) {

        String sql = "UPDATE produto SET descricao = ?, cod_categoria = ? WHERE cod_produto = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao_produto());
            stmt.setString(2, produto.getCategoria().getCodigo_categoria());
            stmt.setString(3, produto.getCodigo_produto());
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
    
         public boolean delete(Produtos produto) {

        String sql = "DELETE FROM produto WHERE cod_produto = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getCodigo_produto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
            return false;
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt);
        }*/

    }
        
    }

   
    /*public void delete(Produtos p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE cod_produto= ?");
            stmt.setString(1, p.getCodigo_produto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }*/
    //Testeigual os outros:
    /*public void create(Produtos p) {
        Connecton con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto (cod_produto, descricao, unid_produto, fk_categoria )VALUES (?,?,?,?)");
            stmt.setString(1, p.getCodigo_produto());
            stmt.setString(2, p.getDescricao_produto());
            stmt.setInt(3, p.getUnidade_produto());
            stmt.setObject(4, p.getCategoria().toString());
            
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produtos> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos produto= new Produtos();

                produto.setCodigo_produto(rs.getString("cod_produto"));
                produto.setDescricao_produto(rs.getString("descricao"));
                produto.setUnidade_produto(rs.getInt("unid_produto"));
                produto.setCategoria((Categorias) rs.getObject("fk_categoria"));
                produtos.add(produto);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;
    }
     public void update(Produtos p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE produto SET descricao = ?, unid_produto =?, fk_categoria = ? WHERE cod_produto = ?");
            stmt.setString(1, p.getDescricao_produto());
            stmt.setInt(2, p.getUnidade_produto());
            stmt.setObject(3, p.getCategoria());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
     public void delete(Produtos p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE cod_produto= ?");
            stmt.setString(1, p.getCodigo_produto());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }*/

 

