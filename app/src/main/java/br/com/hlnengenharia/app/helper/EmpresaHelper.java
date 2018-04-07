package br.com.hlnengenharia.app.helper;

import android.widget.TextView;

import br.com.hlnengenharia.app.cadastro.CadEmpresaActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.model.Empresa;

/**
 * Created by rn-15 on 14/03/2018.
 */

public class EmpresaHelper {

    private final TextView campoNome;
    private final TextView campoSegmento;
    private Empresa e;


    public EmpresaHelper(CadEmpresaActivity activity) {
        campoNome = activity.findViewById(R.id.cadEmpresa_nome);
        campoSegmento = activity.findViewById(R.id.cadEmpresa_segmento);
        e = new Empresa();
    }

    public Empresa pegaEmpresa (){
        e.setNome(campoNome.getText().toString());
        e.setSegmento(campoSegmento.getText().toString());
        return e;
    }

    public void preencherFormulario(Empresa empresa) {
        campoNome.setText(empresa.getNome());
        campoSegmento.setText(empresa.getSegmento());
        this.e = empresa;
    }
}

