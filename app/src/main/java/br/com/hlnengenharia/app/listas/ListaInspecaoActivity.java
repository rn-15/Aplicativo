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

import java.util.List;

import br.com.hlnengenharia.app.InspecaoActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadInspecaoActivity;
import br.com.hlnengenharia.app.cadastro.CadPerguntaActivity;
import br.com.hlnengenharia.app.dao.InspecaoDAO;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Pergunta;
import br.com.hlnengenharia.app.model.Setor;
import br.com.hlnengenharia.app.model.Unidade;

public class ListaInspecaoActivity extends AppCompatActivity {

    private ListView listaInspecao;
    private Unidade unidade;
    private TextView nomeSetor;
    private Button novaInspecao;
    private Setor setor;
    private Inspecao inspecao;
    private Pergunta pergunta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inspecao);
        
        setTitle("Inspeções");

        carregaLista();
        carregaNomeSetor();
        botaoNovo();
        vaiParaFormInspecao();

        registerForContextMenu(listaInspecao);

    }

    private void botaoNovo() {
        novaInspecao = findViewById(R.id.inspecao_novo);
        novaInspecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaInspecaoActivity.this, CadInspecaoActivity.class);
                intent.putExtra("id", setor);
                startActivity(intent);
            }
        });
    }

    private void vaiParaFormInspecao() {
        listaInspecao = findViewById(R.id.lista_inspecao);
        listaInspecao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vaiParaInspecoes = new Intent(ListaInspecaoActivity.this, InspecaoActivity.class);
                Inspecao inspecao = (Inspecao) listaInspecao.getItemAtPosition(position);
                vaiParaInspecoes.putExtra("nome", inspecao);
                startActivity(vaiParaInspecoes);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem cadastro = menu.add("Cadastrar Perguntas");
        cadastro.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Inspecao inspecao = (Inspecao) listaInspecao.getItemAtPosition(info.position);
                Intent cadastraPergunta = new Intent(ListaInspecaoActivity.this, CadPerguntaActivity.class);
                cadastraPergunta.putExtra("inspecao", inspecao);
                startActivity(cadastraPergunta);

                return false;
            }
        });
    }

    private void carregaNomeSetor() {
        Intent intent = getIntent();
        setor = (Setor) intent.getSerializableExtra("nome");
        nomeSetor = findViewById(R.id.inspecao_nomeSetor);
        nomeSetor.setText(setor.getNome());
    }


    private void carregaLista() {
        InspecaoDAO dao = new InspecaoDAO(this);


        List<Inspecao> inspecoes = dao.buscaInspecao();
        dao.close();

        ArrayAdapter<Inspecao> adapter = new ArrayAdapter<Inspecao>(this, android.R.layout.simple_list_item_1, inspecoes);
        listaInspecao = findViewById(R.id.lista_inspecao);
        listaInspecao.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
