package com.xtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xtec.pvalidarDATIC;

public class pingresar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingresar);

        Button btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        //Si presiona ingresar, entonces pasa a esa pantalla de ingresar
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bandera para saber que se va iniciar sesion
                pvalidarDATIC.Flag = true;
                Intent ingresar = new Intent (v.getContext(), pvalidarDATIC.class);
                startActivityForResult(ingresar, 0);
            }
        });
        //Si presiona para registrar, entonces pasa de registrar informacion
        Button btn_registarse = (Button) findViewById(R.id.btn_registarse);
        btn_registarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bandera para saber si se va a registrar
                pvalidarDATIC.Flag = false;
                Intent registrarse = new Intent (v.getContext(), pvalidarDATIC.class);
                startActivityForResult(registrarse, 0);
            }
        });
    }
}
