/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.model;

/**
 *
 * @author Muquifo
 */
public class Mensagem {
    private long id_usuario;
    private int id_mensagem;
    private String titulo; 
    private String corpo;
    private String lida;
    private long qtd;
    
    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }
    
     public int getId_mensagem() {
        return id_mensagem;
    }

    public void setId_mensagem(int id_mensagem) {
        this.id_mensagem = id_mensagem;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
     public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }
    
    public String getLida() {
        return lida;
    }

    public void setLida(String lida) {
        this.lida = lida;
    }
    
    public long getQtd() {
        return qtd;
    }

    public void setQtd(long qtd) {
        this.qtd = qtd;
    }
    
    
}
