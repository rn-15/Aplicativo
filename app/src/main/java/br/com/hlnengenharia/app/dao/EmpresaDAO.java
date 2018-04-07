package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Empresa;

/**
 * Created by rn-15 on 14/03/2018.
 */

public class EmpresaDAO implements Closeable {
    private HelperDAO dao;
    private Context context;

    public EmpresaDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }


    @Override
    public void close() {
        dao.close();
    }

    public void insere(Empresa empresa) {
        ContentValues values = pegaEmpresa(empresa);
        dao.getWritableDatabase().insert("Empresas",null, values);
    }
    public List<Empresa> buscaUsuarios() {
        List<Empresa> empresas = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Empresas", null);
        while (c.moveToNext())
            empresas.add(criaEmpresa(c));
        c.close();
        return empresas;
    }

    private Empresa criaEmpresa(Cursor c) {
        Empresa e = new Empresa();
        e.setId(c.getLong(c.getColumnIndex("empresa_id")));
        e.setNome(c.getString(c.getColumnIndex("empresa_nome")));
        e.setSegmento(c.getString(c.getColumnIndex("empresa_segmento")));

        return e;
    }

    @NonNull
    private ContentValues pegaEmpresa(Empresa empresa) {
        ContentValues values = new ContentValues();
        values.put("empresa_nome", empresa.getNome() );
        values.put("empresa_segmento", empresa.getSegmento());
        return values;
    }

    public void deleta(Empresa empresa) {
        dao.getWritableDatabase().delete("Empresas", "empresa_id=?",new String[]{empresa.getId().toString()});
    }

    public void editar(Empresa empresa) {
        ContentValues values = pegaEmpresa(empresa);
        String[]params = {empresa.getId().toString()};
        dao.getWritableDatabase().update("Empresas", values, "empresa_id=?",params);
    }

}
