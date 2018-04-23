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

    private TextView nomeInsp, campoPergunta,campoId;
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


        atualizaFormularioComPerguntaAtual();

        proximaPergunta = findViewById(R.id.proximaPergunta);
        proximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responde();
            }
        });
    }

    private void atualizaFormularioComPerguntaAtual() {
        RespostaCarro resposta = new RespostaCarro();
        pCarroDAO dao = new pCarroDAO(InspecaoActivity.this);
        List<PerguntaCarro> perguntas = dao.buscaPerguntaCarro();

        if (indicePerguntaAtual < perguntas.size()){
            perguntaAtual = perguntas.get(indicePerguntaAtual);

        campoPergunta = findViewById(R.id.pergunta);
        campoPergunta.setText(perguntaAtual.getPergunta());

        dao.inserir(resposta);
        }else{
            finish();
        }
    }


    public void responde() {
        indicePerguntaAtual++;
        atualizaFormularioComPerguntaAtual();
    }

            private void criarComponentes() {
                nomeInsp = findViewById(R.id.nomeInsp);
                campoPergunta = findViewById(R.id.pergunta);
                proximaPergunta = findViewById(R.id.proximaPergunta);
                c = findViewById(R.id.conforme);
                nc = findViewById(R.id.nConforme);
                na = findViewById(R.id.nSeAplica);
                rg = findViewById(R.id.radioGroup);
            }


            private void carregaNomeDoCheckList() {
                if (nomeInsp != null) {
                    carregaNomeInspecao();
                } else {
                    Intent intent = getIntent();
                    Carro carro = (Carro) intent.getSerializableExtra("carro");
                    nomeInsp = findViewById(R.id.nomeInsp);
                    nomeInsp.setText(carro.getNome());
                }
            }


            private void carregaNomeInspecao() {
                Intent intent = getIntent();
                Inspecao inspecao = (Inspecao) intent.getSerializableExtra("nome");
                nomeInsp = findViewById(R.id.nomeInsp);
                nomeInsp.setText(inspecao.getNome());
            }
        }