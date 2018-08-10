    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource;

import br.com.secult.resource.UsuarioResource;
import br.com.secult.dao.MensagensDao;
import br.com.secult.model.Mensagem;
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
@Path("/mensagens")
public class MensagemResource {
    
    @GET
    @Path("/listarMensagens/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarMensagem(@PathParam("id_usuario") long id_usuario) throws SQLException, Exception {

        Mensagem mensagem = new Mensagem();
        mensagem.setId_usuario(id_usuario);
       
        
        MensagensDao mensagensDao = new MensagensDao();
        List<Mensagem> mensagens = mensagensDao.listarMensagens(mensagem);

        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(mensagens).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("mensagens", ArrayUsarios);
        
        return jsonObject.toString();
        
    }
    
    @GET
    @Path("/qtdMensagens/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String qtdMensagensNaoLidas(@PathParam("id_usuario") long id_usuario) throws SQLException, Exception {
        Mensagem mensagem = new Mensagem();
        mensagem.setId_usuario(id_usuario);
       
        MensagensDao mensagemDao = new MensagensDao();
         
        List<Mensagem> mensagens = mensagemDao.qtdMensagensNaoLidas(mensagem);

        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(mensagens).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("mensagens", ArrayUsarios);

        

        return jsonObject.toString();
        
   
    }
    
    
    
        @GET
    @Path("/updateLida/{id_usuario}&{id_mensagem}")
    @Produces(MediaType.APPLICATION_JSON)
    public String UpdateLida(@PathParam("id_usuario") long id_usuario, @PathParam("id_mensagem") int id_mensagem) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Mensagem mensagem = new Mensagem();
        mensagem.setId_usuario(id_usuario);
        mensagem.setId_mensagem(id_mensagem);

        MensagensDao mensagemDao = new MensagensDao();

        if (mensagemDao.updateSenha(mensagem)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
            
    
    
    
	
	   @GET
    @Path("/insertMensagem/{id_usuario}&{titulo}&{corpo}&{lida}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAluno(@PathParam("id_usuario") long id_usuario, @PathParam("titulo") String titulo, @PathParam("corpo") String corpo, @PathParam("lida") String lida) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        
        Mensagem msg = new Mensagem();
        msg.setId_usuario(id_usuario);
        msg.setTitulo(titulo);
        msg.setCorpo(corpo);
        msg.setLida(lida);

        
        MensagensDao msgDao = new MensagensDao();
        
        if (msgDao.insertMensagem(msg)) {
            
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }

   
}

  
