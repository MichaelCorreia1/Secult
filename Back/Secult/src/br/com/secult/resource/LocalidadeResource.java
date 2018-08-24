/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.dao.LocalidadeDao;
import br.com.secult.model.Localidade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Muquifo
 */
@Path("/localidade")
public class LocalidadeResource {

    @GET
    @Path("/listarLocalidade")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarLocalidade() throws SQLException, Exception {

        LocalidadeDao localidadeDao = new LocalidadeDao();
        List<Localidade> localidade = localidadeDao.listarLocalidade();

        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(localidade).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("localidades", ArrayUsarios);

        return jsonObject.toString();

    }

}
