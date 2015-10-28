package com.mobile.gynfit.gynfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

//Classe para o armazenamento local no SaredPreferences
public class ArmazenamentoLocal {
    //Nome do arquivo
    public static final String SP_NAME = "Dados";
    SharedPreferences bancolocal;

    public ArmazenamentoLocal(Context context) {
        //Pega o contexto da Activity
        bancolocal = context.getSharedPreferences(SP_NAME, 0);
    }

    //Função para gravar no SharedPreferences Usuario
    public void gravaDadosUsuario(Usuario usuario) {
        SharedPreferences.Editor spEditor = bancolocal.edit();
        spEditor.putString("usuario", usuario.usuario);
        spEditor.putString("senha", usuario.senha);
        spEditor.putString("tipo", usuario.tipo);
        spEditor.commit();
    }
    //Função para gravar no SharedPreferences Perfil
    public void gravaDadosPerfil(Perfil perfil) {
        SharedPreferences.Editor spEditor = bancolocal.edit();
        spEditor.putString("usuario", perfil.usuario);
        spEditor.putString("nome", perfil.nome);
        spEditor.putString("idade", perfil.idade);
        spEditor.putString("email", perfil.email);
        spEditor.putString("endereco", perfil.endereco);
        spEditor.putString("telefone", perfil.telefone);
        spEditor.putString("sexo", perfil.sexo);
        spEditor.commit();
    }
    public void limpaDadosPerfil() {
        SharedPreferences.Editor spEditor = bancolocal.edit();
        spEditor.putString("usuario","");
        spEditor.putString("nome","");
        spEditor.putString("idade","");
        spEditor.putString("email","");
        spEditor.putString("endereco","");
        spEditor.putString("telefone","");
        spEditor.putString("sexo","");
        spEditor.commit();
    }

    //Função para buscar usuários no  SharedPreferences Usuario
    public Usuario pegaDadosusuario() {
        String usuario = bancolocal.getString("usuario", "");
        String senha = bancolocal.getString("senha", "");
        String tipo = bancolocal.getString("tipo", "");

        Usuario usuarioGravado = new Usuario(usuario, senha,tipo);
        return usuarioGravado;
    }
    //Função para buscar  no  SharedPreferences Perfil
    public Perfil pegaDadosperfil() {
        String usuario = bancolocal.getString("usuario", "");
        String nome = bancolocal.getString("nome", "");
        String idade = bancolocal.getString("idade", "");
        String email = bancolocal.getString("email", "");
        String endereco = bancolocal.getString("endereco", "");
        String telefone = bancolocal.getString("telefone", "");
        String sexo = bancolocal.getString("sexo", "");

        Perfil perfilGravado = new Perfil(nome,idade,email,endereco,telefone,sexo,usuario);
        return perfilGravado;
    }

    //Função para definir o usuario como logado
    public void DefinirLogado(Boolean logado) {
        SharedPreferences.Editor spEditor = bancolocal.edit();
        spEditor.putBoolean("Logado", logado);
        spEditor.commit();
    }

    //Função para limpara os dados armazenados no SharedPreferences
    public void Limpa() {
        SharedPreferences.Editor spEditor = bancolocal.edit();
        spEditor.clear();
        spEditor.commit();
    }

    //Função para verificar se o usuario está logado
    public boolean VerificaLogado() {
        if (bancolocal.getBoolean("Logado", false) == true) {
            return true;
        } else {
            return false;
        }

    }




}
