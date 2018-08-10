/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.dao;

import br.com.secult.model.Localidade;
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
public class LocalidadeDao {

    private Connection con;

    public List<Localidade> listarLocalidade() throws SQLException, Exception {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        this.con = new ConnectionFactory().getConnection();
        String sql = "select  * from localidade";

        try {
            stmt = con.prepareStatement(sql);
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
    
     private List<Localidade> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Localidade> objs = new Vector<>();
        while (rs.next()) {
            Localidade localidade = new Localidade();
            localidade.setId(rs.getInt("id"));
            localidade.setNome(rs.getString("nome"));

            objs.add(localidade);
        }
        return objs;
    }
}
