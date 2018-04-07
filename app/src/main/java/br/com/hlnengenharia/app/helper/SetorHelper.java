package br.com.hlnengenharia.app.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadSetorActivity;
import br.com.hlnengenharia.app.model.Setor;

/**
 * Created by rn-15 on 23/03/2018.
 */

public class SetorHelper {

    private final TextView campoIdUnidade;
    private EditText campoNome;
    private EditText campoAtividade;
    private Setor s;

    public SetorHelper (CadSetorActivity activity) {
        campoIdUnidade = activity.findViewById(R.id.cadSetor_idunidade);
        campoNome = activity.findViewById(R.id.cadSetor_nome);
        campoAtividade = activity.findViewById(R.id.cadSetor_atividade);
        s = new Setor();
    }

    public Setor pegaSetor() {
        s.setIdUnidade(Long.valueOf(campoIdUnidade.getText().toString()));
        s.setNome(campoNome.getText().toString());
        s.setAtividade(campoAtividade.getText().toString());
        return s;
    }
}
