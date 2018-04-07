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

import org.w3c.dom.Text;

import java.util.List;
import java.util.Set;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadSetorActivity;
import br.com.hlnengenharia.app.dao.SetorDAO;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Setor;
import br.com.hlnengenharia.app.model.Unidade;

public class ListaSetoresActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Setores";
    private Setor setor;
    private Unidade unidade;
    private TextView nomeUnidade;
    private ListView listaSetor;
    private Button novoSetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_setores);

        setTitle(TITULO_APPBAR);
        carregaNomeUnidade();
        carregaLista();

        vaiParaTelaCadastro();
        vaiParaListaInspecao();
        registerForContextMenu(listaSetor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Setor setor = (Setor) listaSetor.getItemAtPosition(info.position);
                SetorDAO dao = new SetorDAO(ListaSetoresActivity.this);

                dao.deleta(setor);
                dao.close();


                return false;
            }
        });
    }

    private void vaiParaListaInspecao() {
        listaSetor = findViewById(R.id.lista_setores);
        listaSetor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiParaListaInspecoes = new Intent(ListaSetoresActivity.this, ListaInspecaoActivity.class);
                Setor setor = (Setor) listaSetor.getItemAtPosition(position);
                vaiParaListaInspecoes.putExtra("nome", setor);
                startActivity(vaiParaListaInspecoes);

            }
        });
    }


    private void vaiParaTelaCadastro() {
        novoSetor = findViewById(R.id.setor_novo);
        novoSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaCadSetor = new Intent(ListaSetoresActivity.this, CadSetorActivity.class);
                vaiParaCadSetor.putExtra("unidade", unidade);
                startActivity(vaiParaCadSetor);
            }
        });
    }

    private void carregaLista() {
        SetorDAO dao = new SetorDAO(ListaSetoresActivity.this);
        Long id = unidade.getId();
        List<Setor> setores = dao.buscaSetor(id);
        dao.close();
        ArrayAdapter<Setor> adapter = new ArrayAdapter<Setor>(this, android.R.layout.simple_list_item_1, setores);
        listaSetor = findViewById(R.id.lista_setores);
        listaSetor.setAdapter(adapter);
    }

    private void carregaNomeUnidade() {
        Intent intent = getIntent();
        unidade = (Unidade) intent.getSerializableExtra("nome");
        nomeUnidade = findViewById(R.id.nomeUnidade);
        nomeUnidade.setText(unidade.getNome());
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
