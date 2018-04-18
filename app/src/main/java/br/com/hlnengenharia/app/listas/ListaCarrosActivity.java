package br.com.hlnengenharia.app.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

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

        vaiParaCadCarros();
        carregaListaCarros();
    }

    private void carregaListaCarros() {
        CarroDAO dao = new CarroDAO(ListaCarrosActivity.this);
        List<Carro> carros = dao.buscaCarros();
        dao.close();
        listaCarros = findViewById(R.id.lista_carros);
        ArrayAdapter<Carro> adapter = new ArrayAdapter<Carro>(this, android.R.layout.activity_list_item, carros);
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
