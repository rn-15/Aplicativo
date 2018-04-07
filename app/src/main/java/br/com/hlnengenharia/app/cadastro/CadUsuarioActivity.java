package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.UsuarioDAO;
import br.com.hlnengenharia.app.helper.UsuarioHelper;
import br.com.hlnengenharia.app.model.Usuario;

public class CadUsuarioActivity extends AppCompatActivity {

    public static final String NOME_APPBAR = "Cadastro de Usuários";
    private UsuarioHelper helper;
    private EditText nome, profissao, email, telefone, senha, confirmaSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        setTitle(NOME_APPBAR);
        helper = new UsuarioHelper(this);
        pegaDadosParaEditar();
    }

    private void pegaDadosParaEditar() {
        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        if (usuario!=null){
            helper.preencherFormulario(usuario);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ok:
                Usuario usuario = helper.pegaUsuario();
                UsuarioDAO dao = new UsuarioDAO(CadUsuarioActivity.this);
                nome = findViewById(R.id.cadUser_nome);
                profissao = findViewById(R.id.cadUser_profissao);
                email = findViewById(R.id.cadUser_email);
                telefone = findViewById(R.id.cadUser_telefone);
                senha = findViewById(R.id.cadUser_senha);
                confirmaSenha = findViewById(R.id.cadUser_confirmasSenha);
                if (nome.getText().toString().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Preencher nome do usuário", Toast.LENGTH_SHORT).show();
                    nome.requestFocus();
                } else if (profissao.getText().toString().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Preencher profissão do usuário", Toast.LENGTH_SHORT).show();
                    profissao.requestFocus();
                } else if (email.getText().toString().trim().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Preencher email do usuário", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                } else if (telefone.getText().toString().trim().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Preencher telefone do usuário", Toast.LENGTH_SHORT).show();
                    telefone.requestFocus();
                } else if (senha.getText().toString().trim().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Preencher senha do usuário", Toast.LENGTH_SHORT).show();
                    senha.requestFocus();
                } else if (confirmaSenha.toString().trim().equals("")) {
                    Toast.makeText(CadUsuarioActivity.this, "Confirme a senha do usuário", Toast.LENGTH_SHORT).show();
                    confirmaSenha.requestFocus();
                } else {
                    if (usuario.getId() != null) {
                        dao.editar(usuario);
                        Toast.makeText(CadUsuarioActivity.this, "Usuário " + usuario.getNome() + " alterado!", Toast.LENGTH_SHORT).show();
                    } else if (usuario.getSenha().equals(usuario.getConfirmaSenha())) {
                        dao.insere(usuario);
                        Toast.makeText(CadUsuarioActivity.this, "Usuário " + usuario.getNome() + " Salvo", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(CadUsuarioActivity.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                    finish();
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
