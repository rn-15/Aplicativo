package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.PerguntaCarro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class pCarroDAO implements Closeable {


    private final Context context;
    private HelperDAO dao;

    public pCarroDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }

    public void insere(PerguntaCarro perguntaCarro) {
        ContentValues values = new ContentValues();
            values.put("cpergunta", perguntaCarro.getPergunta());
        dao.getWritableDatabase().insert("CarroPergunta",null, values);
    }

    public List<PerguntaCarro> buscaPerguntaCarro() {
        List<PerguntaCarro> perguntas = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT cpergunta FROM CarroPergunta",null);
            while (c.moveToNext()){
                PerguntaCarro p = new PerguntaCarro();
                    p.setPergunta(c.getString(c.getColumnIndex("cpergunta")));
                 perguntas.add(p);
            }c.close();
            return perguntas;
    }

    public void inserir(RespostaCarro resposta) {
        ContentValues values = new ContentValues();
            values.put("idPergunta", resposta.getIdPergunta());
            values.put("idCarro", resposta.getIdCarro());
            values.put("cresposta_desc", resposta.getResposta());
        dao.getWritableDatabase().insert("CarroResposta", null, values);
    }
}
