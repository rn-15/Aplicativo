package br.com.hlnengenharia.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.hlnengenharia.app.cadastro.CadUsuarioActivity;
import br.com.hlnengenharia.app.dao.UsuarioDAO;
import br.com.hlnengenharia.app.listas.ListaEmpresasActivity;
import br.com.hlnengenharia.app.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "";
    Button botao_login;
    TextView cadastro_login;
    ImageView hln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    setTitle(TITULO_APPBAR);

    hln = findViewById(R.id.login_imagem);
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

    cadastro_login = findViewById(R.id.login_cad);
    cadastro_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent vaiParaCadUsuario = new Intent(LoginActivity.this,CadUsuarioActivity.class);
            startActivity(vaiParaCadUsuario);
        }
    });

    botao_login = findViewById(R.id.login_botao);
    botao_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);

            String nome = ((EditText) findViewById(R.id.login_usuario)).getText().toString();
            String senha = ((EditText) findViewById(R.id.login_senha)).getText().toString();


            Usuario usuario = dao.validarLogin(nome, senha);
            if (usuario != null) {
                Intent vaiParaListaEmpresas = new Intent(LoginActivity.this, TelaDepoisDoLoginActivity.class);
                vaiParaListaEmpresas.putExtra("nome", usuario);
                startActivity(vaiParaListaEmpresas);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "Confira seu login e senha!", Toast.LENGTH_SHORT).show();
            }

        }
    });
    }
}
