package com.mobile.gynfit.gynfit;

/**
 * Created by m4ster on 22/10/15.
 */
public class Perfil {
    String nome,idade,endereco,email,telefone,sexo,usuario;
    Perfil(String nome, String idade,String endereco,String email, String telefone, String sexo,String usuario){
        this.usuario = usuario;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;

    }
    Perfil(String usuario){
        this.usuario = usuario;
    }
}
