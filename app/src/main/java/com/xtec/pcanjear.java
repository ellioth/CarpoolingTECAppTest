package com.xtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class pcanjear extends AppCompatActivity {
    // Botones de la pantalla de canjeo de puntos
    private ImageButton btn_home;
    private ImageButton btn_notificaciones;
    private Button btn_aceptar;
    private Button btn_cancelar;
    private EditText ip_puntosAcanjear;
    private TextView ip_puntosDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcanjear);
        //Hilo de todas las posibles funciones de canejo de puntos
        home();
        notificacion();
        cnacelar();
        aceptar();

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
    //Esta funcion muestra pup op en caso de presionar
    //aceptar para cancelar el agregar carro
    public void cnacelar(){
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent cancelar = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(cancelar, 0);
            }
        });
    }
    //Esta funcion muestra pup op en caso de presionar
    //aceptar para agregar carro
    public void aceptar(){
        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent aceptar = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(aceptar, 0);
            }
        });
    }
}
