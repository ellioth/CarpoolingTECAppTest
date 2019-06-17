package com.xtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class pantalla_principal extends AppCompatActivity {

    // Botones de la pantalla principal
    private ImageButton btn_notificacion;
    private ImageButton btn_viaje;
    private ImageButton btn_amigos;
    private ImageButton btn_CP;
    private ImageButton btn_configuracion;
    private ImageButton btn_historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        //Hilo de todas las posibles funciones de la pantalla principal
        notificaciones();
        viajes();
        amigos();
        canjear_puntos();
        configuraciones();
        historial();

    }
    //Esta funcion muestra la pantalla de notificaciones en caso de presionar
    //la imagen de la campana
    public void notificaciones(){
        btn_notificacion = (ImageButton) findViewById(R.id.btn_notificaciones);
        btn_notificacion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent notificacion = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(notificacion, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de viajes en caso de presionar
    //la imagen del mas
    public void viajes (){
        btn_viaje = (ImageButton) findViewById(R.id.btn_viaje);
        btn_viaje.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent viaje = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(viaje, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de amigos en caso de presionar
    //la imagen de unas personas
    public void amigos(){
        btn_amigos = (ImageButton) findViewById(R.id.btn_amigos);
        btn_amigos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent amigos = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(amigos, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de puntos en caso de presionar
    //la imagen del billete
    public void canjear_puntos(){
        btn_CP = (ImageButton) findViewById(R.id.btn_CP);
        btn_CP.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent cp = new Intent (v.getContext(), pcanjear.class);
                startActivityForResult(cp, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de configuraciones en caso de presionar
    //la imagen del engranaje
    public void configuraciones(){
        btn_configuracion = (ImageButton) findViewById(R.id.btn_configuraciones);
        btn_configuracion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent configuraciones = new Intent (v.getContext(), peditar.class);
                startActivityForResult(configuraciones, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla del historial en caso de presionar
    //la imagen de la estadistica
    public void historial(){
        btn_historial = (ImageButton) findViewById(R.id.btn_historial);
        btn_historial.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent historial = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(historial, 0);
            }
        });
    }
}
