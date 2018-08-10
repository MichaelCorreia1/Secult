package br.com.secult.resource;

import br.com.secult.dao.MunicipioDao;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.model.Municipio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import javax.ws.rs.PathParam;

@Path("/municipios")
public class MunicipioResource {

    
    @GET
    @Path("/listarMunicipios/{uf}")
    @Produces("application/json")
    public String listarMunicipios(@PathParam("uf") String uf) throws SQLException, Exception {
        Municipio municipio = new Municipio();
        municipio.setUf(uf);

        MunicipioDao municipioDao = new MunicipioDao();
        List<Municipio> municipios = municipioDao.listarMunicipios(municipio);

        Gson gson = new GsonBuilder().create();
        
       
        
        JsonArray ArrayUsarios = gson.toJsonTree(municipios).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("municipios", ArrayUsarios);
        
        return jsonObject.toString();
        
    }

   

}
