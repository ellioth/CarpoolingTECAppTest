package com.xtec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtec.auth.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;

import com.xtec.auth.RequestManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class pregistarse extends AppCompatActivity {

    //Variables de los botones y demas elementos de la pantalla
    private EditText ip_name;
    private EditText ip_PA;
    private EditText ip_SA;
    private EditText ip_correo;
    private EditText ip_telefono;
    private TextView ip_carnet;
    private ImageButton btn_plus;
    private Button btn_continuar;
    private ImageButton btn_atras;

    //Variables para obtener los datos de la pantalla
    public static String setNombreDB;
    public static String setApellido1DB;
    public static String setApellido2DB;
    public static String setCorreoDB;
    public static String setTelefonoDB;
    public static String setRolDB = "U";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregistarse);

        //Obtener id del usuario de la variable global del programa
        ip_carnet = (TextView)findViewById(R.id.ip_carne);
        ip_carnet.setText(RequestManager.ID);
        btn_atras = (ImageButton) findViewById(R.id.btn_atras);
        btn_atras.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent atras = new Intent(v.getContext(), pvalidarDATIC.class);
                startActivityForResult(atras, 0);
            }
        });
        changeTextOnce();
    }

    public void changeTextOnce(){

        //Obtiene valores ingresados en la pantalla
        ip_name = (EditText)findViewById(R.id.ip_Nombre);
        ip_PA = (EditText)findViewById(R.id.ip_PA);
        ip_SA = (EditText)findViewById(R.id.ip_SA);
        ip_correo = (EditText)findViewById(R.id.ip_correo);
        ip_telefono = (EditText)findViewById(R.id.ip_telefono);
        btn_continuar = (Button) findViewById(R.id.btn_guardar);

        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verifica que no haya espacios vacios en la pantalla
                if (ip_name.getText().toString().isEmpty() || ip_PA.getText().toString().isEmpty()
                        || ip_SA.getText().toString().isEmpty() || ip_correo.getText().toString().isEmpty()
                        || ip_telefono.getText().toString().isEmpty()) {
                    datosIncompletos();
                }
                else {
                    // Obtiene datos de la pantalla principal
                    setNombreDB = ip_name.getText().toString();
                    setApellido1DB = ip_PA.getText().toString();
                    setApellido2DB = ip_SA.getText().toString();
                    setCorreoDB = ip_correo.getText().toString();
                    setTelefonoDB = ip_telefono.getText().toString();

                    //valida que el correo cumpla con los requisitos
                    if (validarCorreo(setCorreoDB)) {
                        String json = crearJSON(setNombreDB, setApellido1DB, setApellido2DB, setCorreoDB);
                        String jsonTel = crearJSONTel(setTelefonoDB);

                        RequestManager.POST(1, json);
                        RequestManager.POST(2, jsonTel);
                        // pasa a la pantalla si desea agregar carro
                        Intent atras = new Intent(v.getContext(), p_preguntaCarro.class);
                        startActivityForResult(atras, 0);
                    }
                    else {
                        correoInvalido();
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
    //Es un pop up para informar que el correo es invalido
    public void correoInvalido(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Correo inválido.");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
    //funcion para crear json de usuario en la app con id, nombre, apellidos, correo y rol
    public static String crearJSON(String pNombre, String pPA, String pSA, String pCorreo){

        JSONObject json = new JSONObject();
        try {
            json.put("US_ID", RequestManager.ID);
            json.put("US_nombre", pNombre);
            json.put("US_apellido1", pPA);
            json.put("US_apellido2", pSA);
            json.put("US_correo", pCorreo);
            json.put("US_rol", setRolDB);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json.toString();
    }
    //funcion para crear json para ingresar telefono a un usuario
    public static String crearJSONTel(String pTelefono){

        JSONObject json = new JSONObject();
        try {
            json.put("TE_userID", RequestManager.ID);
            json.put("TE_telefono", pTelefono);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json.toString();
    }

    //Valida si el correo cumple con las limitaciones
    public boolean validarCorreo (String pCorreo) {
        int len = pCorreo.length();
        // Si el correo solo incluye @xtec.ac.cr entonces no procede
        if (len > 11) {
            String temp = pCorreo.substring(len - 11, len);
          //el correo debe cumplir la extension de @xtec.ac.cr
            if (temp.compareTo("@xtec.ac.cr") == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }


    }
}