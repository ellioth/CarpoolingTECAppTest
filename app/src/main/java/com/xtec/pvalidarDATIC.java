package com.xtec;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.DialogInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.xtec.auth.RequestManager;

public class pvalidarDATIC extends AppCompatActivity {
    //Variables de los botones y demas elementos de la pantalla
    private EditText ip_id;
    private EditText ip_contraseña;
    private Button btn_ingresar;
    private ImageButton btn_atras;

    //Bandera para saber si se va a registrar o iniciar sesion
    public static boolean Flag;

    public static String getClaveDatic;

    //Variables para guardar el json obtenido de la base
    private String jsonDatic;
    private String jsonDB;

    // Variable para completar el URL
    private String parameter;
    private int consulta;
    //Variables para el usuario y clave obtenidos de la pantalla
    private String id;
    private String clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvalidar_datic);

        btn_atras = (ImageButton) findViewById(R.id.btn_atras);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atras = new Intent (v.getContext(), pingresar.class);
                startActivityForResult(atras, 0);
            }
        });
        changeTextOnce();
    }

    public void changeTextOnce(){
        //Obtiene valores ingresados en la pantalla
        ip_id = (EditText)findViewById(R.id.ip_id);
        ip_contraseña = (EditText)findViewById(R.id.ip_contraseña);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verifica que no haya espacios vacios en la pantalla
                if (ip_id.getText().toString().isEmpty() || ip_contraseña.getText().toString().isEmpty()) {
                    datosIncompletos();
                }
                else {
                    id = ip_id.getText().toString();
                    parameter = id;
                    clave = ip_contraseña.getText().toString();
                    try {
                        // Para reutilizar la funcion, se cambia una variable local
                        //asi obtener el json de la base
                        consulta = 1;
                        //obtiene usuario de DATIC
                        jsonDatic = new HttpConsult().execute().get();
                        consulta = 2;
                        //obtiene usuario de la app
                        jsonDB = new HttpConsult().execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // True si va iniciar sesion
                    if (Flag) {
                        // Verifica si existe en la base de datos de la app
                        if (verificar_DB()) {
                            RequestManager.GET(1, jsonDatic);
                            // Verifica si existe en DATIC
                            if (verificar_DATIC(clave)) {
                                Log.e("Error", "entro");
                                //pasa a la pantalla principal
                                Intent atras = new Intent(v.getContext(), pantalla_principal.class);
                                startActivityForResult(atras, 0);
                            } else {
                                usuarioInvalidoDatic();
                            }
                        } else {
                            usuarioInvalidoDB();
                        }
                    }
                    // False si va a registrarse
                    else {
                        // Verifica si bo existe en la base de datos de la app
                        if (!verificar_DB()) {
                            RequestManager.GET(1, jsonDatic);
                            // Verifica si existe en DATIC
                            if (verificar_DATIC(clave)) {
                                //pasa a la pantalla de ingresar datos
                                Intent atras = new Intent(v.getContext(), pregistarse.class);
                                startActivityForResult(atras, 0);
                            } else {
                                usuarioInvalidoDatic();
                            }
                        } else {
                            usuarioExisteDB();
                        }
                    }
                }
            }
        });
    }

    //Es un pop up para informar que los datos estan incompletos
    public void datosIncompletos(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Existe campos incompletos, por favor completarlos.");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
    //Es un pop up para informar que el usuario o la clave es invalida de DATIC
    private void usuarioInvalidoDatic(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Usuario o clave inválida de DATIC.");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
    //Es un pop up para informar que el usuario es invalido de la app
    private void usuarioInvalidoDB(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("No existe el usuario en la aplicación. Por favor, registrarse.");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
    //Es un pop up para informar que el usuario existe en la app
    private void usuarioExisteDB(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Ya existe el usuario en la aplicación.");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
    //Verifica si el usuario existe o no en DATIC
    public boolean verificar_DATIC(String pClave) {
        if (pClave.compareTo(getClaveDatic) == 0 && jsonDatic.compareTo("[]") != 0) {
            getClaveDatic = "";
            RequestManager.ID = id;
            return true;
        }
        else {
            return false;
        }
    }
    //Verifica si el usuario existe o no en la app
    public boolean verificar_DB() {
        return (jsonDB.compareTo("[]") != 0) ? true : false;
    }

    private class HttpConsult extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            URL url;
            BufferedReader reader=null;
            StringBuilder builder=null;
            try {
                if (consulta == 1) {
                    //url para obtener usuarios de DATIC
                    url = new URL("https://daticwebapi22019.azurewebsites.net/api/Usuarios/" + parameter);
                }
                else  {
                    //url para obtener usuarios de la app
                    url = new URL("https://carpoolingtecwebapi22019.azurewebsites.net/api/Usuario/Get/" + parameter);
                }
                builder = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                for (String line; (line = reader.readLine()) != null;) {
                    builder.append(line.trim());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
            }
            return builder.toString();
        }
    }
}


