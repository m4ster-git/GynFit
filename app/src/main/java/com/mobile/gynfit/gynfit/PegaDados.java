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
    public String debug = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pega_dados_ui);
        tv_Debug = (TextView)findViewById(R.id.tv_debug2);
        armazenamentoLocal = new ArmazenamentoLocal(this);
        //
        Perfil plocal = armazenamentoLocal.pegaDadosperfil();
        Usuario ulocal = armazenamentoLocal.pegaDadosusuario();

        //
        debug = debug + "Usuario\n";
        debug = debug + "USUARIO->" + ulocal.usuario + "\n";
        debug = debug + "SENHA->" + ulocal.senha + "\n";
        debug = debug + "TIPO->" + ulocal.tipo + "\n";
        debug = debug + "Perfil\n";
        debug = debug + "USUARIO->" + plocal.usuario + "\n";
        debug = debug + "NOME->" + plocal.nome + "\n";
        debug = debug + "ENDERECO->" + plocal.endereco + "\n";
        debug = debug + "TELEFONE->" + plocal.telefone + "\n";
        debug = debug + "IDADE->" + plocal.idade + "\n";
        debug = debug + "SEXO->" + plocal.sexo + "\n";
        debug = debug + "EMAIL->" + plocal.email + "\n";



        //

        tv_Debug.setText("Debug:\n"+debug);






    }


;
}
