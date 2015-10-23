package com.mobile.gynfit.gynfit;

//Classe com as fórmulas usadas no aplicativo

public class Formulas {
    //Indice de massa corporal
    //Formula para retornar o valor do IMC
    public float imc(float peso, float altura){
        float imc = peso/(altura*altura);
        return imc;
    }
    //Formula de verificação do valor do IMC
    public String verifica_imc(float imc){
        if (imc < 17){
            return ("Muito abaixo do peso");
        }
        if (imc >= 17 && imc < 18.5 ){
            return ("Abaixo do peso");
        }
        if (imc >= 18.5 && imc < 25.5 ){
            return ("Peso normal");
        }
        if (imc >= 25 && imc < 30 ){
            return ("Acima do peso");
        }
        if (imc >= 30 && imc < 35 ){
            return ("Obesidade I");
        }
        if (imc >= 35 && imc < 40 ){
            return ("Obesidade II");
        }
        if (imc >= 40){
            return ("Obesidade III");
        }
        return ("erro");
    }
}
