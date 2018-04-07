package br.com.hlnengenharia.app.helper;

import android.widget.EditText;
import android.widget.TextView;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.cadastro.CadInspecaoActivity;
import br.com.hlnengenharia.app.model.Inspecao;

/**
 * Created by rn-15 on 20/03/2018.
 */

public class InspecaoHelper {

    private final EditText campoNome;
    private final TextView campoCodigo;
    private Inspecao i;

    public InspecaoHelper(CadInspecaoActivity activity) {
        campoCodigo = activity.findViewById(R.id.codigo_setor);
        campoNome = activity.findViewById(R.id.cadInspecao);
      i = new Inspecao();
    }

    public Inspecao pegaInspecao(){
        i.setIdSetor(Long.valueOf(campoCodigo.getText().toString()));
        i.setNome(campoNome.getText().toString());
        return i;
    }
}
