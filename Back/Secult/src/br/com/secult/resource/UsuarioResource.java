package br.com.secult.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.dao.UsuarioDao;

import br.com.secult.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;

import com.sun.jersey.multipart.FormDataParam;
import java.io.IOException;
import javax.servlet.ServletException;

@Path("/usuario")
public class UsuarioResource {

    @GET
    @Path("/listarUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarUsuarios() throws SQLException, Exception {

        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.listar();

        Gson gson = new GsonBuilder().create();

        tratarImagem(usuarios);

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
    @Path("/listarUsuariosById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarUsuariosById(@PathParam("id") long id) throws SQLException, Exception {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.listarById(usuario);

        Gson gson = new GsonBuilder().create();

        tratarImagem(usuarios);

        JsonArray ArrayUsarios = gson.toJsonTree(usuarios).getAsJsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("usuarios", ArrayUsarios);

        if (usuarios.size() > 0) {

            return jsonObject.toString();
        } else {

           return jsonObject.toString();
        }

    }

    public void tratarImagem(List<Usuario> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getFoto() != null) {

                String foto = usuarios.get(i).getFoto().toString();

                usuarios.get(i).setFoto(foto.getBytes());

            }
        }
    }

    @GET
    @Path("/autenticar/{email}&{senha}")
    @Produces("application/json")
    public String autenticar(@PathParam("email") String email, @PathParam("senha") String senha) throws SQLException, Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = usuarioDao.autenticar(usuario);

        Gson gson = new GsonBuilder().create();
        
         tratarImagem(usuarios);
        
        
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
    @Path("/insertUsuario/{nome}&{email}&{senha}&{tipo}&{endereco}&{telefone}&{cidade}&{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUsuario(@PathParam("nome") String nome, @PathParam("email") String email, @PathParam("senha") String senha, @PathParam("tipo") String tipo, @PathParam("endereco") String endereco, @PathParam("telefone") long telefone, @PathParam("cidade") String cidade, @PathParam("estado") String estado) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);
        usuario.setEndereco(endereco);
        usuario.setTelefone(telefone);
        usuario.setCidade(cidade);
        usuario.setEstado(estado);

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.insert(usuario)) {

            return "{\"status\":\"ok\", \"id_usuario\":\"" + usuario.getId() + "\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }

    @GET
    @Path("/updateUsuario/{id}&{nome}&{email}&{senha}&{tipo}&{endereco}&{telefone}&{cidade}&{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUsuario(@PathParam("id") long id, @PathParam("nome") String nome, @PathParam("email") String email, @PathParam("senha") String senha, @PathParam("tipo") String tipo, @PathParam("endereco") String endereco, @PathParam("telefone") long telefone, @PathParam("cidade") String cidade, @PathParam("estado") String estado) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);
        usuario.setEndereco(endereco);
        usuario.setTelefone(telefone);
        usuario.setCidade(cidade);
        usuario.setEstado(estado);
        

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.update(usuario)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }

    @GET
    @Path("/updateSenha/{id}&{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateSenha(@PathParam("id") long id, @PathParam("senha") String senha) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setSenha(senha);

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.updateSenha(usuario)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }

    @GET
    @Path("/deleteUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUsuario(@PathParam("id") long id) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(id);

        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.delete(usuario)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }

    @POST
    @Path("/salvarFoto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarFoto(@FormDataParam("foto") InputStream uploadedInputStream,
            @PathParam("id") Long id, @FormDataParam("foto") FormDataContentDisposition fileDetail) throws Exception {

        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = new Usuario();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            buffer.write(bytes, 0, read);
        }

        byte[] byteArray = buffer.toByteArray();
        buffer.flush();

        usuario.setId(id);
        usuario.setFoto(byteArray);
        usuarioDao.salvarFoto(usuario);

        return Response.ok().build();
    }

    @GET
    @Path("/find/{id}")
    @Produces({"image/png", "image/jpg"})
    public Response find(@PathParam("id") Long id) throws ServletException, IOException {
        try {

            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario = usuarioDao.getById(usuario).get(0);
            final byte[] foto = usuario.getFoto();

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
    
    @GET
    @Path("/updateUsuario/{nome}&{email}&{endereco}&{telefone}&{cidade}&{estado}&{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUsuario(@PathParam("nome") String nome, @PathParam("email") String email, @PathParam("endereco") String endereco, @PathParam("telefone") long telefone , @PathParam("cidade") String cidade , @PathParam("estado") String estado, @PathParam("id") long id ) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, Exception {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setEndereco(endereco);
        usuario.setTelefone(telefone);
        usuario.setCidade(cidade);
        usuario.setEstado(estado);
        usuario.setId(id);
              
        
        UsuarioDao usuarioDao = new UsuarioDao();

        if (usuarioDao.updateUsuario(usuario)) {

            return "{\"status\":\"ok\"}";
        } else {

            return "{\"status\":\"erro\"}";
        }
    }
    
    

}
