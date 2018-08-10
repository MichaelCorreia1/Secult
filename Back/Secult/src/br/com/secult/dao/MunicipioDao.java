/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Estado;
import br.com.secult.model.Municipio;
import br.com.secult.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Lucas
 */
public class MunicipioDao {

    private Connection connection;

    public List<Municipio> listarMunicipios(Municipio municipio) throws SQLException, Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();
        String sql = "SELECT m.* FROM estado JOIN municipio m ON m.uf = estado.uf where m.uf=?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, municipio.getUf());
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

    private List<Municipio> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Municipio> objs = new Vector();
        while (rs.next()) {
            Municipio municipio = new Municipio();
            municipio.setId(rs.getLong("id"));
            municipio.setCodigo(rs.getLong("codigo"));
            municipio.setNome(rs.getString("nome"));
            municipio.setUf(rs.getString("uf"));
            
            objs.add(municipio);
        }
        return objs;
    }

}
