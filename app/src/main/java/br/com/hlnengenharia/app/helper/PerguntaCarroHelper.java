package br.com.hlnengenharia.app.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadPerguntaActivity;
import br.com.hlnengenharia.app.model.Pergunta;
import br.com.hlnengenharia.app.model.PerguntaCarro;

public class PerguntaCarroHelper {


    private final EditText campoPergunta;
    private final PerguntaCarro pergunta;


    public PerguntaCarroHelper(CadPerguntaActivity activity) {
        campoPergunta = activity.findViewById(R.id.pergunta);
        pergunta = new PerguntaCarro();
    }

    public PerguntaCarro pegaPergunta() {
        pergunta.setPergunta(campoPergunta.getText().toString());
        return pergunta;
    }
}
