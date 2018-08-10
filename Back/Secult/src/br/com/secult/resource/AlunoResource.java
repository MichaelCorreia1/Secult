package br.com.secult.resource;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import br.com.secult.dao.AlunoDao;
import br.com.secult.model.Aluno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/aluno")
public class AlunoResource {
    
    @GET
    @Path("/insertAluno/{id_usuario}&{id_aluno}&{curso}")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAluno(@PathParam("id_usuario") long id_usuario, @PathParam("id_aluno") long id_aluno, @PathParam("curso") String curso) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        
        Aluno aluno = new Aluno();
        aluno.setId_aluno(id_aluno);
        aluno.setId_usuario(id_usuario);
        aluno.setCurso(curso);
        
        AlunoDao alunoDao = new AlunoDao();
        
        if (alunoDao.insert(aluno)) {
            
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
    
    @GET
    @Path("/listarAlunos")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarAlunos() throws SQLException, Exception {

        AlunoDao alunoDao = new AlunoDao();
        List<Aluno> alunos = alunoDao.listarAlunos();
        tratarImagem(alunos);
        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(alunos).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("alunos", ArrayUsarios);

        if (alunos.size() > 0) {

            return jsonObject.toString();
        } else {

           return jsonObject.toString();
        }

    }
    
 
    
    @GET
    @Path("/listarAlunosById/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarAlunosById(@PathParam("id_usuario") long id_usuario) throws SQLException, Exception {

        Aluno aluno = new Aluno();
        aluno.setId_usuario(id_usuario);
       
        
        AlunoDao alunoDao = new AlunoDao();
        List<Aluno> alunos = alunoDao.listarAlunosById(aluno);
        tratarImagem(alunos);
        
        Gson gson = new GsonBuilder().create();
        JsonArray ArrayUsarios = gson.toJsonTree(alunos).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("alunos", ArrayUsarios);
        
        return jsonObject.toString();
        
    }
    
    
    
     @GET
    @Path("/updateAluno/{curso}&{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUsuario(@PathParam("curso") String curso, @PathParam("id_usuario") long id_usuario ) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, Exception {
        Aluno aluno = new Aluno();
        aluno.setCurso(curso);
        aluno.setId_usuario(id_usuario);
        
        AlunoDao alunoDao = new AlunoDao();

        if (alunoDao.updateAluno(aluno)) {
            return "{\"status\":\"ok\"}";
        } else {
            return "{\"status\":\"erro\"}";
        }
    }
    public void tratarImagem(List<Aluno> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getFoto() != null) {

                String foto = usuarios.get(i).getFoto().toString();

                usuarios.get(i).setFoto(foto.getBytes());

            }
        }
    }
    
}
