package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.InspecaoDAO;
import br.com.hlnengenharia.app.helper.InspecaoHelper;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Setor;

public class CadInspecaoActivity extends AppCompatActivity {

    private Button salvarInspecao;
    private InspecaoHelper helper;
    private TextView codigo;
    private Setor setor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_inspecao);

        helper = new InspecaoHelper(this);

        setTitle("");

        Intent intent = getIntent();
        setor = (Setor) intent.getSerializableExtra("id");
        codigo = findViewById(R.id.codigo_setor);
        codigo.setText(setor.getId().toString());

        salvarInspecao = findViewById(R.id.salvar_inspecao);
        salvarInspecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InspecaoDAO dao = new InspecaoDAO(CadInspecaoActivity.this);
                Inspecao inspecao = helper.pegaInspecao();

                dao.insere(inspecao);
                dao.close();

                finish();
            }
        });

    }
}
