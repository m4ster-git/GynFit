package com.mobile.gynfit.gynfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sissi on 17/09/2015.
 */

public class Menu extends Activity {
    Button cadastro;
    Button login;




    @Override
    public void onBackPressed() {
        super.onBackPressed();


            Toast.makeText(this, "Saindo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_ui);

        cadastro = (Button)findViewById(R.id.btCadastro);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent cadastro = new Intent(Menu.this, Cadastro.class);
                startActivity(cadastro);
            }
        });

        login = (Button)findViewById(R.id.btLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent login = new Intent(Menu.this, Login.class);
                startActivity(login);

            }
        });



    }
}
