package br.com.hlnengenharia.app.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadUsuarioActivity;
import br.com.hlnengenharia.app.dao.UsuarioDAO;
import br.com.hlnengenharia.app.model.Usuario;

public class ListaUsuariosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Usuários Cadastrados";
    ListView listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        setTitle(TITULO_APPBAR);

        carregaListaUsuarios();
        registerForContextMenu(listaUsuarios);
    }

    private void carregaListaUsuarios() {
        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuarios = dao.buscaUsuarios();
        dao.close();

        listaUsuarios = findViewById(R.id.listaUsuarios_listView);
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, usuarios);
        listaUsuarios.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaUsuarios();
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem visualizar = menu.add("Visualizar");
        visualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Usuario usuario = (Usuario) listaUsuarios.getItemAtPosition(info.position);


                return false;
            }
        });
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Usuario usuario = (Usuario) listaUsuarios.getItemAtPosition(info.position);
                Intent editaAluno = new Intent(ListaUsuariosActivity.this, CadUsuarioActivity.class);
                editaAluno.putExtra("usuario", usuario);
                startActivity(editaAluno);

                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Usuario usuario = (Usuario) listaUsuarios.getItemAtPosition(info.position);
                UsuarioDAO dao = new UsuarioDAO(ListaUsuariosActivity.this);

                dao.deleta(usuario);
                dao.close();
                carregaListaUsuarios();

                Toast.makeText(ListaUsuariosActivity.this, "Usuario "+ usuario.getNome()+" deletado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
