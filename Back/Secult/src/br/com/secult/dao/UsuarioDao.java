package br.com.secult.dao;

import br.com.secult.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class UsuarioDao {

    private Connection connection;

    public boolean insert(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "INSERT INTO usuario (nome, email, senha, tipo, endereco, telefone, cidade, estado) VALUES (?,?,?,?,?,?,?,?)";
        try {

            String senha = convertToHash(usuario);

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senha);
            pstmt.setString(4, usuario.getTipo());
            pstmt.setString(5, usuario.getEndereco());
            pstmt.setLong(6, usuario.getTelefone());
            pstmt.setString(7, usuario.getCidade());
            pstmt.setString(8, usuario.getEstado());
            pstmt.execute();
            
            
            usuario.setId(maxGame());

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

    public long maxGame() {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select max(id) from usuario";
            PreparedStatement pstmt = null;
            this.connection = new ConnectionFactory().getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public boolean delete(Usuario usuario) throws SQLException {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, usuario.getId());
            pstmt.executeUpdate();

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

    public boolean update(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;

        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE usuario SET nome=?, email=?, senha = ?, tipo=?, endereco =?, telefone = ?, cidade =?, estado =? where id=?";
        try {
            pstmt = connection.prepareStatement(sql);
            
            String senha = convertToHash(usuario);

             pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, senha);
            pstmt.setString(4, usuario.getTipo());
            pstmt.setString(5, usuario.getEndereco());
            pstmt.setLong(6, usuario.getTelefone());
            pstmt.setString(7, usuario.getCidade());
            pstmt.setString(8, usuario.getEstado());
            pstmt.setLong(9, usuario.getId());
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

    public boolean updateSenha(Usuario usuario) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        PreparedStatement pstmt = null;

        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE usuario SET senha = ? where id=?";
        try {
            pstmt = connection.prepareStatement(sql);

            String senha = convertToHash(usuario);
            pstmt.setString(1, senha);
            pstmt.setLong(2, usuario.getId());
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

    public void salvarFoto(Usuario usuario) throws Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "UPDATE usuario SET foto=? WHERE id = ?";
        try {

            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, usuario.getFoto());
            pstmt.setLong(2, usuario.getId());

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

    public List<Usuario> autenticar(Usuario usuario) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select * from usuario where  email=? and senha=?";
        ResultSet rs = null;

        try {

            String senha = convertToHash(usuario);

            pstmt = connection.prepareStatement(sql);

            pstmt.setObject(1, usuario.getEmail());
            pstmt.setObject(2, senha);
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

    public List<Usuario> getById(Usuario usuario) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select * from usuario where  id = ?";
        ResultSet rs = null;

        try {

            pstmt = connection.prepareStatement(sql);

            pstmt.setObject(1, usuario.getId());
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

    public List<Usuario> listar() throws SQLException, Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select  * from usuario";

        try {
            pstmt = connection.prepareStatement(sql);
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
    
     public List<Usuario> listarById(Usuario usuario) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "select  * from usuario where id=?";

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, usuario.getId());
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

    private String convertToHash(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(usuario.getSenha().getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senha = hexString.toString();
        return senha;
    }

    private List<Usuario> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Usuario> objs = new Vector();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipo(rs.getString("tipo"));
            usuario.setEndereco(rs.getString("endereco"));
            usuario.setTelefone(rs.getLong("telefone"));
            usuario.setCidade(rs.getString("cidade"));
            usuario.setEstado(rs.getString("estado"));
            usuario.setFoto(rs.getBytes("foto"));

            objs.add(usuario);
        }
        return objs;
    }
    
        public boolean updateUsuario(Usuario usuario) throws Exception {
        PreparedStatement pstmt = null;
        this.connection = new ConnectionFactory().getConnection();
        boolean hasError = true;
        String sql = "UPDATE public.usuario SET nome=?, email=?, endereco=?, telefone=?, cidade=?, estado=? WHERE id=?;";
        try {

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getEndereco());
            pstmt.setLong(4, usuario.getTelefone());
            pstmt.setString(5, usuario.getCidade());
            pstmt.setString(6, usuario.getEstado());
            pstmt.setLong(7, usuario.getId());

            pstmt.execute();
        } catch (Exception e) {

            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (Exception e) {
            }

        }
        return hasError;
    }   
}
