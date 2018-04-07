package br.com.hlnengenharia.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rn-15 on 14/03/2018.
 */

public class Unidade implements Serializable {

    private Long id;
    private Long idEmpresa;
    private String nome;
    private String cidade;


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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public String toString() {
        return getNome() + " - " + getCidade();
    }
}
