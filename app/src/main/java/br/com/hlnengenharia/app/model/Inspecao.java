package br.com.hlnengenharia.app.model;

import java.io.Serializable;

/**
 * Created by rn-15 on 20/03/2018.
 */

public class Inspecao implements Serializable{
    private Long id;
    private Long idSetor;
    private String nome;

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
