package br.com.secult.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.dao.AdministradorDao;
import br.com.secult.model.Administrador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/administrador")
public class AdministradorResource {
    
    @GET
    @Path("/insertAdministrador/{id_usuario}&{id_adm}&{cargo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAdministrador(@PathParam("id_usuario") long id_usuario, @PathParam("id_adm") long id_adm, @PathParam("cargo") String cargo) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        
        Administrador administrador = new Administrador();
        administrador.setId_adm(id_adm);
        administrador.setId_usuario(id_usuario);
        administrador.setCargo(cargo);
        
        AdministradorDao administradorDao = new AdministradorDao();
        
        if (administradorDao.insert(administrador)) {
            
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
    
    @GET
    @Path("/listarAdministradores")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarAdministradores() throws SQLException, Exception {

        AdministradorDao administradorDao = new AdministradorDao();
        List<Administrador> administradores = administradorDao.listarAdministradores();

            tratarImagem(administradores);
        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(administradores).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("administradores", ArrayUsarios);

        

           return jsonObject.toString();
        

    }
    
         @GET
    @Path("/updateAdm/{cargo}&{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUsuario(@PathParam("cargo") String cargo, @PathParam("id_usuario") long id_usuario ) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, Exception {
        Administrador adm = new Administrador();
        adm.setCargo(cargo);
        adm.setId_usuario(id_usuario);
        
        AdministradorDao admDao = new AdministradorDao();

        if (admDao.updateAdm(adm)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
    
    
     @GET
    @Path("/listarAdmById/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarAlunosById(@PathParam("id_usuario") long id_usuario) throws SQLException, Exception {

        Administrador adm = new Administrador();
        adm.setId_usuario(id_usuario);
       
        
        AdministradorDao admDao = new AdministradorDao();
        List<Administrador> adms = admDao.listarAdmById(adm);
         tratarImagem(adms);
        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(adms).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("administradores", ArrayUsarios);
        
        return jsonObject.toString();
        
    }
    
    public void tratarImagem(List<Administrador> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getFoto() != null) {

                String foto = usuarios.get(i).getFoto().length + "";

                usuarios.get(i).setFoto(foto.getBytes());

            }
        }

    }
    
}


