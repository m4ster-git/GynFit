package com.mobile.gynfit.gynfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Introducao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introducao_ui);
        Thread splashThread = new Thread() {

            @Override
            public void run() {
                try {
                    int waited = 10;
                    while (waited < 3000) {
                        sleep(1);
                        waited += 1;
                    }
                }
                catch (InterruptedException e) {
                    // do nothing
                }
                finally {
                    finish();
                    final Intent cadastro = new Intent(Introducao.this, PrincipalAluno.class);
                    startActivity(cadastro);
                }
            }
        };
        splashThread.start();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
