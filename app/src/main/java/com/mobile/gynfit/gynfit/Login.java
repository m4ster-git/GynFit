package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        et_lusuario = (EditText)findViewById(R.id.etlUsuario);
        et_lsenha = (EditText)findViewById(R.id.etlPass);
        bt_login = (Button)findViewById(R.id.btLog);
        tv_status = (TextView)findViewById(R.id.status2);
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

                    Usuario usuariol = new Usuario(usuario,senha);
                    Autentica(usuariol);


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
                if (retornoUsuario == null){
                    Mensagem_erro();
                }else {
                    LoginUser(retornoUsuario);

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

        startActivity(new Intent(this,PegaDados.class));
    }
}