/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Cadart;
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
 * @author David
 */
public class CadartDao {

    private Connection connection;

    public boolean insert(Cadart cadart) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement stmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;

        try {
            String sql = "INSERT INTO cadart (cpf, nome, nome_artistico, telefone, email, sexo, descricao, projeto_atual, data_nascimento, senha, id_arte, visibilidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, cadart.getCpf());
            stmt.setString(2, cadart.getNome());
            stmt.setString(3, cadart.getNomeArtistico());
            stmt.setString(4, cadart.getTelefone());
            stmt.setString(5, cadart.getEmail());
            stmt.setString(6, cadart.getSexo());
            stmt.setString(7, cadart.getDescricao());
            stmt.setString(8, cadart.getProjetoAtual());
            stmt.setDate(9, cadart.getDataNascimento());
            stmt.setString(10, cadart.getSenha());
            stmt.setInt(11, cadart.getIdArte());
            stmt.setString(12, cadart.getVisibilidade());

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

    private List<Cadart> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Cadart> objs = new Vector<>();
        while (rs.next()) {
            Cadart cadart = new Cadart();
            cadart.setCpf(rs.getLong("cpf"));
            cadart.setNome(rs.getString("nome"));
            cadart.setNomeArtistico(rs.getString("nome_artistico"));
            cadart.setSexo(rs.getString("sexo"));
            cadart.setFotoPerfil(rs.getBytes("foto_perfil"));
            cadart.setDescricao(rs.getString("descricao"));
            cadart.setDataNascimento(rs.getDate("data_nascimento"));
            cadart.setIdArte(rs.getInt("id_arte"));
            cadart.setProjetoAtual(rs.getString("projeto_atual"));
            cadart.setEmail(rs.getString("email"));
            cadart.setTelefone(rs.getString("telefone"));
            cadart.setSenha(rs.getString("senha"));
            cadart.setVisibilidade(rs.getString("visibilidade"));

            objs.add(cadart);
        }
        return objs;
    }

    public List<Cadart> listarUsuario() throws Exception, Exception {
        this.connection = new ConnectionFactory().getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM cadart";
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
    
     public List<Cadart> listarUsuarioByVisi() throws Exception, Exception {
        this.connection = new ConnectionFactory().getConnection();
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT cpf, C.nome as \"nomeUsu\", nome_artistico, sexo, foto_perfil, descricao, data_nascimento, senha, projeto_atual, telefone, email, A.nome as \"nomeArte\", visibilidade, id_arte From cadart as C join arte as A ON(C.id_arte = A.id) Where visibilidade = 's'";
            stmt = connection.prepareStatement(sql);

            rs = stmt.executeQuery();
            
            return resultSetToObjectTransferByVisi(rs);
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
     private List<Cadart> resultSetToObjectTransferByVisi(ResultSet rs) throws Exception {
        List<Cadart> objs = new Vector<>();
        while (rs.next()) {
            Cadart cadart = new Cadart();
            cadart.setCpf(rs.getLong("cpf"));
            cadart.setNome(rs.getString("nomeUsu"));
            cadart.setNomeArtistico(rs.getString("nome_artistico"));
            cadart.setSexo(rs.getString("sexo"));
            cadart.setFotoPerfil(rs.getBytes("foto_perfil"));
            cadart.setDescricao(rs.getString("descricao"));
            cadart.setDataNascimento(rs.getDate("data_nascimento"));
            cadart.setProjetoAtual(rs.getString("projeto_atual"));
            cadart.setEmail(rs.getString("email"));
            cadart.setTelefone(rs.getString("telefone"));
            cadart.setSenha(rs.getString("senha"));
            cadart.setVisibilidade(rs.getString("visibilidade"));
            cadart.setNomeArte(rs.getString("nomeArte"));
            cadart.setIdArte(rs.getInt("id_arte"));


            objs.add(cadart);
        }
        return objs;
    }
   
    public boolean updateUsuario(Cadart cadart){
        this.connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        boolean hasError = true;
        
        String sql = "UPDATE cadart SET nome_artistico=?, descricao=?, email=?, id_arte=?, telefone=?, senha=?, projeto_atual=?, visibilidade=? WHERE cpf=?";
        try {
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, cadart.getNomeArtistico());
            stmt.setString(2, cadart.getDescricao());
            stmt.setString(3, cadart.getEmail());
            stmt.setInt(4, cadart.getIdArte());
            stmt.setString(5, cadart.getTelefone());
            stmt.setString(6, cadart.getSenha());
            stmt.setString(7, cadart.getProjetoAtual());
            stmt.setLong(8, cadart.getCpf());
            stmt.setString(9, cadart.getVisibilidade());
            
            stmt.executeUpdate();
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
    
    public boolean delete(Cadart cadart){
        this.connection = new ConnectionFactory().getConnection();
        PreparedStatement stmt = null;
        boolean hasError = true;
        
        String sql = "DELETE FROM cadart WHERE cpf=?";
        try {
            stmt = connection.prepareStatement(sql);
            
             stmt.setLong(1, cadart.getCpf());
            
            stmt.executeUpdate();
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

    
    public void salvarFoto(Cadart usuario) throws Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "UPDATE cadart SET foto_perfil=? WHERE cpf = ?";
        try {

            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, usuario.getFotoPerfil());
            pstmt.setLong(2, usuario.getCpf());

            pstmt.execute();
        } catch (Exception e) {

            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }

        }

    }
    public List<Cadart> getById(Cadart usuario) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select * from cadart where  cpf = ?";
        ResultSet rs = null;

        try {

            pstmt = connection.prepareStatement(sql);

            pstmt.setObject(1, usuario.getCpf());
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
}

