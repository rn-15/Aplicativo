package br.com.hlnengenharia.app.model;

import java.io.Serializable;
import java.io.SerializablePermission;

public class PerguntaCarro implements Serializable {
    private Long id;
    private String pergunta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
