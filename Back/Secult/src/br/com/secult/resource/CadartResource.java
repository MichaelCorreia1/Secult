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
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author David
 */
@Path("/cadart")
public class CadartResource {
//a
    @GET
    @Path("/insertUsuario/{cpf}&{nome}&{nomeArtistico}&{telefone}&{email}&{sexo}&{descricao}&{projetoAtual}&{idade}&{senha}&{idArte}&{visibilidade}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUsuario(@PathParam("cpf") long cpf, @PathParam("nome") String nome, @PathParam("telefone") String telefone ,@PathParam("email") String email, @PathParam("nomeArtistico") String nomeArtistico, @PathParam("sexo") String sexo, @PathParam("descricao") String descricao, @PathParam("projetoAtual") String projetoAtual, @PathParam("idade") int idade, @PathParam("senha") String senha, @PathParam("idArte") int idArte, @PathParam("visibilidade") String visibilidade) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);
        cadart.setNome(nome);
        cadart.setTelefone(telefone);
        cadart.setEmail(email);
        cadart.setNomeArtistico(nomeArtistico);
        cadart.setSexo(sexo);
        cadart.setDescricao(descricao);
        cadart.setProjetoAtual(projetoAtual);
        cadart.setIdade(idade);
        cadart.setSenha(senha);
        cadart.setIdArte(idArte);
        cadart.setVisibilidade(visibilidade);
        
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

                tratarImagem(usuarios);

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
    @GET
    @Path("/listarUsuarioByVisibilidade")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarUsuarioByVisibilidade() throws SQLException, Exception {

        CadartDao usuarioDao = new CadartDao();
        List<Cadart> usuarios = usuarioDao.listarUsuarioByVisibilidade();

        tratarImagem(usuarios);
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
    
     @GET
    @Path("/getByVisibilidadeDiferenteS")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByVisibilidadeDiferenteS() throws SQLException, Exception {

        CadartDao cadartDao = new CadartDao();
        List<Cadart> cadarts = cadartDao.getByVisibilidadeDiferenteS();

        tratarImagem(cadarts);
        Gson gson = new GsonBuilder().create();

        JsonArray ArrayUsarios = gson.toJsonTree(cadarts).getAsJsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("usuario", ArrayUsarios);

        if (cadarts.size() > 0) {

            return jsonObject.toString();
        } else {

            return jsonObject.toString();
        }

    }
    
    public void tratarImagem(List<Cadart> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getFotoPerfil() != null) {

                String foto = usuarios.get(i).getFotoPerfil().toString();

                usuarios.get(i).setFotoPerfil(foto.getBytes());

            }
        }
    }

    @GET
    @Path("/updateUsuario/{cpf}&{nome}&{idade}&{nomeArtistico}&{email}&{telefone}&{sexo}&{descricao}&{projetoAtual}&{idArte}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatetUsuario(@PathParam("cpf") long cpf, @PathParam("nome") String nome, @PathParam("idade") int idade, @PathParam("nomeArtistico") String nomeArtistico, @PathParam("email") String email, @PathParam("telefone") String telefone, @PathParam("sexo") String sexo, @PathParam("descricao") String descricao, @PathParam("projetoAtual") String projetoAtual, @PathParam("idArte") int idArte) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);
        cadart.setNome(nome);
        cadart.setIdade(idade);
        cadart.setNomeArtistico(nomeArtistico);
        cadart.setEmail(email);
        cadart.setTelefone(telefone);
        cadart.setSexo(sexo);
        cadart.setDescricao(descricao);
        cadart.setProjetoAtual(projetoAtual);
        cadart.setIdArte(idArte);

        CadartDao cadartDao = new CadartDao();

        if (cadartDao.updateUsuario(cadart)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }

    @GET
    @Path("/deleteUsuario/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUsuario(@PathParam("cpf") long cpf) throws SQLException {
        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);

        CadartDao cadartDao = new CadartDao();

        if (cadartDao.delete(cadart)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }
    @GET
    @Path("/autenticar/{email}&{senha}")
    @Produces("application/json")
    public String autenticar(@PathParam("email") String email, @PathParam("senha") String senha) throws SQLException, Exception {
        Cadart cadart = new Cadart();
        cadart.setEmail(email);
        cadart.setSenha(senha);

        CadartDao cadartDao = new CadartDao();
        List<Cadart> cadarts = cadartDao.autenticar(cadart);

        Gson gson = new GsonBuilder().create();
        
        tratarImagem(cadarts);
        
        
        JsonArray ArrayUsarios = gson.toJsonTree(cadarts).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("usuario", ArrayUsarios);
        

     

            return jsonObject.toString();
        
    }
    @GET
    @Path("/updateSenha/{cpf}&{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSenha(@PathParam("cpf") long cpf, @PathParam("senha") String senha) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);
        cadart.setSenha(senha);

        CadartDao cadartDao = new CadartDao();

        if (cadartDao.updateSenha(cadart)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }
    
    @GET
    @Path("/updateVisibilidade/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSenha(@PathParam("cpf") long cpf) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Cadart cadart = new Cadart();
        cadart.setCpf(cpf);

        CadartDao cadartDao = new CadartDao();

        if (cadartDao.updateVisibilidade(cadart)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }
    @POST
    @Path("/salvarFoto/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarFoto(@FormDataParam("foto_perfil") InputStream uploadedInputStream,
            @PathParam("cpf") Long cpf, @FormDataParam("foto_perfil") FormDataContentDisposition fileDetail) throws Exception {

        CadartDao cadartDao = new CadartDao();
        Cadart usuario = new Cadart();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            buffer.write(bytes, 0, read);
        }

        byte[] byteArray = buffer.toByteArray();
        buffer.flush();

        usuario.setCpf(cpf);
        usuario.setFotoPerfil(byteArray);
        cadartDao.salvarFoto(usuario);

        return Response.ok().build();
    }
    @GET
    @Path("/find/{cpf}")
    @Produces({"image/png", "image/jpg"})
    public Response find(@PathParam("cpf") Long cpf) throws ServletException, IOException {
        try {

            CadartDao cadartDao = new CadartDao();
            Cadart cadart = new Cadart();
            cadart.setCpf(cpf);
            cadart = cadartDao.getById(cadart).get(0);
            final byte[] foto = cadart.getFotoPerfil();

            if (foto == null) {
                return Response.ok("Imagem não encontrada").build();
            } else {

                return Response.ok(foto).build();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível concluir consulta.").build();
    }
}
