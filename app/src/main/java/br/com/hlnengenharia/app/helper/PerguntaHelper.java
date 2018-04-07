package br.com.hlnengenharia.app.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadPerguntaActivity;
import br.com.hlnengenharia.app.model.Pergunta;

public class PerguntaHelper {


    private final EditText campoPergunta;
    private final Pergunta pergunta;
    private final TextView campoCodigo;


    public PerguntaHelper(CadPerguntaActivity activity) {
        campoCodigo = activity.findViewById(R.id.codigo_insp);
        campoPergunta = activity.findViewById(R.id.pergunta);
        pergunta = new Pergunta();
    }

    public Pergunta pegaPergunta() {
        pergunta.setIdInspecao(Long.valueOf(campoCodigo.getText().toString()));
        pergunta.setPergunta(campoPergunta.getText().toString());
        return pergunta;
    }
}
