package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.EmpresaDAO;
import br.com.hlnengenharia.app.helper.EmpresaHelper;
import br.com.hlnengenharia.app.model.Empresa;

public class CadEmpresaActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de Empresas";
    private EmpresaHelper helper;
    private Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_empresa);

        setTitle(TITULO_APPBAR);
        helper = new EmpresaHelper(this);

        botaoSalvar();
        pegaEmpresaParaEditar();

    }

    private void pegaEmpresaParaEditar() {
        Intent intent = getIntent();
        Empresa empresa = (Empresa) intent.getSerializableExtra("empresa");
        if(empresa!=null){
            helper.preencherFormulario(empresa);
        }
    }

    private void botaoSalvar() {
        salvar = findViewById(R.id.salvar_empresa);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empresa empresa = helper.pegaEmpresa();
                EmpresaDAO dao = new EmpresaDAO(CadEmpresaActivity.this);

                if (empresa.getId()!=null){
                    dao.editar(empresa);
                    Toast.makeText(CadEmpresaActivity.this, "Empresa "+empresa.getNome()+" alterada com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    dao.insere(empresa);
                    Toast.makeText(CadEmpresaActivity.this, "Empresa "+empresa.getNome()+" cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                }
                dao.close();
                finish();

            }
        });
    }
}
