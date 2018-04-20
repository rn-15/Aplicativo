package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Pergunta;

public class PerguntaDAO implements Closeable {

    private HelperDAO dao;
    private Context context;


    public PerguntaDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }



    public List<Pergunta> buscaPergunta(){
        List<Pergunta> perguntas = new ArrayList<>();

        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Altura", null);

        while (c.moveToNext())
            perguntas.add(criaPergunta(c));
        c.close();
        return perguntas;
    }

    private Pergunta criaPergunta(Cursor c) {
        Pergunta p = new Pergunta();
        p.setId(c.getLong(c.getColumnIndex("pergunta_id")));
        p.setPergunta(c.getString(c.getColumnIndex("pergunta_desc")));
        p.setIdInspecao(c.getLong(c.getColumnIndex("idInspecao")));
        return p;
    }
}
