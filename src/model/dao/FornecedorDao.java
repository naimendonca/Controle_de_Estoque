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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Fornecedores;


/**
 *
 * @author naine
 */
public class FornecedorDao {
    public void create(Fornecedores f) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO fornecedor (cod_fornecedor, cnpj, nome, insc_estadual, logradouro, telefone)VALUES (?,?,?,?,?,?)");
            stmt.setString(1, f.getRazao_social());
            stmt.setString(2, f.getCnpj());
            stmt.setString(3, f.getNome_fornecedor());
            stmt.setString(4, f.getInsc_estadual());
            stmt.setString(5, f.getCep());
            stmt.setString(6, f.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Fornecedores> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Fornecedores> fornecedores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedor");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedores fornecedor= new Fornecedores();

                fornecedor.setRazao_social(rs.getString("cod_fornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setNome_fornecedor(rs.getString("nome"));
                fornecedor.setInsc_estadual(rs.getString("insc_estadual"));
                fornecedor.setCep(rs.getString("logradouro"));
                fornecedor.setTelefone(rs.getString("telefone"));
                
                fornecedores.add(fornecedor);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return fornecedores;
    }
     public void update(Fornecedores f) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE fornecedor SET cnpj=?, nome=?, insc_estadual=?, logradouro=?, telefone=? WHERE cod_fornecedor = ?");
            stmt.setString(1, f.getCnpj());
            stmt.setString(2, f.getNome_fornecedor());
            stmt.setString(3, f.getInsc_estadual());
            stmt.setString(4, f.getCep());
            stmt.setString(5, f.getTelefone());
            stmt.setString(6, f.getRazao_social());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
     public void delete(Fornecedores f) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM fornecedor WHERE cod_fornecedor = ?");
            stmt.setString(1, f.getRazao_social());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
  
}
