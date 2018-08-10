    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource;

import br.com.secult.dao.BuscarIdpEmailDao;
import br.com.secult.dao.MensagensDao;
import br.com.secult.model.BuscarIdpEmail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Muquifo
 */
@Path("/buscar")
public class BuscarIdpEmailResource {
    
    @GET
    @Path("porEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public String BuscarID(@PathParam("email") String email) throws SQLException, Exception {

         BuscarIdpEmail buscarId = new BuscarIdpEmail();
         buscarId.setEmail(email);
        
        BuscarIdpEmailDao buscarIdDao = new BuscarIdpEmailDao();
        List<BuscarIdpEmail> buscar = buscarIdDao.BuscarID(buscarId);

        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(buscar).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("buscar", ArrayUsarios);
        
        return jsonObject.toString();
        
    }
    
     
   
   
}
