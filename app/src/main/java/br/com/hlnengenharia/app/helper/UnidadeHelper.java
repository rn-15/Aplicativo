package br.com.hlnengenharia.app.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadUnidadeActivity;
import br.com.hlnengenharia.app.model.Empresa;
import br.com.hlnengenharia.app.model.Unidade;

/**
 * Created by rn-15 on 15/03/2018.
 */

public class UnidadeHelper {

    private final EditText campoNome;
    private final EditText campoCidade;
    private final TextView campoIdEmpresa;
    private Unidade u;
    private Empresa e;

    public UnidadeHelper(CadUnidadeActivity activity) {
        campoIdEmpresa = activity.findViewById(R.id.cadUnidade_idempresa);
        campoNome = activity.findViewById(R.id.cadUnidade_nome);
        campoCidade = activity.findViewById(R.id.cadUnidade_cidade);

        u = new Unidade();
    }

    public Unidade pegaUnidade() {
        u.setIdEmpresa(Long.valueOf(campoIdEmpresa.getText().toString()));
        u.setNome(campoNome.getText().toString());
        u.setCidade(campoCidade.getText().toString());

        return u;
    }

    public void preencherFormulario(Unidade unidade) {


        campoNome.setText(unidade.getNome());
        campoCidade.setText(unidade.getCidade());

        this.u = unidade;
    }
}
