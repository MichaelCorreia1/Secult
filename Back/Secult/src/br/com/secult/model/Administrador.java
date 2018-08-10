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
public class Administrador extends Usuario{

    private long id_usuario;
    private long id_adm;
    private String cargo;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_adm() {
        return id_adm;
    }

    public void setId_adm(long id_adm) {
        this.id_adm = id_adm;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

   
}
