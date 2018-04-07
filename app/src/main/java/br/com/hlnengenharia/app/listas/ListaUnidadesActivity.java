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
import br.com.hlnengenharia.app.cadastro.CadUnidadeActivity;
import br.com.hlnengenharia.app.dao.UnidadeDAO;
import br.com.hlnengenharia.app.helper.UnidadeHelper;
import br.com.hlnengenharia.app.model.Empresa;
import br.com.hlnengenharia.app.model.Unidade;
import br.com.hlnengenharia.app.model.Usuario;

public class ListaUnidadesActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Unidades";
    private Button unidadeNovo;
    private ListView listaUnidades;
    private TextView id;
    private Empresa empresa;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_unidades);

        setTitle(TITULO_APPBAR);
        carregaNomeDaEmpresa();
        vaiParaCadUnidade();
        carregaListaDeUnidades();
        clicaNoItemDaLista();
        listaUnidades = findViewById(R.id.lista_unidades);
        registerForContextMenu(listaUnidades);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Unidade unidade = (Unidade) listaUnidades.getItemAtPosition(info.position);
                Intent editaUnidade = new Intent(ListaUnidadesActivity.this, CadUnidadeActivity.class);
                editaUnidade.putExtra("unidade", unidade);
                editaUnidade.putExtra("empresa", empresa);
                startActivity(editaUnidade);

                return false;
            }
        });
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Unidade unidade = (Unidade) listaUnidades.getItemAtPosition(info.position);
                UnidadeDAO dao = new UnidadeDAO(ListaUnidadesActivity.this);

                dao.deleta(unidade);
                dao.close();
                carregaListaDeUnidades();

                Toast.makeText(ListaUnidadesActivity.this, "Unidade "+ unidade.getNome()+" deletado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void clicaNoItemDaLista() {
        listaUnidades = findViewById(R.id.lista_unidades);

        listaUnidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiParaSetores = new Intent(ListaUnidadesActivity.this, ListaSetoresActivity.class);
                Unidade unidade = (Unidade) listaUnidades.getItemAtPosition(position);
                vaiParaSetores.putExtra("nome", unidade);
                startActivity(vaiParaSetores);
            }
        });
    }

    private void carregaNomeDaEmpresa() {
        Intent intent = getIntent();
        empresa = (Empresa) intent.getSerializableExtra("nome");
        id = findViewById(R.id.nomeEmpresa);
        id.setText(empresa.getNome());
    }

    private void vaiParaCadUnidade() {
        unidadeNovo = findViewById(R.id.unidade_novo);
        unidadeNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaCadUnidade = new Intent(ListaUnidadesActivity.this, CadUnidadeActivity.class);
                vaiParaCadUnidade.putExtra("empresa", empresa);
                startActivity(vaiParaCadUnidade);
            }
        });
    }

    private void carregaListaDeUnidades() {
        UnidadeDAO dao = new UnidadeDAO(this);

        Long idEmp = empresa.getId();

        List<Unidade> unidades = dao.buscaUnidades(idEmp);
        dao.close();
        ArrayAdapter<Unidade> adapter = new ArrayAdapter<Unidade>(this, android.R.layout.simple_list_item_1, unidades);
        listaUnidades = findViewById(R.id.lista_unidades);
        listaUnidades.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        carregaListaDeUnidades();
    }




}
