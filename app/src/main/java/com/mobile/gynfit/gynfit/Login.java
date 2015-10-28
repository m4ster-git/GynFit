package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by sissi on 17/09/2015.
 */
public class Login extends Activity implements View.OnClickListener{
    EditText et_lusuario;
    EditText et_lsenha;
    Button bt_login;
    TextView tv_status;
    ArmazenamentoLocal armazenamentoLocal;
    RadioGroup rbg_tipo;
    RadioButton rb_tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        et_lusuario = (EditText)findViewById(R.id.etlUsuario);
        et_lsenha = (EditText)findViewById(R.id.etlPass);
        bt_login = (Button)findViewById(R.id.btLog);
        tv_status = (TextView)findViewById(R.id.status2);
        rbg_tipo = (RadioGroup)findViewById(R.id.rgb_tipol);
        bt_login.setOnClickListener(this);
        armazenamentoLocal = new ArmazenamentoLocal(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLog:

                String usuario = et_lusuario.getText().toString();
                String senha = et_lsenha.getText().toString();
                tv_status.setText(verificaLogin(usuario,senha));
                if(verificaLogin(usuario,senha).equals("")){
                    int selectedId = rbg_tipo.getCheckedRadioButtonId();
                    rb_tipo = (RadioButton) findViewById(selectedId);
                    if (rb_tipo.getText().equals("Aluno")){
                        Usuario usuariol = new Usuario(usuario,senha);
                        Perfil perfill = new Perfil(usuario);
                        Autentica2(perfill);
                        Autentica(usuariol);
                    }else {
                        Usuario usuariol = new Usuario(usuario,senha);
                        Autentica(usuariol);
                    }




                }
                break;
        }
    }



    public String verificaLogin(String usuario,String senha){
        String status = "Status:\n";
        if (usuario.equals("")) {
            status = status + "Campo usuario em branco\n";
        }if (senha.equals("")) {
            status = status + "Campo de senha em branco\n";
        }else {
            status = "";
        }
        return status;
    }

    private void Autentica(Usuario usuario){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.BuscaUsuarioBackground(usuario, new RetornoUsuario() {
            @Override
            public void Terminado(Usuario retornoUsuario) {
                if (retornoUsuario == null) {
                    Mensagem_erro();
                } else {
                    LoginUser(retornoUsuario);

                }
            }


        });
    }
    private void Autentica2(final Perfil perfil){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.BuscaPerfilBackground(perfil, new RetornoPerfil() {
            @Override
            public void Terminado(Perfil retornoPerfil) {
                if (retornoPerfil == null) {
                    Mensagem_erro();
                } else {
                    armazenamentoLocal.gravaDadosPerfil(retornoPerfil);

                }
            }


        });
    }

    private void Mensagem_erro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Dados do usuário incorreto");
        builder.setPositiveButton("ok", null);
        builder.show();
    }
    private void LoginUser(Usuario retornoUsuario) {
        armazenamentoLocal.gravaDadosUsuario(retornoUsuario);
        armazenamentoLocal.DefinirLogado(true);

        if(retornoUsuario.tipo.equals("Aluno")){
            startActivity(new Intent(this, PrincipalAluno.class));
        }else {
            startActivity(new Intent(this, PrincipalPersonal.class));
        }

    }


}