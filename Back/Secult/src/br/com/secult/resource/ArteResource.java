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
import br.com.secult.dao.ArteDao;
import br.com.secult.model.Arte;
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
@Path("/arte")
public class ArteResource {

    @GET
    @Path("/listarArte")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarArte() throws SQLException, Exception {

        ArteDao arteDao = new ArteDao();
        List<Arte> artes = arteDao.listarArte();

        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(artes).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("artes", ArrayUsarios);

        return jsonObject.toString();

    }
}
