package com.xtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class p_preguntaCarro extends AppCompatActivity {
    private Button btn_si;
    private Button btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_pregunta_carro);
        //Si presiona que si desea agregar un carro, entonces pasa a esa pantalla
        btn_si = (Button) findViewById(R.id.btn_si);
        btn_si.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent si = new Intent(v.getContext(), pagregarCarro.class);
                startActivityForResult(si, 0);
            }
        });

        //Si presiona que no desea agregar un carro, entonces pasa a la pantalla principal
        btn_no = (Button) findViewById(R.id.btn_no);
        btn_no.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent no = new Intent(v.getContext(), pantalla_principal.class);
                startActivityForResult(no, 0);
            }
        });
    }
}
