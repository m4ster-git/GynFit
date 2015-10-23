package com.mobile.gynfit.gynfit;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by sissi on 19/09/2015.
 */
public class PrincipalPersonal extends ActionBarActivity{
    //Toolbar
    private Toolbar  mToolbar;


    ArmazenamentoLocal armazenamentoLocal;
    public String usuario_var;
    public String email_var;


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                LocationFound();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void LocationFound() {
        startActivity(new Intent(PrincipalPersonal.this,Menu.class));
    }


    //Drawer
    private Drawer DrawerDireito;
    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(PrincipalPersonal.this,"onCheckedChanged"+(b ? "true":"false"), Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        if (Autenticacao() == true) {
            motrarUsuario(usuario_var);

        } else {
            startActivity(new Intent(PrincipalPersonal.this,Menu.class));
        }
    }

    private boolean Autenticacao(){
        return armazenamentoLocal.VerificaLogado();
    }

    private void motrarUsuario(String usuario_var){
        Usuario usuario = armazenamentoLocal.pegaDadosusuario();
        usuario_var = usuario.usuario;
        Toast.makeText(PrincipalPersonal.this,"Logado "+usuario_var, Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_personal_ui);
        //Armazenamento local
        armazenamentoLocal = new ArmazenamentoLocal(this);
        Usuario usuario = armazenamentoLocal.pegaDadosusuario();
        //Dados do perfil
        usuario_var = usuario.usuario;
        //Toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar_main);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setLogo(R.drawable.user);
        mToolbar.setSubtitle("Subtitulo");
        setSupportActionBar(mToolbar);
        //header
        final AccountHeader headerDireito = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.gradient_laranja)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .addProfiles(
                        new ProfileDrawerItem().withName("Personal " + usuario_var.toUpperCase()).withTypeface(Typeface.DEFAULT_BOLD).withEmail(email_var).withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
                        Toast.makeText(PrincipalPersonal.this, "Profile " + iProfile.getName(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .build();




        //Drawer
        DrawerDireito = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggleAnimated(true)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerDireito)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {

                        return false;
                    }
                })
                .build();

        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Logout").withIcon(R.drawable.unlock).withTypeface(Typeface.DEFAULT_BOLD).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {

                armazenamentoLocal.Limpa();
                armazenamentoLocal.DefinirLogado(false);
                LocationFound();

                return false;
            }
        }));
        DrawerDireito.addItem(new SectionDrawerItem().withName("Alunos"));
        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Cadastrar Alunos"));
        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Buscar Alunos"));
        DrawerDireito.addItem(new DividerDrawerItem().withTag("Avaliações"));
        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Cadastrar Avaliação"));
        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Buscar Avaliação"));
        DrawerDireito.addItem(new SectionDrawerItem().withName("Configurações"));
        DrawerDireito.addItem(new SwitchDrawerItem().withName("Notificações").withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener));
        DrawerDireito.addItem(new PrimaryDrawerItem().withName("Sobre"));

        //Header




    }
}
