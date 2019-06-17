package com.xtec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class pagregarCarro extends AppCompatActivity {

    private Button btn_aceptar;
    private Button btn_cancelar;
    private EditText ip_placa;
    private EditText ip_marca;
    private EditText ip_modelo;
    private EditText ip_cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagregar_carro);

        btn_cancelar = (Button) findViewById(R.id.btn_aceptar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent no = new Intent(v.getContext(), pantalla_principal.class);
                startActivityForResult(no, 0);
            }
        });
        guardarDatos();
    }
    //Esta funcion guarda datos del carro
    public void guardarDatos(){
        // Obtiene datos de la pantalla principal
        ip_placa = (EditText)findViewById(R.id.ip_placa);
        ip_marca = (EditText)findViewById(R.id.ip_marca);
        ip_modelo = (EditText)findViewById(R.id.ip_modelo);
        ip_cp = (EditText)findViewById(R.id.ip_cp) ;

        btn_aceptar = (Button) findViewById(R.id.btn_cancelar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ip_placa.getText().toString().isEmpty() || ip_marca.getText().toString().isEmpty()) {
                    Intent guardar = new Intent (v.getContext(), pantalla_principal.class);
                    startActivityForResult(guardar, 0);
                    //datosIncompletos();

                }



                /*if (!ip_id.getText().toString().isEmpty()){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "entre", Toast.LENGTH_SHORT);
                    toast1.show();
                }*/
            }
        });
    }

    public void datosIncompletos(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Existe campos incompletos, por favor completarlos");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
            }
        });
        dialogo.show();
    }
}
