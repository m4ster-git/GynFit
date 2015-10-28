package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Personal_Cadastra_Aluno extends Activity implements View.OnClickListener {
    EditText ed_buscaAluno;
    Button bt_buscaAluno;
    Button bt_cadastraAluno;
    ArmazenamentoLocal armazenamentoLocal;
    TextView tv_alunoSelecionado;
    Perfil aluno;
    CheckBox cb_selecionaAluno;
    String pusuario;

    int Click=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_cadastra_aluno);
        pusuario = getIntent().getStringExtra("usuario");

        armazenamentoLocal = new ArmazenamentoLocal(this);
        ed_buscaAluno = (EditText)findViewById(R.id.et_buscaAluno);
        tv_alunoSelecionado = (TextView)findViewById(R.id.tv_alunoSelecionado);
        bt_cadastraAluno = (Button)findViewById(R.id.bt_cadastraAluno);
        cb_selecionaAluno = (CheckBox)findViewById(R.id.cb_selecionaAluno);
        bt_buscaAluno = (Button)findViewById(R.id.bt_buscaAluno);
        cb_selecionaAluno.setOnClickListener(this);
        bt_buscaAluno.setOnClickListener(this);
        bt_cadastraAluno.setOnClickListener(this);
    }




    private void PegaPerfilBanco(final Perfil perfil){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.BuscaPerfilBackground(perfil, new RetornoPerfil() {
            @Override
            public void Terminado(Perfil retornoPerfil) {
                if (retornoPerfil == null) {

                } else {
                    armazenamentoLocal.gravaDadosPerfil(retornoPerfil);

                }
            }


        });
    }
    public void RegistraAlunoDoPersonal(AlunoDoPersonal alunoDoPersonal){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.GravaAlunoDoPersonalBackground(alunoDoPersonal, new RetornoAlunoDoPersonal() {
            @Override
            public void Terminado(AlunoDoPersonal retornoAlunoDoPersonal) {
                startActivity(new Intent(Personal_Cadastra_Aluno.this, Personal_Cadastra_Aluno.class));

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_buscaAluno:

                Perfil pbusca = new Perfil(ed_buscaAluno.getText().toString());
                PegaPerfilBanco(pbusca);
                Perfil lperfil = armazenamentoLocal.pegaDadosperfil();
                aluno = lperfil;
                armazenamentoLocal.limpaDadosPerfil();
                if (Click == 2) {
                    cb_selecionaAluno.setVisibility(View.VISIBLE);
                    cb_selecionaAluno.setText(aluno.nome + " - " + aluno.idade + " anos");
                    if (aluno.nome.equals("")) {
                        tv_alunoSelecionado.setText("Aluno não encontrado!");
                        cb_selecionaAluno.setVisibility(View.INVISIBLE);
                        Click = 1;
                    }
                } else {
                    Click++;
                    tv_alunoSelecionado.setText("Clique novamente, porfavor.");
                    cb_selecionaAluno.setVisibility(View.INVISIBLE);

                }

                break;
            case R.id.bt_cadastraAluno:
                AlunoDoPersonal alp = new AlunoDoPersonal(pusuario.toString(),aluno.usuario.toString());
                RegistraAlunoDoPersonal(alp);
                break;
            case R.id.cb_selecionaAluno:
                if (cb_selecionaAluno.isChecked()) {
                    bt_cadastraAluno.setVisibility(View.VISIBLE);
                    tv_alunoSelecionado.setText("Aluno selecionado");
                } else {
                    bt_cadastraAluno.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
