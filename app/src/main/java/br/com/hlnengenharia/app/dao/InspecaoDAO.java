package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.dao.HelperDAO;
import br.com.hlnengenharia.app.model.Inspecao;

import static br.com.hlnengenharia.app.dao.UnidadeDAO.NOME;

/**
 * Created by rn-15 on 20/03/2018.
 */

public class InspecaoDAO implements Closeable{
    private HelperDAO dao;
    private Context context;
    protected static final String INSPECOES = "Inspecoes";
    protected static final String ID = "inspecao_id";
    protected static final String NOME = "inspecao_nome";
    protected static final String ID_SETOR = "idSetor";



    public InspecaoDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }


    public void insere(Inspecao inspecao) {
        ContentValues values = pegaInspecao(inspecao);

        dao.getWritableDatabase().insert(INSPECOES, null, values);
    }

    public List<Inspecao> buscaInspecao() {
        List<Inspecao> i = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Inspecoes", null);
        while (c.moveToNext())
            i.add(criaSetor(c));
        c.close();
        return i;
    }

    @NonNull
    private Inspecao criaSetor(Cursor c) {
        Inspecao in = new Inspecao();
        in.setId(c.getLong(c.getColumnIndex(ID)));
        in.setNome(c.getString(c.getColumnIndex(NOME)));
        in.setIdSetor(c.getLong(c.getColumnIndex(ID_SETOR)));
        return in;
    }
    @NonNull
    private ContentValues pegaInspecao(Inspecao inspecao) {
        ContentValues values = new ContentValues();
        values.put(NOME, inspecao.getNome());
        values.put(ID_SETOR, inspecao.getIdSetor());
        return values;
    }
}
