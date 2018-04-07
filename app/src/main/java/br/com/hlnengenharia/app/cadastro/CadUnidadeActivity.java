package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.UnidadeDAO;
import br.com.hlnengenharia.app.helper.EmpresaHelper;
import br.com.hlnengenharia.app.helper.UnidadeHelper;
import br.com.hlnengenharia.app.model.Empresa;
import br.com.hlnengenharia.app.model.Unidade;

public class CadUnidadeActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de unidade";
    private Button salvarUnidade;
    private UnidadeHelper helper;
    private TextView undempresa, undsegmento, undidEmpresa;
    private Empresa empresa;
    private EmpresaHelper empresaHelper;
    private Unidade unidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_unidade);
        setTitle(TITULO_APPBAR);

        helper = new UnidadeHelper(this);

        puxaEmpresaDaOutraTela();
        Intent intent = getIntent();
        Unidade unidade = (Unidade) intent.getSerializableExtra("unidade");
        if (unidade != null) {
            helper.preencherFormulario(unidade);

        } else {
            puxaEmpresaDaOutraTela();
        }

        salvaUnidade();
    }

    private void salvaUnidade() {
        salvarUnidade = findViewById(R.id.salvar_unidade);
        salvarUnidade.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Unidade unidade = helper.pegaUnidade();

            UnidadeDAO dao = new UnidadeDAO(CadUnidadeActivity.this);
            if(unidade.getId()!=null)
                dao.altera(unidade);
            else
                dao.insere(unidade);

            dao.close();
            finish();

            }
        });
    }

    private void puxaEmpresaDaOutraTela() {

        Intent intent = getIntent();
        empresa = (Empresa) intent.getSerializableExtra("empresa");
        undempresa = findViewById(R.id.cadUnidade_empresa);
        undsegmento =findViewById(R.id.cadUnidade_segmento);
        undidEmpresa = findViewById(R.id.cadUnidade_idempresa);

        undidEmpresa.setText(empresa.getId().toString());
        undempresa.setText(empresa.getNome());
        undsegmento.setText(empresa.getSegmento());

    }
}
