package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.SetorDAO;
import br.com.hlnengenharia.app.helper.SetorHelper;
import br.com.hlnengenharia.app.model.Setor;
import br.com.hlnengenharia.app.model.Unidade;

public class CadSetorActivity extends AppCompatActivity {

    public static final String APP_TITULOBAR = "Cadastro de Setor";
    private Button salvarSetor;
    private TextView idUnd, nomeUnd, cidadeUnd;
    private SetorHelper helper;
    private Setor setor;
    private Unidade und;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_setor);

        setTitle(APP_TITULOBAR);

        helper = new SetorHelper(this);

        pegaDadosUnidade();
        salvaSetor();

    }

    private void salvaSetor() {
        salvarSetor = findViewById(R.id.salvar_setor);
        salvarSetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Setor setor = helper.pegaSetor();
                SetorDAO dao = new SetorDAO(CadSetorActivity.this);

                dao.insere(setor);
                dao.close();

                finish();
            }
        });
    }

    private void pegaDadosUnidade() {
        Intent intent = getIntent();
        und = (Unidade) intent.getSerializableExtra("unidade");
        idUnd = findViewById(R.id.cadSetor_idunidade);
        nomeUnd = findViewById(R.id.cadSetor_unidade);
        cidadeUnd = findViewById(R.id.cadSetor_cidade);

        idUnd.setText(und.getId().toString());
        nomeUnd.setText(und.getNome());
        cidadeUnd.setText(und.getCidade());
    }
}
