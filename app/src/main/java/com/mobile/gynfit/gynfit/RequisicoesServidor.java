package com.mobile.gynfit.gynfit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sissi on 22/09/2015.
 */
public class RequisicoesServidor {
    ProgressDialog progressDialog;
    public static final int ESPERA_CONEXAO = 2000 * 15;
    public static final String ENDERECO_SERVIDOR = "http://batatabacon.netne.net/";

    public RequisicoesServidor(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processando");
        progressDialog.setMessage("Aguarde por favor...");
    }

    //Grava usuario
    public void GravaUsusuarioBackground(Usuario usuario, RetornoUsuario retornoUsuario) {
        progressDialog.show();
        new GravaUsuarioAssinc(usuario, retornoUsuario).execute();
    }
    //Grava perfil
    public void GravaPerfilBackground(Perfil perfil, RetornoPerfil retornoPerfil) {
        progressDialog.show();
        new GravaPerfilAssinc(perfil, retornoPerfil).execute();
    }
    //Grava AlunoDoPersonal
    public void GravaAlunoDoPersonalBackground(AlunoDoPersonal alunoDoPersonal , RetornoAlunoDoPersonal retornoAlunoDoPersonal) {
        progressDialog.show();
        new GravaAlunoDoPersonalAssinc(alunoDoPersonal, retornoAlunoDoPersonal).execute();
    }
    //Busca usuario
    public void BuscaUsuarioBackground(Usuario usuario, RetornoUsuario retornoUsuario) {
        progressDialog.show();
        new BuscaUsuarioAssinc(usuario,retornoUsuario).execute();

    }
    //Busca Perfil
    public void BuscaPerfilBackground(Perfil perfil, RetornoPerfil retornoPerfil) {
        progressDialog.show();
        new BuscaPerfilAssinc(perfil,retornoPerfil).execute();

    }
    //Grava usuario Assinc
    public class GravaUsuarioAssinc extends AsyncTask<Void, Void, Void> {
        Usuario usuario;
        RetornoUsuario retornoUsuario;

        public GravaUsuarioAssinc(Usuario usuario, RetornoUsuario retornoUsuario) {
            this.usuario = usuario;
            this.retornoUsuario = retornoUsuario;
        }


        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> enviaDado = new ArrayList<NameValuePair>();
            enviaDado.add(new BasicNameValuePair("usuario", usuario.usuario));
            enviaDado.add(new BasicNameValuePair("senha", usuario.senha));
            enviaDado.add(new BasicNameValuePair("tipo", usuario.tipo));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, ESPERA_CONEXAO);
            HttpConnectionParams.setSoTimeout(httpRequestParams, ESPERA_CONEXAO);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(ENDERECO_SERVIDOR + "registrar_usuario.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(enviaDado));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            retornoUsuario.Terminado(null);
            super.onPostExecute(aVoid);
        }
    }
    //Grava AlunoDoPersonal Assinc
    public class GravaAlunoDoPersonalAssinc extends AsyncTask<Void, Void, Void> {
        AlunoDoPersonal alunoDoPersonal;
        RetornoAlunoDoPersonal retornoAlunoDoPersonal;

        public GravaAlunoDoPersonalAssinc(AlunoDoPersonal alunoDoPersonal, RetornoAlunoDoPersonal retornoAlunoDoPersonal) {
            this.alunoDoPersonal = alunoDoPersonal;
            this.retornoAlunoDoPersonal = retornoAlunoDoPersonal;
        }


        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> enviaDado = new ArrayList<NameValuePair>();
            enviaDado.add(new BasicNameValuePair("personal", alunoDoPersonal.personal));
            enviaDado.add(new BasicNameValuePair("aluno", alunoDoPersonal.aluno));


            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, ESPERA_CONEXAO);
            HttpConnectionParams.setSoTimeout(httpRequestParams, ESPERA_CONEXAO);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(ENDERECO_SERVIDOR + "registra_alunodopersonal.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(enviaDado));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            retornoAlunoDoPersonal.Terminado(null);
            super.onPostExecute(aVoid);
        }
    }
    //Grava perfil Assinc
    public class GravaPerfilAssinc extends AsyncTask<Void, Void, Void> {
        Perfil perfil;
        RetornoPerfil retornoPerfil;

        public GravaPerfilAssinc(Perfil perfil, RetornoPerfil retornoPerfil) {
            this.perfil = perfil;
            this.retornoPerfil = retornoPerfil;
        }


        @Override
        protected Void doInBackground(Void... params) {

            ArrayList<NameValuePair> enviaDado = new ArrayList<NameValuePair>();
            enviaDado.add(new BasicNameValuePair("usuario",perfil.usuario ));
            enviaDado.add(new BasicNameValuePair("nome", perfil.nome));
            enviaDado.add(new BasicNameValuePair("idade", perfil.idade));
            enviaDado.add(new BasicNameValuePair("endereco", perfil.endereco));
            enviaDado.add(new BasicNameValuePair("email", perfil.email));
            enviaDado.add(new BasicNameValuePair("telefone", perfil.telefone));
            enviaDado.add(new BasicNameValuePair("sexo", perfil.sexo));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, ESPERA_CONEXAO);
            HttpConnectionParams.setSoTimeout(httpRequestParams, ESPERA_CONEXAO);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(ENDERECO_SERVIDOR + "registra_perfil.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(enviaDado));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();
            retornoPerfil.Terminado(null);
            super.onPostExecute(aVoid);
        }
    }

    public class BuscaUsuarioAssinc extends AsyncTask<Void, Void, Usuario> {
        Usuario usuario;
        RetornoUsuario retornoUsuario;

        public BuscaUsuarioAssinc(Usuario usuario, RetornoUsuario retornoUsuario) {
            this.usuario = usuario;
            this.retornoUsuario = retornoUsuario;
        }

        @Override
        protected Usuario doInBackground(Void... params) {
            ArrayList<NameValuePair> enviaDado = new ArrayList<NameValuePair>();
            enviaDado.add(new BasicNameValuePair("usuario", usuario.usuario));
            enviaDado.add(new BasicNameValuePair("senha", usuario.senha));
            enviaDado.add(new BasicNameValuePair("tipo", usuario.tipo));




            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, ESPERA_CONEXAO);
            HttpConnectionParams.setSoTimeout(httpRequestParams, ESPERA_CONEXAO);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(ENDERECO_SERVIDOR + "buscar_dados_usuario.php");
            Usuario usuarioRetornado = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(enviaDado));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                String resultado = EntityUtils.toString(httpEntity);

                JSONObject jsonObject = new JSONObject(resultado);

                if (jsonObject.length() == 0) {
                    usuarioRetornado = null;
                }else {
                    String tipo = jsonObject.getString("tipo");

                    usuarioRetornado = new Usuario(usuario.usuario,usuario.senha,tipo);

                }
            } catch (Exception e) {
                e.printStackTrace();

            }


            return usuarioRetornado;
        }

        @Override
        protected void onPostExecute(Usuario usuarioRetorno) {

            progressDialog.dismiss();
            retornoUsuario.Terminado(usuarioRetorno);
            super.onPostExecute(usuarioRetorno);
        }

    }
    //Busca perfil
    public class BuscaPerfilAssinc extends AsyncTask<Void, Void, Perfil> {
        Perfil perfil;
        RetornoPerfil retornoPerfil;

        public BuscaPerfilAssinc(Perfil perfil, RetornoPerfil retornoPerfil) {
            this.perfil = perfil;
            this.retornoPerfil = retornoPerfil;
        }

        @Override
        protected Perfil doInBackground(Void... params) {
            ArrayList<NameValuePair> enviaDado = new ArrayList<NameValuePair>();
            enviaDado.add(new BasicNameValuePair("usuario", perfil.usuario));
            enviaDado.add(new BasicNameValuePair("nome", perfil.nome));
            enviaDado.add(new BasicNameValuePair("idade", perfil.idade));
            enviaDado.add(new BasicNameValuePair("email", perfil.email));
            enviaDado.add(new BasicNameValuePair("endereco", perfil.endereco));
            enviaDado.add(new BasicNameValuePair("telefone", perfil.telefone));
            enviaDado.add(new BasicNameValuePair("sexo", perfil.sexo));




            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, ESPERA_CONEXAO);
            HttpConnectionParams.setSoTimeout(httpRequestParams, ESPERA_CONEXAO);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(ENDERECO_SERVIDOR + "buscar_dados_perfil.php");
            Perfil perfilRetornado = null;

            try {
                post.setEntity(new UrlEncodedFormEntity(enviaDado));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                String resultado = EntityUtils.toString(httpEntity);

                JSONObject jsonObject = new JSONObject(resultado);

                if (jsonObject.length() == 0) {
                    perfilRetornado = null;
                }else {
                    String nome = jsonObject.getString("nome");
                    String idade = jsonObject.getString("idade");
                    String email = jsonObject.getString("email");
                    String endereco = jsonObject.getString("endereco");
                    String telefone = jsonObject.getString("telefone");
                    String sexo = jsonObject.getString("sexo");
                    String usuario = jsonObject.getString("usuario");

                    perfilRetornado = new Perfil(nome,idade,email,endereco,telefone,sexo,usuario);

                }
            } catch (Exception e) {
                e.printStackTrace();

            }


            return perfilRetornado;
        }

        @Override
        protected void onPostExecute(Perfil perfilRetorno) {

            progressDialog.dismiss();
            retornoPerfil.Terminado(perfilRetorno);
            super.onPostExecute(perfilRetorno);
        }

    }
}
