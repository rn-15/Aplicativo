package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Setor;

/**
 * Created by rn-15 on 22/03/2018.
 */

public class SetorDAO implements Closeable{

    private HelperDAO dao;
    private Context context;

    public SetorDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close(){
        dao.close();
    }

    public List<Setor> buscaSetor(Long id) {
        List<Setor> setores = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Setores JOIN Unidades ON idUnidade = unidade_id WHERE idUnidade=?", new String[]{id.toString()} );

        while (c.moveToNext())
            setores.add(criaSetor(c));

        c.close();
        return setores;
    }

    public void insere(Setor setor) {
        ContentValues values = pegaSetor(setor);

        dao.getWritableDatabase().insert("Setores", null, values);
    }

    @NonNull
    private ContentValues pegaSetor(Setor setor) {
        ContentValues values = new ContentValues();

        values.put("idUnidade", setor.getIdUnidade());
        values.put("setor_nome", setor.getNome());
        values.put("setor_atividade", setor.getAtividade());
        return values;
    }
    private Setor criaSetor(Cursor c) {
        Setor s = new Setor();
        s.setId(c.getLong(c.getColumnIndex("setor_id")));
        s.setNome(c.getString(c.getColumnIndex("setor_nome")));
        s.setAtividade(c.getString(c.getColumnIndex("setor_atividade")));
        s.setIdUnidade(c.getLong(c.getColumnIndex("idUnidade")));
        return s;
    }

    public void deleta(Setor setor) {
        dao.getWritableDatabase().delete("Setores", "setor_id=?", new String[]{setor.getId().toString()});
    }
}
