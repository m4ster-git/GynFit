package com.mobile.gynfit.gynfit;

/**
 * Created by sissi on 18/09/2015.
 */
public class Usuario {
    String usuario,senha,tipo;

    public Usuario(String usuario, String senha, String tipo){
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }
    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    public Usuario(String usuario){
        this.usuario = usuario;
    }
}
