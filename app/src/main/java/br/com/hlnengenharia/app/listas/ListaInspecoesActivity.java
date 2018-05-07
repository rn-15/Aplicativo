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

import br.com.hlnengenharia.app.InspecaoActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadDataKmActivity;
import br.com.hlnengenharia.app.dao.EmpresaDAO;
import br.com.hlnengenharia.app.dao.pCarroDAO;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.DataHoraCarro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class ListaInspecoesActivity extends AppCompatActivity {

    private ListView listaRespostas;
    private Button novaInspecao;
    private TextView nomeCarro,idC;
    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inspecoes);

        setTitle("");

        carregaNomeCarro();
        carregaListaInspecoes();

        botaoNovo();

        vaiParaFormInsp();

        registerForContextMenu(listaRespostas);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                DataHoraCarro dataHoraCarro = (DataHoraCarro) listaRespostas.getItemAtPosition(info.position);
                pCarroDAO dao = new pCarroDAO(ListaInspecoesActivity.this);

                dao.deleta(dataHoraCarro);
                dao.close();
                carregaListaInspecoes();

                Toast.makeText(ListaInspecoesActivity.this, "Inspeção do dia  "+ dataHoraCarro.getData()+" deletada!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void vaiParaFormInsp() {
        listaRespostas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent vaiParaFormInsp= new Intent(ListaInspecoesActivity.this, InspecaoActivity.class);
                DataHoraCarro data = (DataHoraCarro) listaRespostas.getItemAtPosition(position);
                vaiParaFormInsp.putExtra("data", data);
                vaiParaFormInsp.putExtra("carro", carro);
                startActivity(vaiParaFormInsp);
            }
        });
    }


    private void botaoNovo() {
        novaInspecao = findViewById(R.id.nova_insp);
        novaInspecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaInspecao = new Intent(ListaInspecoesActivity.this, CadDataKmActivity.class);
                vaiParaInspecao.putExtra("carro", carro);
                startActivity(vaiParaInspecao);
            }
        });
    }

    private void carregaNomeCarro() {
        Intent intent = getIntent();
        carro = (Carro) intent.getSerializableExtra("carro");
        nomeCarro = findViewById(R.id.nomeInsp);
        nomeCarro.setText(carro.getNome());
        idC = findViewById(R.id.idCarroI);
        idC.setText(carro.getId().toString());

    }

    private void carregaListaInspecoes() {
        pCarroDAO dao = new pCarroDAO(this);

        Long idCar = carro.getId();

        List<DataHoraCarro> respostaCarros = dao.buscaData(idCar);
        dao.close();
        listaRespostas = findViewById(R.id.lista_insp);
        ArrayAdapter<DataHoraCarro> adapter = new ArrayAdapter<DataHoraCarro>(this, android.R.layout.simple_list_item_1, respostaCarros);
        listaRespostas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaInspecoes();
    }
}
