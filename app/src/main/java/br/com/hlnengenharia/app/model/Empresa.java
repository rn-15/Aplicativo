package br.com.hlnengenharia.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rn-15 on 13/03/2018.
 */

public class Empresa implements Serializable {
    private Long id;
    private String nome;
    private String segmento;


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

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }



    @Override
    public String toString() {return getNome();}
}
