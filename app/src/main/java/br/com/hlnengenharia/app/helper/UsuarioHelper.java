package br.com.hlnengenharia.app.helper;

import android.widget.TextView;

import br.com.hlnengenharia.app.cadastro.CadUsuarioActivity;
import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.model.Usuario;

/**
 * Created by rn-15 on 08/03/2018.
 */

public class UsuarioHelper {

    private final TextView campoNome;
    private final TextView campoProfissao;
    private final TextView campoEmail;
    private final TextView campoTelefone;
    private final TextView campoSenha;
    private final TextView campoConfirmaSenha;
    private Usuario u;

    public UsuarioHelper(CadUsuarioActivity activity) {
        campoNome = activity.findViewById(R.id.cadUser_nome) ;
        campoProfissao = activity.findViewById(R.id.cadUser_profissao);
        campoEmail = activity.findViewById(R.id.cadUser_email) ;
        campoTelefone = activity.findViewById(R.id.cadUser_telefone) ;
        campoSenha = activity.findViewById(R.id.cadUser_senha) ;
        campoConfirmaSenha = activity.findViewById(R.id.cadUser_confirmasSenha) ;
        u = new Usuario();
    }


    public Usuario pegaUsuario() {
            u.setNome(campoNome.getText().toString());
            u.setProfissao(campoProfissao.getText().toString());
            u.setEmail(campoEmail.getText().toString());
            u.setTelefone(campoTelefone.getText().toString());
            u.setSenha(campoSenha.getText().toString());
            u.setConfirmaSenha(campoConfirmaSenha.getText().toString());
        return  u;
    }

    public void preencherFormulario(Usuario usuario) {
        campoNome.setText(usuario.getNome());
        campoProfissao.setText(usuario.getProfissao());
        campoEmail.setText(usuario.getEmail());
        campoTelefone.setText(usuario.getTelefone());
        campoSenha.setText(usuario.getSenha());
        campoConfirmaSenha.setText(usuario.getConfirmaSenha());
        this.u = usuario;
    }
}
