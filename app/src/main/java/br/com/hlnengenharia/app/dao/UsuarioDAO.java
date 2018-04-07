package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.View;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.Usuario;

/**
 * Created by rn-15 on 08/03/2018.
 */

public class UsuarioDAO implements Closeable {

    private HelperDAO dao;
    private Context context;

    public UsuarioDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close(){
        dao.close();
    }

    public void insere(Usuario usuario) {
        ContentValues values = pegaUsuario(usuario);

        dao.getWritableDatabase().insert("Usuarios", null, values);
    }

    @NonNull
    private ContentValues pegaUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();

        values.put("usuario_nome", usuario.getNome());
        values.put("usuario_profissao", usuario.getProfissao());
        values.put("usuario_email", usuario.getEmail());
        values.put("usuario_telefone", usuario.getTelefone());
        values.put("usuario_senha", usuario.getSenha());
        values.put("usuario_confirmaSenha", usuario.getConfirmaSenha());
        return values;
    }

    public List<Usuario> buscaUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM Usuarios", null);
        while (c.moveToNext())
            usuarios.add(criaUsuario(c));
        c.close();
        return usuarios;
    }

    private Usuario criaUsuario(Cursor c) {
        Usuario usuario = new Usuario();

        usuario.setId(c.getLong(c.getColumnIndex("usuario_id")));
        usuario.setNome(c.getString(c.getColumnIndex("usuario_nome")));
        usuario.setProfissao(c.getString(c.getColumnIndex("usuario_profissao")));
        usuario.setEmail(c.getString(c.getColumnIndex("usuario_email")));
        usuario.setTelefone(c.getString(c.getColumnIndex("usuario_telefone")));
        usuario.setSenha(c.getString(c.getColumnIndex("usuario_senha")));
        usuario.setConfirmaSenha(c.getString(c.getColumnIndex("usuario_confirmaSenha")));

        return usuario;
    }

    public void deleta(Usuario usuario) {
        dao.getWritableDatabase().delete("Usuarios", "usuario_id=?", new String[]{usuario.getId().toString()});
    }

    public void editar(Usuario usuario) {
        ContentValues values  = pegaUsuario(usuario);
        String[] params = {usuario.getId().toString()};
        dao.getWritableDatabase().update("Usuarios", values, "usuario_id=?", params);
    }


    public Usuario validarLogin(String login, String senha) {

        String[] selectionArgs = new String[]{login, senha};
        Cursor cursor = dao.getReadableDatabase().rawQuery("SELECT * FROM Usuarios WHERE usuario_nome=? AND usuario_senha=?", selectionArgs);
        Usuario usuarioLinha = null;
        while (cursor.moveToNext()) {
            usuarioLinha = new Usuario();
            usuarioLinha.setNome(cursor.getString(cursor.getColumnIndex("usuario_nome")));
            usuarioLinha.setSenha(cursor.getString(cursor.getColumnIndex("usuario_senha")));
        }cursor.close();
        return usuarioLinha;
    }
}
