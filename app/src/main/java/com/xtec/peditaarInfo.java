package com.xtec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class peditaarInfo extends AppCompatActivity {
    //Botones de la pantalla y textos de la pantalla
    private ImageButton btn_home;
    private ImageButton btn_notificaciones;
    private EditText ip_name;
    private EditText ip_PA;
    private EditText ip_SA;
    private EditText ip_correo;
    private EditText ip_telefono;
    private ImageButton btn_plus;
    private Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peditaar_info);
        //Hilo de todas las posibles funciones de la editar informacion
        home();
        notificacion();
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
        btn_notificaciones = (ImageButton) findViewById(R.id.btn_notifiacion);
        btn_notificaciones.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent notificacion = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(notificacion, 0);
            }
        });
    }

}
