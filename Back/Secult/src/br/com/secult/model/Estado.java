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
public class Estado{

    private long id;
    private long codigoUf;
    private String nome;
    private String uf;
    private long regiao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(long codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getRegiao() {
        return regiao;
    }

    public void setRegiao(long regiao) {
        this.regiao = regiao;
    }

}