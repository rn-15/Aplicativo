package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

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
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM CarroPergunta",null);
            while (c.moveToNext()){
                PerguntaCarro p = new PerguntaCarro();
                    p.setId(c.getLong(c.getColumnIndex("cpergunta_id")));
                    p.setPergunta(c.getString(c.getColumnIndex("cpergunta")));
                 perguntas.add(p);
            }c.close();
            return perguntas;
    }

    public void inserir(RespostaCarro resposta) {
        ContentValues values = new ContentValues();
            values.put("idCarro", resposta.getIdCarro());
            values.put("idPergunta", resposta.getIdPergunta());
            values.put("cresposta_desc", resposta.getResposta());
            values.put("cresposta_obs", resposta.getObs());
        dao.getWritableDatabase().insert("CarroResposta", null, values);
    }

    public List<RespostaCarro> buscaResposta(Long idCar) {
        List<RespostaCarro> respostas = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM DiaHora JOIN Carro ON idCarro=carro_id WHERE idCarro=?",new String[]{idCar.toString()});
        while (c.moveToNext()){
            RespostaCarro r = new RespostaCarro();
                r.setIdCarro(c.getLong(c.getColumnIndex("idCarro")));
                r.setData(c.getString(c.getColumnIndex("dia")));
                r.setHora(c.getString(c.getColumnIndex("hora")));
                r.setKm(c.getString(c.getColumnIndex("km")));
         respostas.add(r);
        }c.close();
        return respostas;
    }

    @NonNull
    private RespostaCarro criaResposta(Cursor c) {
        RespostaCarro r = new RespostaCarro();
        r.setId(c.getLong(c.getColumnIndex("cresposta_id")));
        r.setIdCarro(c.getLong(c.getColumnIndex("idCarro")));
        r.setIdPergunta(c.getLong(c.getColumnIndex("idPergunta")));
        r.setResposta(c.getString(c.getColumnIndex("cresposta_desc")));
        r.setObs(c.getString(c.getColumnIndex("cresposta_obs")));
        return r;
    }

    public void pega(RespostaCarro horaData) {
        ContentValues values = new ContentValues();
            values.put("idCarro", horaData.getIdCarro());
            values.put("dia", horaData.getData());
            values.put("hora", horaData.getHora());
            values.put("km", horaData.getKm());
        dao.getWritableDatabase().insert("DiaHora", null, values);
    }
}
