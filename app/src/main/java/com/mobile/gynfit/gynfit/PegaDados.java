package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by m4ster on 23/10/15.
 */
public class PegaDados extends Activity {
    ArmazenamentoLocal armazenamentoLocal;
    TextView tv_Debug;
    String debug = "-";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pega_dados_ui);
        tv_Debug = (TextView)findViewById(R.id.tv_debug);
       // armazenamentoLocal = new ArmazenamentoLocal(this);
        Usuario usuariol = new Usuario("leandro","muranga");

        tv_Debug.setText(  Autentica(usuariol));

        Perfil perfill = new Perfil("leandro");

        tv_Debug.setText(Autentica2(perfill));






    }

    //
    private String Autentica(Usuario usuario){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.BuscaUsuarioBackground(usuario, new RetornoUsuario() {
            @Override
            public void Terminado(Usuario retornoUsuario) {
                if (retornoUsuario == null) {
                    Toast.makeText(PegaDados.this, "Fail ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(PegaDados.this, "ok -> " + retornoUsuario.tipo, Toast.LENGTH_LONG).show();
                    debug = debug + "USUARIO->" + retornoUsuario.tipo + "\n";
                    debug = debug + "SENHA->" + retornoUsuario.senha + "\n";
                    debug = debug + "TIPO->" + retornoUsuario.tipo + "\n";


                }
            }


        });
        return debug;
    }
    //
    private String Autentica2(Perfil perfil){
        RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.BuscaPerfilBackground(perfil, new RetornoPerfil() {
            @Override
            public void Terminado(Perfil retornoPerfil) {
                if (retornoPerfil == null) {
                    Toast.makeText(PegaDados.this, "Fail ", Toast.LENGTH_LONG).show();
                } else {
                    debug = debug + "USUARIO->" + retornoPerfil.usuario + "\n";
                    debug = debug + "NOME->" + retornoPerfil.nome + "\n";
                    debug = debug + "ENDERECO->" + retornoPerfil.endereco + "\n";
                    debug = debug + "TELEFONE->" + retornoPerfil.telefone + "\n";
                    debug = debug + "IDADE->" + retornoPerfil.idade + "\n";
                    debug = debug + "SEXO->" + retornoPerfil.sexo + "\n";
                    debug = debug + "EMAIL->" + retornoPerfil.email + "\n";

                }
            }


        });
        return debug;
    }

;
}
