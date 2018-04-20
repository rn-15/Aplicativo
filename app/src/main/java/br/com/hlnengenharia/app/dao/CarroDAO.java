package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class CarroDAO implements Closeable {
    private HelperDAO dao;
    private Context context;

    public CarroDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }


    public void insere(Carro carro) {
        ContentValues values = new ContentValues();
            values.put("carro_nome", carro.getNome());
        dao.getWritableDatabase().insert("Carro",null, values);
    }

    public List<Carro> buscaCarros() {
        List<Carro>carros = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Carro",null);
        while (c.moveToNext())
            carros.add(criaCarro(c));
        c.close();
        return carros;
    }

    @NonNull
    private Carro criaCarro(Cursor c) {
        Carro carro = new Carro();
        carro.setId(c.getLong(c.getColumnIndex("carro_id")));
        carro.setNome(c.getString(c.getColumnIndex("carro_nome")));
        return carro;
    }

    public void deleta(Carro carro) {
        dao.getWritableDatabase().delete("Carro", "carro_id=?", new String[]{carro.getId().toString()});
    }

}
