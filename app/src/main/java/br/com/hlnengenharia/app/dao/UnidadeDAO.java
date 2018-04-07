package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.cadastro.CadEmpresaActivity;
import br.com.hlnengenharia.app.model.Empresa;
import br.com.hlnengenharia.app.model.Unidade;

/**
 * Created by rn-15 on 15/03/2018.
 */

public class UnidadeDAO implements Closeable {

    private HelperDAO dao;
    private Context context;
    protected static final String UNIDADES = "Unidades";
    protected static final String ID = "unidade_id";
    protected static final String NOME = "unidade_nome";
    protected static final String CIDADE = "unidade_cidade";
    protected static final String ID_EMPRESA = "idEmpresa";
    private Empresa empresa;
    private Unidade unidade;



    public UnidadeDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }

    public void insere(Unidade unidade) {
        ContentValues values = pegaUnidade(unidade);

       dao.getWritableDatabase().insert(UNIDADES, null, values);
    }


    public List<Unidade> buscaUnidades(Long idEmp) {
        List<Unidade> unidades = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Unidades JOIN Empresas ON idEmpresa = empresa_id WHERE idEmpresa=?", new String[]{idEmp.toString()} );

        while (c.moveToNext())
            unidades.add(criaUnidade(c));

        c.close();
        return unidades;
    }

    private Unidade criaUnidade(Cursor c){
        Unidade u = new Unidade();
        u.setId(c.getLong(c.getColumnIndex(ID)));
        u.setNome(c.getString(c.getColumnIndex(NOME)));
        u.setCidade(c.getString(c.getColumnIndex(CIDADE)));
        u.setIdEmpresa(c.getLong(c.getColumnIndex(ID_EMPRESA)));


        return u;
    }

    @NonNull
    private ContentValues pegaUnidade(Unidade unidade) {
        ContentValues values = new ContentValues();

        values.put(ID_EMPRESA, unidade.getIdEmpresa());
        values.put(NOME, unidade.getNome());
        values.put(CIDADE, unidade.getCidade());
        return values;
    }


    public void deleta(Unidade unidade) {
        dao.getWritableDatabase().delete("Unidades", "unidade_id=?", new String[]{unidade.getId().toString()});
    }

    public void altera(Unidade unidade) {
        ContentValues values = pegaUnidade(unidade);
        dao.getWritableDatabase().update("Unidades", values, "unidade_id=?", new String[]{unidade.getId().toString()});
    }
}
