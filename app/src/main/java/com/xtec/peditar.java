package com.xtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class peditar extends AppCompatActivity {
    //Botones de la pantalla
    private Button btn_ep;
    private Button btn_ec;
    private Button btn_eca;
    private ImageButton btn_notificacion;
    private ImageButton btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peditar);
        //Hilo de todas las posibles funciones de la pantalla de editar perfil
        editar_perfil();
        eliminar_cuenta();
        editar_carros();
        home();
        notificacion();
    }
    //Esta funcion muestra la pantalla de editar perfil en caso de presionar
    //la imagen de edicion
    public void editar_perfil(){
        btn_ep = (Button) findViewById(R.id.btn_ep);
        btn_ep.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent perfil = new Intent (v.getContext(), peditaarInfo.class);
                startActivityForResult(perfil, 0);
            }
        });
    }
    //Esta funcion muestra si desea eliminar la cuenta en caso de presionar
    //en el basurero
    public void eliminar_cuenta(){
        btn_ec = (Button) findViewById(R.id.btn_ec);
        btn_ec.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent eliminar = new Intent (v.getContext(), peliminarCuenta.class);
                startActivityForResult(eliminar, 0);
            }
        });
    }
    //Esta funcion muestra la pantalla de editar carros en caso de presionar
    //en el mas
    public void editar_carros(){
        btn_eca = (Button) findViewById(R.id.btn_eca);
        btn_eca.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent carros = new Intent (v.getContext(), Carro.class);
                startActivity(carros);
            }
        });
    }
    //Esta funcion muestra la pantalla principal en caso de presionar
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
        btn_notificacion = (ImageButton) findViewById(R.id.btn_notifiacion);
        btn_notificacion.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent notificacion = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(notificacion, 0);
            }
        });
    }
}
