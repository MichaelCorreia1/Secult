package br.com.secult.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.dao.EstadoDao;
import br.com.secult.model.Estado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import javax.ws.rs.core.MediaType;

@Path("/estados")
public class EstadoResource {
    
    
    
    @GET
    @Path("/listarEstados")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEstados() throws SQLException, Exception {

        EstadoDao estadoDao = new EstadoDao();
        List<Estado> estados = estadoDao.listarEstados();

        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(estados).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("estados", ArrayUsarios);
        
        return jsonObject.toString();
        

    }
    
}
