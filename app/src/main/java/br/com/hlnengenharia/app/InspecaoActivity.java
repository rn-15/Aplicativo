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
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.Inspecao;

public class InspecaoActivity extends AppCompatActivity {

    private TextView nomeInsp, campoPergunta;
    private int indicePerguntaAtual = 0;
    private Button proximaPergunta;
    private Carro perguntaAtual;
    private RadioButton c,nc,na;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspecao);

        setTitle("");
        carregaNomeDoCheckList();

        botaoProximo();


    }

    private void carregaNomeDoCheckList() {
        if (nomeInsp!=null) {
            carregaNomeInspecao();
        }else{
            nomeInsp = findViewById(R.id.nomeInsp);
            nomeInsp.setText("Check List Carro");
        }
    }

    private void botaoProximo() {
        proximaPergunta = findViewById(R.id.proximaPergunta);
        proximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carro carro = new Carro();
                CarroDAO dao = new CarroDAO(InspecaoActivity.this);

                reconheceBotões(carro);
                if(!c.isChecked() && !nc.isChecked() && !na.isChecked()){
                    Toast.makeText(InspecaoActivity.this, "Preencha o formulário corretamente!", Toast.LENGTH_SHORT).show();
                }else{
                    dao.insereResposta(carro);
                }
                responde();
            }
        });
    }

    private void reconheceBotões(Carro carro) {
        c = findViewById(R.id.conforme);
        nc = findViewById(R.id.nConforme);
        na = findViewById(R.id.nSeAplica);
        rg = findViewById(R.id.radioGroup);
        campoPergunta = findViewById(R.id.pergunta);

            carro.setPergunta(campoPergunta.getText().toString());
            carro.setResposta(nc.getText().toString());
            carro.setResposta(na.getText().toString());
            carro.setResposta(c.getText().toString());

    }

    public void responde() {
        indicePerguntaAtual++;
        atualizaFormularioComPerguntaAtual();
    }

    private void atualizaFormularioComPerguntaAtual() {
        CarroDAO dao = new CarroDAO(InspecaoActivity.this);
        List<Carro> carros = dao.buscaPergunta();
        if (indicePerguntaAtual < carros.size()) {
            perguntaAtual = carros.get(indicePerguntaAtual);

            campoPergunta = findViewById(R.id.pergunta);
            campoPergunta.setText(perguntaAtual.getPergunta());
        } else {
            Toast.makeText(InspecaoActivity.this, "Não há perguntas!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void carregaNomeInspecao() {
        Intent intent = getIntent();
        Inspecao inspecao = (Inspecao) intent.getSerializableExtra("nome");
        nomeInsp = findViewById(R.id.nomeInsp);
        nomeInsp.setText(inspecao.getNome());
    }

}
