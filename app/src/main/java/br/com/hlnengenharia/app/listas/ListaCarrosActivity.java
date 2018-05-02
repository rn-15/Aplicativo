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
import br.com.hlnengenharia.app.model.PerguntaCarro;

public class ListaCarrosActivity extends AppCompatActivity {
    public static final String NOME_APPBAR = "Carros cadastrados";
    private ListView listaCarros;
    private Button novoCarro;
    private PerguntaCarro pergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);
        listaCarros = findViewById(R.id.lista_carros);
        registerForContextMenu(listaCarros);

        setTitle(NOME_APPBAR);
        vaiParaCadCarros();
        carregaListaCarros();

        listaCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Intent vaiParaInspecaoRealizada = new Intent(ListaCarrosActivity.this, ListaInspecoesActivity.class);
               Carro carro = (Carro) listaCarros.getItemAtPosition(position);
               vaiParaInspecaoRealizada.putExtra("carro", carro);
               startActivity(vaiParaInspecaoRealizada);
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
        MenuItem visualizar = menu.add("Visualizar Inspeções");
        visualizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

               // PRECISO PUXAR TODAS INSPEÇOES FEITAS PRAQUELE CARRO
               // COLOCAR DATA NAS INSPEÇOES
                //COLOCAR KM NO FORMULARIO
              //  COLOCAR PRA PUXAR QUAL USUARIO ESTA FAZENDO A INSPEÇÃO
            //    VISUALIZAR SEM ALTERAR A INSPEÇÃO
          //      COLOCAR CAMPOO DE OBSERVAÇÃO
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
