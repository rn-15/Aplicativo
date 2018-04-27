package br.com.hlnengenharia.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.hlnengenharia.app.dao.CarroDAO;
import br.com.hlnengenharia.app.dao.pCarroDAO;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.PerguntaCarro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class InspecaoActivity extends AppCompatActivity {

    private TextView nomeInsp, campoPergunta,idCarro,idPergunta;
    private int indicePerguntaAtual = 0;
    private Button proximaPergunta;
    private PerguntaCarro perguntaAtual;
    private RadioButton c,nc,na;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspecao);

        setTitle("");

        carregaNomeDoCheckList();
        criarComponentes();
        botaoProximo();
        atualizaFormularioComPerguntaAtual();


    }

    private void botaoProximo() {
        proximaPergunta = findViewById(R.id.proximaPergunta);
        proximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicePerguntaAtual++;
                responde();
            }
        });
    }

    private void atualizaFormularioComPerguntaAtual() {

        pCarroDAO dao = new pCarroDAO(InspecaoActivity.this);
        List<PerguntaCarro> perguntas = dao.buscaPerguntaCarro();

        if (indicePerguntaAtual < perguntas.size()) {

            perguntaAtual = perguntas.get(indicePerguntaAtual);

            campoPergunta.setText(perguntaAtual.getPergunta());
            idPergunta.setText(perguntaAtual.getId().toString());

        }else{
            finish();
        }

    }

    public void responde() {
        RespostaCarro resposta = new RespostaCarro();
        pCarroDAO dao = new pCarroDAO(InspecaoActivity.this);
        resposta.setIdCarro(Long.valueOf(idCarro.getText().toString()));
        resposta.setIdPergunta(Long.valueOf(idPergunta.getText().toString()));

        if (c.isChecked()) {
            resposta.setResposta(c.getText().toString());
        } else if (nc.isChecked()) {
            resposta.setResposta(nc.getText().toString());
        } else {
            resposta.setResposta(na.getText().toString());
        }
        dao.inserir(resposta);
        atualizaFormularioComPerguntaAtual();
    }


    private void criarComponentes() {
        idCarro = findViewById(R.id.idCarro);
        idPergunta = findViewById(R.id.idPergunta);
        nomeInsp = findViewById(R.id.nomeInsp);
        campoPergunta = findViewById(R.id.pergunta);
        proximaPergunta = findViewById(R.id.proximaPergunta);
        campoPergunta = findViewById(R.id.pergunta);
        c = findViewById(R.id.conforme);
        nc = findViewById(R.id.nConforme);
        na = findViewById(R.id.nSeAplica);
        rg = findViewById(R.id.radioGroup);
    }


    private void carregaNomeDoCheckList() {
        if (nomeInsp != null) {
            Intent intent = getIntent();
            Inspecao inspecao = (Inspecao) intent.getSerializableExtra("nome");
            nomeInsp = findViewById(R.id.nomeInsp);
            nomeInsp.setText(inspecao.getNome());
        } else {
            Intent intent = getIntent();
            Carro carro = (Carro) intent.getSerializableExtra("carro");
            nomeInsp = findViewById(R.id.nomeInsp);
            nomeInsp.setText(carro.getNome());
            idCarro = findViewById(R.id.idCarro);
            idCarro.setText(carro.getId().toString());
        }
    }
}