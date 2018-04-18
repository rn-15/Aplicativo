package br.com.hlnengenharia.app.cadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.CarroDAO;
import br.com.hlnengenharia.app.helper.CarroHelper;
import br.com.hlnengenharia.app.model.Carro;

public class CadCarroActivity extends AppCompatActivity {

    private TextView carro;
    private Button salvarCarro;
    private CarroHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_carro);

        carro = findViewById(R.id.carro);
        salvarCarro = findViewById(R.id.salvar_carro);

        helper = new CarroHelper(CadCarroActivity.this);

        salvarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarroDAO dao = new CarroDAO(CadCarroActivity.this);
                    Carro carro = helper.pegaCarro();
                    dao.insere(carro);
                    dao.close();
                finish();
            }
        });
    }
}
