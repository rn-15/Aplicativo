package br.com.hlnengenharia.app.model;

import java.io.Serializable;

/**
 * Created by rn-15 on 22/03/2018.
 */

public class Setor implements Serializable{
    private Long id;
    private Long idUnidade;
    private String nome;
    private String atividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
