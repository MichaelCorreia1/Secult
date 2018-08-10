/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Aluno;
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
public class AlunoDao {

    private Connection connection;

    public boolean insert(Aluno aluno) {
        PreparedStatement stmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "INSERT INTO aluno (id_usuario, id_aluno, curso) VALUES (?,?,?)";
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, aluno.getId_usuario());
            stmt.setLong(2, aluno.getId_aluno());
            stmt.setString(3, aluno.getCurso());
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

    public List<Aluno> listarAlunos() throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select  * from aluno a join usuario u on a.id_usuario = u.id";

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
    
    public List<Aluno> listarAlunosById(Aluno aluno) throws SQLException, Exception {
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();

        String sql = "select * from aluno a JOIN usuario u on(a.id_usuario=u.id) where id_usuario=?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, aluno.getId_usuario());
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

    
    
    private List<Aluno> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Aluno> objs = new Vector();
        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setId_aluno(rs.getLong("id_aluno"));
            aluno.setId_usuario(rs.getInt("id_usuario"));
            aluno.setCurso(rs.getString("curso"));

            aluno.setId(rs.getInt("id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setEmail(rs.getString("email"));
            aluno.setSenha(rs.getString("senha"));
            aluno.setTipo(rs.getString("tipo"));
            aluno.setEndereco(rs.getString("endereco"));
            aluno.setTelefone(rs.getLong("telefone"));
            aluno.setCidade(rs.getString("cidade"));
            aluno.setEstado(rs.getString("estado"));
            aluno.setFoto(rs.getBytes("foto"));
        

            objs.add(aluno);
        }
        return objs;
    }
    
    public boolean updateAluno(Aluno aluno) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;

        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE aluno SET curso=? where id_usuario=?";
        try {
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, aluno.getCurso());
            pstmt.setLong(2, aluno.getId_usuario());
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

}
