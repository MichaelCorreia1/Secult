/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.model;

/**
 *
 * @author Lucas
 */
public class Aluno extends Usuario{

    private long id_usuario;
    private long id_aluno;
    private String curso;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
