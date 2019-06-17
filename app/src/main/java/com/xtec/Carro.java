package com.xtec;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.xtec.auth.RequestManager;

public class Carro extends AppCompatActivity {
    private ImageButton btn_notificaciones;
    private ImageButton btn_home;
    private String route="Carro/Get/2017541286";
    private String deleteRoute="Carro/Delete?pPlaca=";
    private String id= "2014057732";
    private String jsonCars;
    private List<String> DataCars;
    private LinearLayout linearLayout;
    private String url_base="https://carpoolingtecwebapi22019.azurewebsites.net/api/";

    /**
     * constructor del activity, se arma sola y despliega
     * los carros que son propios de una persona sobre la
     * interfaz grafica.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Intent intent = getIntent();
        //id = intent.getStringExtra("key");
       // super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);
        ImageButton tt=findViewById(R.id.addCarro);
        home();
        notificacion();
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Carro.this, addNewCar.class);
                myIntent.putExtra("key", id);
                Carro.this.startActivity(myIntent);
            }
        });
        try {
            jsonCars = new HttpConsult().execute("---").get();
            Format();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para extraer los datos del Json de la consulta
     * cuando se obtiene se piden todos los datos de los carros
     * NO RECIBE NADA, NO RETORNA NADA, SETEA VALORES GLOBALES.
     */
    private void Format(){
        getInfoJson();

        //added LInearLayout
        linearLayout = findViewById(R.id.activity_linear_layout);
        for(int i=0; i<DataCars.size(); i++){
            // added Button
            Log.i("prueba", "\n"+DataCars.get(i)+"\n");
            final Button button = new Button(this);
            button.setText("\n"+DataCars.get(i)+"\n");
            button.setId(i);
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.carro,0,R.drawable.delete,0);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = button.getText().toString().substring(8,button.getText().toString().length()-1);
                    RequestManager.DELETE(url_base+route+data, "");
                    linearLayout.removeAllViews();
                    Format();
                }
            });
            linearLayout.addView(button);
        }

    }

    /**
     * metodo para parsear todas las placas
     * de los carros de una persona y mostrarlos.
     */
    private void getInfoJson(){
        DataCars= new ArrayList<>();
        try {
            JSONArray t1 =new JSONArray(jsonCars);
            for(int i=0; i<t1.length(); i++){
                JSONObject f1 = t1.getJSONObject(i);
                String tt= "Placa: "+ f1.getString("CA_placa");
                DataCars.add(tt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * clase que sirve que para obtener el Json crudo que genera la capa de
     * servicios cuando se llamada.
     * NO RECIBE DATOS, SE LE PUEDEN PASAR PERO NO LOS MANEJA
     * OPERA SOBRE DATOS GOBALES DE LA CLASE QUE LO INVOCA.
     */
    private class HttpConsult extends AsyncTask<String, Void, String> {

        /**
         * metodo sobrecargado, este es el que hace la magia,
         * busca sobre una direccion URL, extrae el Json crudo
         * y lo retorna, no se encarga de manejar demas.
         * @param route1
         * @return
         */
        @Override
        protected String doInBackground(String... route1) {
            URL url;
            BufferedReader reader=null;
            StringBuilder builder=null;
            try {

                url = new URL(url_base+route);
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    //Esta funcion muestra la pantalla principall en caso de presionar
    //la imagen de la casa
    public void home(){
        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent home = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(home, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de notificaciones en caso de presionar
    //la imagen de la campana
    public void notificacion(){
        btn_notificaciones = (ImageButton) findViewById(R.id.btn_notificaciones);
        btn_notificaciones.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent notificacion = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(notificacion, 0);
            }
        });
    }
}
