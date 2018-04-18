package br.com.hlnengenharia.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.hlnengenharia.app.cadastro.CadPerguntaActivity;
import br.com.hlnengenharia.app.listas.ListaCarrosActivity;
import br.com.hlnengenharia.app.listas.ListaEmpresasActivity;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Usuario;

public class TelaDepoisDoLoginActivity extends AppCompatActivity {

    private TextView nome_usuario;
    private ImageView carro, empresa, hln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_depois_do_login);

        pegaUsuarioLogado();

        carro = findViewById(R.id.carro);
        carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDepoisDoLoginActivity.this, ListaCarrosActivity.class);
                startActivity(intent);
            }
        });

        empresa = findViewById(R.id.empresa);
        empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDepoisDoLoginActivity.this, ListaEmpresasActivity.class);
                startActivity(intent);
            }
        });

        hln = findViewById(R.id.hln);
        hln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://hlnengenharia.com.br/"));
                startActivity(intent);

            }
        });
        registerForContextMenu(carro);
    }

    private void pegaUsuarioLogado() {
        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("nome");
        nome_usuario = findViewById(R.id.nome_usuario);
        nome_usuario.setText(usuario.getNome());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem cadastrar = menu.add("Cadastrar pergunta");
        cadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Intent cadPergunta = new Intent(TelaDepoisDoLoginActivity.this, CadPerguntaActivity.class);
                startActivity(cadPergunta);

                return false;
            }
        });
    }
}
