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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.InspecaoActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadCarroActivity;
import br.com.hlnengenharia.app.dao.CarroDAO;
import br.com.hlnengenharia.app.model.Carro;

public class ListaCarrosActivity extends AppCompatActivity {

    private ListView listaCarros;
    private Button novoCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);
        listaCarros = findViewById(R.id.lista_carros);
        registerForContextMenu(listaCarros);

        setTitle("");
        vaiParaCadCarros();
        carregaListaCarros();

        listaCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent vaiParaInspecao = new Intent(ListaCarrosActivity.this, InspecaoActivity.class);
                Carro carro = (Carro) listaCarros.getItemAtPosition(position);
                vaiParaInspecao.putExtra("nome", carro);
                startActivity(vaiParaInspecao);
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Carro carro = (Carro) listaCarros.getItemAtPosition(info.position);
                CarroDAO dao = new CarroDAO(ListaCarrosActivity.this);

                dao.deleta(carro);
                dao.close();
                carregaListaCarros();

                Toast.makeText(ListaCarrosActivity.this,  carro.getNome()+" deletado!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void carregaListaCarros() {
        CarroDAO dao = new CarroDAO(this);
        List<Carro> carros = dao.buscaCarros();
        dao.close();
        listaCarros = findViewById(R.id.lista_carros);
        ArrayAdapter<Carro> adapter = new ArrayAdapter<Carro>(this, android.R.layout.simple_list_item_1, carros);
        listaCarros.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaCarros();
    }

    private void vaiParaCadCarros() {
        novoCarro = findViewById(R.id.carro_novo);
        novoCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiCadCarro = new Intent(ListaCarrosActivity.this, CadCarroActivity.class);
                startActivity(vaiCadCarro);
            }
        });
    }
}
