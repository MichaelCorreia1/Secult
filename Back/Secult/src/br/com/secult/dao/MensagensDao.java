/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Muquifo
 */
public class MensagensDao {

    private Connection connection;

    public List<Mensagem> listarMensagens(Mensagem mensagens) throws SQLException, Exception {
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();

        String sql = "select * from mensagens where id_usuario=? order by lida";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, mensagens.getId_usuario());
            rs = stmt.executeQuery();
            
           return resultSetToObjectTransfer(rs);

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
            }

        }

    }

    public List<Mensagem> qtdMensagensNaoLidas(Mensagem mensagem) throws SQLException, Exception {
        ArrayList<Mensagem> mensagens = new ArrayList<Mensagem>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select count(*) AS qtd from mensagens where id_usuario=? and lida= 'n'";
        try { 
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, mensagem.getId_usuario());
            rs = pstmt.executeQuery();

            while (rs.next()) {
               
                mensagem.setQtd(rs.getLong("qtd"));
                mensagens.add(mensagem);
            }
            return mensagens;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
         
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }
        }
        return null;

    }
    
    
    public boolean updateSenha(Mensagem mensagem) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;

        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE mensagens SET lida = 's' where id_usuario=?  and id_mensagem=?;";
        try {
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setLong(1, mensagem.getId_usuario());
            pstmt.setLong(2, mensagem.getId_mensagem());
            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            hasError = false;
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }

        }
        return hasError;
    }
    
    
        public boolean insertMensagem(Mensagem mensagem) {
        PreparedStatement stmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "INSERT INTO mensagens (id_usuario, titulo, corpo, lida) VALUES (?,?,?,?)";
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, mensagem.getId_usuario());
            stmt.setString(2, mensagem.getTitulo());
            stmt.setString(3, mensagem.getCorpo());
            stmt.setString(4, mensagem.getLida());

            stmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            hasError = false;
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
            }

        }

        return hasError;
    }
    
    
    
    
    

    private List<Mensagem> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Mensagem> objs = new Vector();

        while (rs.next()) {
            Mensagem mensagem = new Mensagem();

            mensagem.setId_usuario(rs.getInt("id_usuario"));
            mensagem.setId_mensagem(rs.getInt("id_mensagem"));
            mensagem.setTitulo(rs.getString("titulo"));
            mensagem.setCorpo(rs.getString("corpo"));
            mensagem.setLida(rs.getString("lida"));

            objs.add(mensagem);
        }
        return objs;
    }

}
