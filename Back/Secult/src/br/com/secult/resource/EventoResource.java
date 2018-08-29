/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource; 

import br.com.secult.dao.EventoDao;
import br.com.secult.model.Evento;
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
import java.sql.Timestamp;
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
 * @author Muquifo
 */
@Path("/evento")
public class EventoResource {

    @GET
    @Path("/insertEvento/{titulo}&{descricao}&{data_evento}&{visibilidade}&{tipo_evento}&{hora_evento}&{id_povoado}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertEvento(@PathParam("titulo") String titulo, @PathParam("descricao") String descricao, @PathParam("data_evento") String data_evento, @PathParam("visibilidade") String visibilidade, @PathParam("tipo_evento") String tipo_evento, @PathParam("hora_evento") String hora_evento, @PathParam("id_povoado") int id_povoado) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Evento evento = new Evento();

        evento.setTitulo(titulo);
        evento.setDescricao(descricao);
        evento.setData_evento(data_evento);
        evento.setVisibilidade(visibilidade);
        evento.setTipo_evento(tipo_evento);
        evento.setHora_evento(hora_evento);
        evento.setId_localidade(id_povoado);

        EventoDao eventoDao = new EventoDao();

        if (eventoDao.insertEvento(evento)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }

    }

    @GET
    @Path("/listarEvento")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEvento() throws SQLException, Exception {

        EventoDao eventoDao = new EventoDao();
        List<Evento> even = eventoDao.listaEventos();

        Gson gson = new GsonBuilder().create();

        tratarImagem(even);

        JsonArray ArrayUsuarios = gson.toJsonTree(even).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("eventos", ArrayUsuarios);

        return jsonObject.toString();

    }

    public void tratarImagem(List<Evento> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getImagem() != null) {

                String foto = usuarios.get(i).getImagem().toString();

                usuarios.get(i).setImagem(foto.getBytes());

            }
        }
    }

    @POST
    @Path("/salvarFoto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarFoto(@FormDataParam("imagem") InputStream uploadedInputStream,
            @PathParam("id") Long id, @FormDataParam("imagem") FormDataContentDisposition fileDetail) throws Exception {

        EventoDao usuarioDao = new EventoDao();
        Evento usuario = new Evento();

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = uploadedInputStream.read(bytes)) != -1) {
            buffer.write(bytes, 0, read);
        }

        byte[] byteArray = buffer.toByteArray();
        buffer.flush();

        usuario.setId(id);
        usuario.setImagem(byteArray);
        usuarioDao.salvarFoto(usuario);

        return Response.ok().build();
    }

    @GET
    @Path("/find/{id}")
    @Produces({"image/png", "image/jpg"})
    public Response find(@PathParam("id") Long id) throws ServletException, IOException {
        try {

            EventoDao eventoDao = new EventoDao();
            Evento evento = new Evento();
            evento.setId(id);
            evento = eventoDao.getById(evento).get(0);
            final byte[] foto = evento.getImagem();

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
    @Path("/updateEvento/{id}&{titulo}&{descricao}&{data_evento}&{visibilidade}&{tipo_evento}&{hora_evento}&{id_povoado}")
    @Produces(MediaType.APPLICATION_JSON)
    public String upadetEvento(@PathParam("id") long id, @PathParam("titulo") String titulo, @PathParam("descricao") String descricao, @PathParam("data_evento") String data_evento, @PathParam("visibilidade") String visibilidade, @PathParam("tipo_evento") String tipo_evento, @PathParam("hora_evento") String hora_evento, @PathParam("id_povoado") int id_povoado) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Evento evento = new Evento();

        evento.setId(id);
        evento.setTitulo(titulo);
        evento.setDescricao(descricao);
        evento.setData_evento(data_evento);
        evento.setVisibilidade(visibilidade);
        evento.setTipo_evento(tipo_evento);
        evento.setHora_evento(hora_evento);
        evento.setId_localidade(id_povoado);

        EventoDao eventoDao = new EventoDao();

        if (eventoDao.updateEvento(evento)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }

    @GET
    @Path("/listarEventoGrande")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEventoG() throws SQLException, Exception {
        Evento even = new Evento();
        EventoDao eventoDao = new EventoDao();
        List<Evento> evento = eventoDao.listarEventoGrande(even);

        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsuarios = gson.toJsonTree(evento).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("eventos", ArrayUsuarios);

        return jsonObject.toString();
    }
    
    @GET
    @Path("/listarEventoPequeno")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarEventoP() throws SQLException, Exception {
        Evento even = new Evento();
        EventoDao eventoDao = new EventoDao();
        List<Evento> evento = eventoDao.listarEventoPequeno(even);

        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsuarios = gson.toJsonTree(evento).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("eventos", ArrayUsuarios);

        return jsonObject.toString();
    }
}
