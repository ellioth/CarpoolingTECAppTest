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

public class peliminarCuenta extends AppCompatActivity {

    private Button btn_eliminar;
    private EditText ip_contraseña;
    private ImageButton btn_home;
    private ImageButton btn_notificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliminar_cuenta);
        //Hilo de todas las posibles funciones de la pantalla eliminar cuenta
        home();
        notificacion();
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);
        btn_eliminar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                pregunta();
            }
        });


    }
    //Esta funcion muestra una informacion en caso de presionar
    //la imagen del signo de pregunta
    public void pregunta(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Alerta");
        dialogo.setMessage("¿Desea eliminar la cuenta?");
        dialogo.setCancelable(false);
        //muestra mensaje si preiona que si
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Intent Si = new Intent(peliminarCuenta.this, pingresar.class);
                startActivityForResult(Si, 0);
            }
        });
        dialogo.show();
        // no muestra infromacion en caso de presionar no
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Intent No = new Intent(peliminarCuenta.this, peditar.class);
                startActivityForResult(No, 0);
                ;
            }
        });
        dialogo.show();
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
        btn_notificaciones = (ImageButton) findViewById(R.id.btn_notifiacion);
        btn_notificaciones.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent notificacion = new Intent (v.getContext(), pantalla_principal.class);
                startActivityForResult(notificacion, 0);
            }
        });
    }
}
