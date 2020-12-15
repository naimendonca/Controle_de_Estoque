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
import model.bean.Fornecedores;
import model.bean.Pedidos;
import model.bean.Produtos;


/**
 *
 * @author naine
 */
public class PedidoDao {
    private Connection con = null;

    public PedidoDao() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create (Pedidos pedidos) {

        String sql = "INSERT INTO pedido (num_pedido, cod_cliente, cod_produto, quantidade_pedido, datas) VALUES (?,?,?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pedidos.getCod_pedido());
            stmt.setString(2, pedidos.getCliente().getCod_cliente());
            stmt.setString(3, pedidos.getProduto().getCodigo_produto());
            stmt.setInt(4, pedidos.getQtd());
            stmt.setString(5, pedidos.getData());
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

   public List<Pedidos> read() {

        String sql = "select * from pedido pe inner join produto p, cliente c where p.cod_produto=pe.cod_produto and c.cod_cliente=pe.cod_cliente;";
        

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedidos> pedidos = new ArrayList<>();

        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pedidos pedido = new Pedidos();

                pedido.setCod_pedido(rs.getString("num_pedido"));
                pedido.setQtd(rs.getInt("quantidade_pedido"));
                pedido.setData(rs.getString("datas"));


                Produtos produto = new Produtos();
                produto.setCodigo_produto(rs.getString("cod_produto"));
                produto.setDescricao_produto(rs.getString("descricao"));
                pedido.setProduto(produto);
                
                Clientes cliente = new Clientes();
                cliente.setCod_cliente(rs.getString("cod_cliente"));
                cliente.setNome_cliente(rs.getString("nome_empresa"));
                pedido.setCliente(cliente);
                
                pedidos.add(pedido);

            }

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        } /*finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }*/

       return pedidos;
   }  
    public boolean update(Pedidos pedido) {

        String sql = "UPDATE pedido SET cod_produto = ?, cod_cliente = ?, quantidade_pedido=?, datas=? WHERE num_pedido = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pedido.getProduto().getCodigo_produto());
            stmt.setString(2, pedido.getCliente().getCod_cliente());
            stmt.setInt(3, pedido.getQtd());
            stmt.setString(4, pedido.getData());
            stmt.setString(5, pedido.getCod_pedido());
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
    
         public boolean delete(Pedidos pedido) {

        String sql = "DELETE FROM pedido WHERE num_pedido = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, pedido.getCod_pedido());
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

