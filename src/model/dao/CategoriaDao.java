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
import model.bean.Categorias;

/**
 *
 * @author naine
 */
public class CategoriaDao {

    public void create(Categorias c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO categoria (cod_categoria, nome_categoria)VALUES (?,?)");
            stmt.setString(1, c.getCodigo_categoria());
            stmt.setString(2, c.getNome_categoria());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Categorias> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Categorias> categorias = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM categoria");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Categorias categoria= new Categorias();

                categoria.setCodigo_categoria(rs.getString("cod_categoria"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                categorias.add(categoria);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return categorias;
    }
     public void update(Categorias c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE categoria SET nome_categoria = ? WHERE cod_categoria = ?");
            stmt.setString(1, c.getNome_categoria());
            stmt.setString(2, c.getCodigo_categoria());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
     public void delete(Categorias c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM categoria WHERE cod_categoria = ?");
            stmt.setString(1, c.getCodigo_categoria());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
  
}
