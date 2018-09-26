package br.com.secult.model;


public class RedesSociais {
    private int id;
    private int idCadart;
    private String nomeRede;
    private String nomeLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCadart() {
        return idCadart;
    }

    public void setIdCadart(int idCadart) {
        this.idCadart = idCadart;
    }

    public String getNomeRede() {
        return nomeRede;
    }

    public void setNomeRede(String nomeRede) {
        this.nomeRede = nomeRede;
    }

    public String getNomeLink() {
        return nomeLink;
    }

    public void setNomeLink(String nomeLink) {
        this.nomeLink = nomeLink;
    }
}
