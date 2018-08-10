/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Estado;
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
public class EstadoDao {

    private Connection connection;

    public List<Estado> listarEstados() throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "SELECT * FROM estado ";

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

    private List<Estado> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Estado> objs = new Vector();
        while (rs.next()) {
            Estado estado = new Estado();
            estado.setId(rs.getLong("id"));
            estado.setCodigoUf(rs.getLong("codigoUf"));
            estado.setNome(rs.getString("nome"));
            estado.setUf(rs.getString("uf"));
            estado.setRegiao(rs.getLong("regiao"));
            
            objs.add(estado);
        }
        return objs;
    }

}
