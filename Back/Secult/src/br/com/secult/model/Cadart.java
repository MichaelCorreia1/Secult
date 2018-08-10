/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.model;

import java.sql.Date;

/**
 *
 * @author David
 */
public class Cadart {
    
    private long cpf;
    private String nome;
    private String nomeArtistico;
    private String sexo;
    private byte [] fotoPerfil;
    private String descricao;
    private String projetoAtual;
    private Date dataNascimento;
    private String senha;
    private int idArte;

  
    public long getCpf() {
        return cpf;
    }

   
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }

  
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

   
    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

   
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

  
    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

 
    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

 
    public String getDescricao() {
        return descricao;
    }

   
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getProjetoAtual() {
        return projetoAtual;
    }

   
    public void setProjetoAtual(String projetoAtual) {
        this.projetoAtual = projetoAtual;
    }

   
    public Date getDataNascimento() {
        return dataNascimento;
    }

   
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

 
    public String getSenha() {
        return senha;
    }

 
    public void setSenha(String senha) {
        this.senha = senha;
    }

 
    public int getIdArte() {
        return idArte;
    }

  
    public void setIdArte(int idArte) {
        this.idArte = idArte;
    }

    public void setIdArte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
