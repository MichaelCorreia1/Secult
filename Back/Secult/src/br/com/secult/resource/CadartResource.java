/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource;

import br.com.secult.dao.CadartDao;
import br.com.secult.model.Cadart;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David
 */
@Path ("/cadart")
public class CadartResource {
    @GET
    @Path("/insertUsuario/{cpf}&{nome}&{nomeArtistico}&{email}&{telefone}&{sexo}&{descricao}&{projetoAtual}&{dataNascimento}&{senha}&{idArte}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUsuario(@PathParam("cpf") long cpf, @PathParam("nome") String nome, @PathParam("email") String email, @PathParam("telefone") long telefone, @PathParam("nomeArtistico") String nomeArtistico, @PathParam("sexo") String sexo, @PathParam("descricao") String descricao,@PathParam("projetoAtual") String projetoAtual,@PathParam("dataNascimento") Date dataNascimento, @PathParam("senha") String senha,  @PathParam("idArte") int idArte) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);
        cadart.setNome(nome);
        cadart.setEmail(email);
        cadart.setTelefone(telefone);
        cadart.setNomeArtistico(nomeArtistico);
        cadart.setSexo(sexo);
        cadart.setDescricao(descricao);
        cadart.setProjetoAtual(projetoAtual);
        cadart.setDataNascimento(dataNascimento);
        cadart.setSenha(senha);
        cadart.setIdArte(idArte);
        

        CadartDao cadartDao = new CadartDao();

        if (cadartDao.insert(cadart)) {

            return "{\"status\":\"ok\", \"id_usuario\":\"" + cadart.getCpf() + "\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
     @GET
    @Path("/listarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarUsuarios() throws SQLException, Exception {

        CadartDao usuarioDao = new CadartDao();
        List<Cadart> usuarios = usuarioDao.listarUsuario();

        Gson gson = new GsonBuilder().create();

        JsonArray ArrayUsarios = gson.toJsonTree(usuarios).getAsJsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("usuario", ArrayUsarios);

        if (usuarios.size() > 0) {

            return jsonObject.toString();
        } else {

           return jsonObject.toString();
        }

    }
    
}
