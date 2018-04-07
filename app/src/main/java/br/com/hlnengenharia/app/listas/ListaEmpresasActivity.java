package br.com.hlnengenharia.app.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadEmpresaActivity;
import br.com.hlnengenharia.app.cadastro.CadUnidadeActivity;
import br.com.hlnengenharia.app.dao.EmpresaDAO;
import br.com.hlnengenharia.app.model.Empresa;
import br.com.hlnengenharia.app.model.Unidade;
import br.com.hlnengenharia.app.model.Usuario;

public class ListaEmpresasActivity extends AppCompatActivity {

    public static final String NOME_APPBAR = "Empresas";
    private Button empresanovo, listausuarios;

    private ListView listaEmpresas;
    private Empresa empresa;
    private Unidade unidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresas);

        setTitle(NOME_APPBAR);



        vaiParaCadEmp();
        carregaListaEmpresas();
        vaiParaTelaDeUnidadesDaEmpresa();
        registerForContextMenu(listaEmpresas);

        listaUsuarios();
    }


    private void listaUsuarios() {
        listausuarios = findViewById(R.id.listarUsuario);
        listausuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaEmpresasActivity.this, ListaUsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void vaiParaTelaDeUnidadesDaEmpresa() {
        listaEmpresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent vaiParaListaUnidades = new Intent(ListaEmpresasActivity.this, ListaUnidadesActivity.class);
                Empresa empresa = (Empresa) listaEmpresas.getItemAtPosition(position);
                vaiParaListaUnidades.putExtra("nome", empresa);
                startActivity(vaiParaListaUnidades);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Empresa empresa = (Empresa) listaEmpresas.getItemAtPosition(info.position);
                Intent editaEmpresa = new Intent(ListaEmpresasActivity.this, CadEmpresaActivity.class);
                editaEmpresa.putExtra("empresa", empresa);
                startActivity(editaEmpresa);

                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Empresa empresa = (Empresa) listaEmpresas.getItemAtPosition(info.position);
                EmpresaDAO dao = new EmpresaDAO(ListaEmpresasActivity.this);

                dao.deleta(empresa);
                dao.close();
                carregaListaEmpresas();

                Toast.makeText(ListaEmpresasActivity.this, "Empresa "+ empresa.getNome()+" deletado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void carregaListaEmpresas() {
        EmpresaDAO dao = new EmpresaDAO(this);
        List<Empresa> empresas = dao.buscaUsuarios();
        dao.close();
        listaEmpresas = findViewById(R.id.lista_empresas);
        ArrayAdapter<Empresa> adapter = new ArrayAdapter<Empresa>(this, android.R.layout.simple_list_item_1, empresas);
        listaEmpresas.setAdapter(adapter);
    }

    private void vaiParaCadEmp() {
        empresanovo = findViewById(R.id.empresa_novo);
        empresanovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaCadEmpresa = new Intent(ListaEmpresasActivity.this, CadEmpresaActivity.class);
                startActivity(vaiParaCadEmpresa);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaEmpresas();
    }


}
