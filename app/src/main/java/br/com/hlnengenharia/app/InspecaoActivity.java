package br.com.hlnengenharia.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.hlnengenharia.app.dao.CarroDAO;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Pergunta;

public class InspecaoActivity extends AppCompatActivity {

    private TextView nomeInsp, campoPergunta;
    private int indicePerguntaAtual = 0;
    private Button proximaPergunta;
    private Carro perguntaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspecao);

        setTitle("");


        if (nomeInsp!=null) {
            carregaNomeInspecao();
        }else{
            nomeInsp = findViewById(R.id.nomeInsp);
            nomeInsp.setText("Check List Carro");
        }

        botaoProximo();
    }

    private void botaoProximo() {
        proximaPergunta = findViewById(R.id.proximaPergunta);
        proximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               responde();
            }
        });
    }

    public void responde() {
           indicePerguntaAtual++;
           atualizaformulario();

    }

    private void atualizaformulario() {
        CarroDAO dao = new CarroDAO(InspecaoActivity.this);
        List<Carro> carros = dao.buscaCarro();

        perguntaAtual = carros.get(indicePerguntaAtual);

        if(indicePerguntaAtual<20){
            campoPergunta = findViewById(R.id.pergunta);
                campoPergunta.setText(perguntaAtual.getPergunta());
        }else{
            Toast.makeText(InspecaoActivity.this, "Não há perguntas!", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregaNomeInspecao() {
        Intent intent = getIntent();
        Inspecao inspecao = (Inspecao) intent.getSerializableExtra("nome");
        nomeInsp = findViewById(R.id.nomeInsp);
        nomeInsp.setText(inspecao.getNome());
    }


}
