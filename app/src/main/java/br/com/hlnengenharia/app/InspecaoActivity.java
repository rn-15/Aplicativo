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

        botaoProximo();
        responde();

        atualizaFormularioComPerguntaAtual();


    }

    private void atualizaFormularioComPerguntaAtual() {
        pCarroDAO dao = new pCarroDAO(InspecaoActivity.this);
        List<PerguntaCarro> perguntas = dao.buscaPerguntaCarro();
        if(indicePerguntaAtual<perguntas.size())
            perguntaAtual = perguntas.get(indicePerguntaAtual);
        campoPergunta = findViewById(R.id.pergunta);
        campoPergunta.setText(perguntaAtual.getPergunta());
    }

    public void responde(){
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
        if (nomeInsp!=null) {
            carregaNomeInspecao();
        }//else{
         //   Intent intent = getIntent();
        //    Carro carro = (Carro) intent.getSerializableExtra("carro_id");
        //    PerguntaCarro perguntaCarro = (PerguntaCarro) intent.getSerializableExtra("cpergunta_id");
         //   nomeInsp = findViewById(R.id.nomeInsp);
         //   nomeInsp.setText(carro.getId().toString());
         //   campoId = findViewById(R.id.idCarro);
          //  campoId.setText(perguntaCarro.getId().toString());

     //   }
    }

    private void botaoProximo() {
        proximaPergunta = findViewById(R.id.proximaPergunta);
        proximaPergunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           RespostaCarro resposta = new RespostaCarro();
           pCarroDAO dao = new pCarroDAO(InspecaoActivity.this);
              if (!c.isChecked()&&!nc.isChecked()&&!na.isChecked()){
                  Toast.makeText(InspecaoActivity.this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
              }
                if(c.isChecked()){
                    resposta.setResposta("Conforme");
                }else if(nc.isChecked()){
                    resposta.setResposta("Não conforme");
                }else{
                    resposta.setResposta("N/A");
                }

            dao.inserir(resposta);
            }
        });
    }

    private void carregaNomeInspecao() {
        Intent intent = getIntent();
        Inspecao inspecao = (Inspecao) intent.getSerializableExtra("nome");
        nomeInsp = findViewById(R.id.nomeInsp);
        nomeInsp.setText(inspecao.getNome());
    }

}
