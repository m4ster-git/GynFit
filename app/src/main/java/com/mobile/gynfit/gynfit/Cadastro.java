package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.FileOutputStream;

//Classe para cadastro de usuário
public class Cadastro extends Activity implements View.OnClickListener{
    TextView tv_status;
    EditText et_usuario;
    EditText et_senha1;
    EditText et_senha2;
    EditText et_email;
    RadioGroup rbg_tipo;
    RadioButton tipoSelecionado;
    Button bt_cadastrar;
    RoundedImageView rm_frofile;
    static int TAKE_PICTURE = 1;
    Bitmap bitMap;
    ArmazenamentoLocal armazenamentoLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_ui);
        et_usuario = (EditText)findViewById(R.id.etUsuario);
        et_senha1 = (EditText)findViewById(R.id.etPass1);
        et_senha2 = (EditText)findViewById(R.id.etPass2);
        tv_status = (TextView)findViewById(R.id.tv_status);
        rbg_tipo = (RadioGroup)findViewById(R.id.rbg_tipo);
        bt_cadastrar = (Button)findViewById(R.id.btCad);
        rm_frofile = (RoundedImageView)findViewById(R.id.rmv_profile);
        rm_frofile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, TAKE_PICTURE);

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{

                        break;
                    }
                }
                return true;

            }
        });
        bt_cadastrar.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get("Data");
            rm_frofile.setImageBitmap(bitMap);
        }
    }



    @Override
    public void registerForContextMenu(View view) {
        super.registerForContextMenu(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btCad:
                //Pega valor dos campos
                int selectedId = rbg_tipo.getCheckedRadioButtonId();
                tipoSelecionado = (RadioButton)findViewById(selectedId);

                String usuario = et_usuario.getText().toString();
                String senha = et_senha1.getText().toString();
                String senha2 = et_senha2.getText().toString();
                String tipo = tipoSelecionado.getText().toString();
                tv_status.setText(verificaCadastro(usuario, senha, senha2));

                if(verificaCadastro(usuario, senha, senha2).equals("")){
                    Usuario usuarioc = new Usuario(usuario,senha,tipo);
                    RegistraUsuario(usuarioc);
                }
                break;
        }

    }

    public void RegistraUsuario(final Usuario usuario){
    RequisicoesServidor requisicoesServidor = new RequisicoesServidor(this);
        requisicoesServidor.GravaUsusuarioBackground(usuario, new RetornoUsuario() {
            @Override
            public void Terminado(Usuario retornoUsuario) {
                if (usuario.tipo.equals("Personal")) {
                    startActivity(new Intent(Cadastro.this, Login.class));
                }else if(usuario.tipo.equals("Aluno")){
                    Intent intent = new Intent(getApplicationContext(), Cadastro_Perfil.class);
                    intent.putExtra("usuario", et_usuario.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public String verificaCadastro(String usuario,String senha,String senha2) {
        String status = "Status:\n";
        if (usuario.equals("")) {
            status = status + "Campo usuario em branco\n";
        }if (senha.equals("") || senha2.equals("")) {
            status = status + "Campo de senha em branco\n";
        }else if (!senha.equals(senha2)) {
            return status = status + "As senhas não coencidem.\n";
        }else{
            status="";
        }
        return status;
    }

   public Boolean SalvarFotoNaMemoriaInterna(Context context,Bitmap bitmap){
       try{
           FileOutputStream fos = context.openFileOutput("profile.png",Context.MODE_PRIVATE);
           bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
           fos.close();
           return true;
       }catch (Exception e){
           return false;
       }
   }

}
