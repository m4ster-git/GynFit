package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by m4ster on 22/10/15.
 */
public class Cadastro_Perfil extends Activity implements View.OnClickListener {
    Button bt_cadastrar;
    EditText et_nome;
    EditText et_idade;
    EditText et_endereco;
    EditText et_email;
    EditText et_telefone;
    EditText et_usuarioget;
    RadioGroup rbg_sexo;
    RadioButton rb_sexo;
    TextView tv_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastra_aluno_ui);
        String usuario = getIntent().getStringExtra("usuario");


        et_nome = (EditText)findViewById(R.id.et_nome);
        et_idade = (EditText)findViewById(R.id.et_idade);
        et_endereco = (EditText)findViewById(R.id.et_endereco);
        et_email = (EditText)findViewById(R.id.et_email);
        et_telefone = (EditText)findViewById(R.id.et_telefone);
        tv_status = (TextView)findViewById(R.id.tv_status_aluno);
        bt_cadastrar = (Button)findViewById(R.id.bt_cadastro_aluno);
        rbg_sexo = (RadioGroup)findViewById(R.id.rbg_sexo);
        et_usuarioget = (EditText)findViewById(R.id.et_usuarioget);
        bt_cadastrar.setOnClickListener(this);
        et_usuarioget.setText(usuario);

    }



    public String verificaCadastro(String nome,String idade,String endereco, String email, String telefone) {
        String status = "Status:\n";
        if (nome.equals("")) {
            status = status + "Campo nome em branco\n";
        }if (email.equals("")) {
            status = status + "Campo de email em branco\n";
        }if (idade.equals("")) {
            status = status + "Campo de idade em branco\n";
        }if (endereco.equals("")) {
            status = status + "Campo de endereco em branco\n";
        }if (telefone.equals("")) {
            status = status + "Campo de telefone em branco\n";
        }else{
            status="";
        }
        return status;
    }

    public void RegistraPerfil(Perfil perfil){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.GravaPerfilBackground(perfil, new RetornoPerfil() {
            @Override
            public void Terminado(Perfil retornoPerfil) {
                startActivity(new Intent(Cadastro_Perfil.this, PrincipalPersonal.class));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cadastro_aluno:
                //Pega valor dos campos
                int selectedId = rbg_sexo.getCheckedRadioButtonId();
                rb_sexo = (RadioButton) findViewById(selectedId);
                String nome = et_nome.getText().toString();
                String idade = et_idade.getText().toString();
                String endereco = et_endereco.getText().toString();
                String email = et_email.getText().toString();
                String telefone = et_telefone.getText().toString();
                String sexo = rb_sexo.getText().toString();
                String usuario = et_usuarioget.getText().toString();
                tv_status.setText(verificaCadastro(nome,idade,endereco,email,telefone));

                if (verificaCadastro(nome,idade,endereco,email,telefone).equals("")) {
                    Perfil perfilc = new Perfil(nome,idade,endereco,email,telefone,sexo,usuario);
                    RegistraPerfil(perfilc);
                }
                break;
        }
    }
}
