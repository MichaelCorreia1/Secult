/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Administrador;
import br.com.secult.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Lucas
 */
public class AdministradorDao {

    private Connection connection;

    public boolean insert(Administrador administrador) {
        PreparedStatement stmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "INSERT INTO administrador (id_usuario, id_adm, cargo) VALUES (?,?,?)";
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, administrador.getId_usuario());
            stmt.setLong(2, administrador.getId_adm());
            stmt.setString(3, administrador.getCargo());
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

    public List<Administrador> listarAdministradores() throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select * from administrador a join usuario u on a.id_usuario = u.id";

        try {
            stmt = connection.prepareStatement(sql);
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
    
    public List<Administrador> listarAdmById(Administrador adm) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select * from administrador a join usuario u on a.id_usuario = u.id WHERE id_usuario=?";

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, adm.getId_usuario());
            rs = pstmt.executeQuery();
            return resultSetToObjectTransfer(rs);

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pstmt.close();
            } catch (Exception e) {
            }

        }

    }
    

    private List<Administrador> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Administrador> objs = new Vector();
        while (rs.next()) {
            Administrador administrador = new Administrador();
            administrador.setId_usuario(rs.getInt("id_usuario"));
            administrador.setId_adm(rs.getLong("id_adm"));
            administrador.setCargo(rs.getString("cargo"));   
            
            administrador.setId(rs.getInt("id"));
            administrador.setNome(rs.getString("nome"));
            administrador.setEmail(rs.getString("email"));
            administrador.setSenha(rs.getString("senha"));
            administrador.setTipo(rs.getString("tipo"));
            administrador.setEndereco(rs.getString("endereco"));
            administrador.setTelefone(rs.getLong("telefone"));
            administrador.setCidade(rs.getString("cidade"));
            administrador.setEstado(rs.getString("estado"));
            administrador.setFoto(rs.getBytes("foto"));

            
            
            objs.add(administrador);
        }
        return objs;
    }
    
     public boolean updateAdm(Administrador adm) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;

        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE administrador SET cargo=? where id_usuario=?";
        try {
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, adm.getCargo());
            pstmt.setLong(2, adm.getId_usuario());
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
     
     public List<Administrador> listarAlunosById(Administrador adm) throws SQLException, Exception {
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();

        String sql = "select * from administrador a JOIN usuario u on(a.id_usuario=u.id) where id_usuario=?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, adm.getId_usuario());
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
     
     
}
