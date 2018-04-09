package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Carro;

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

    public void insereCarro(Carro carro) {
        ContentValues values = new ContentValues();
            values.put("carro_pergunta", carro.getPergunta());
        dao.getWritableDatabase().insert("Carro", null, values);
    }

    public List<Carro> buscaPergunta() {
        List<Carro> carros = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT carro_pergunta FROM Carro", null);
        while (c.moveToNext())
            carros.add(criaPerguntaCarro(c));
        c.close();
        return carros;
    }

    private Carro criaPerguntaCarro(Cursor c) {
        Carro carro = new Carro();
            carro.setPergunta(c.getString(c.getColumnIndex("carro_pergunta")));
        return carro;
    }

}
