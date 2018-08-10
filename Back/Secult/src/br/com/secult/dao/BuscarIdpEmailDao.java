/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.BuscarIdpEmail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Muquifo
 */
public class BuscarIdpEmailDao {

    private Connection connection;

    public List<BuscarIdpEmail> BuscarID(BuscarIdpEmail buscarID) throws SQLException, Exception {
       
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.connection = new ConnectionFactory().getConnection();

        String sql = "SELECT * FROM usuario WHERE email=?";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, buscarID.getEmail());
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

    

    private List<BuscarIdpEmail> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<BuscarIdpEmail> objs = new Vector();

        while (rs.next()) {
            BuscarIdpEmail buscar = new BuscarIdpEmail();

            buscar.setId_usuario(rs.getInt("id"));
            buscar.setEmail(rs.getString("email"));

            objs.add(buscar);
        }
        return objs;
    }

}
