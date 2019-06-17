package com.xtec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.xtec.auth.RequestManager;

public class addNewCar extends AppCompatActivity {

    private String route="Carro/Post/";
    private String id;
    private String infoCarro;
    private Button btn_aceptar;
    private Button btn_cancelar;
    private EditText ip_placa;
    private EditText ip_marca;
    private EditText ip_modelo;
    private EditText ip_cp;
    private String url_base="https://carpoolingtecwebapi22019.azurewebsites.net/api/";
    private JSONObject tt;

    /**
     * constructor de la clase, arma la interfaz grafica y queda en
     * espera de que se ingresen datos y puedan ser almacenados en el servidor.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getStringExtra("key");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car);
        btn_cancelar = findViewById(R.id.btn_aceptar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent no = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(no, 0);
            }
        });
        aceptar();
        cnacelar();
        guardarDatos();
    }

    /**
     * metodo para extraer los datos del vehiculo,
     * lo arma en un json, y los sube al servidor.
     */
    public void guardarDatos(){
        ip_placa = (EditText)findViewById(R.id.ip_placa);
        ip_marca = (EditText)findViewById(R.id.ip_marca);
        ip_modelo = (EditText)findViewById(R.id.ip_modelo);
        ip_cp = (EditText)findViewById(R.id.ip_cp) ;

        final JSONObject tt = new JSONObject();
        try {
            tt.put("CA_placa", ip_placa.getText());
            tt.put("CA_marca", ip_marca.getText());
            tt.put("CA_modelo", ip_modelo.getText());
            tt.put("CA_pass_cant", ip_cp.getText());
            tt.put("CA_userID", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btn_aceptar = findViewById(R.id.btn_aceptar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((ip_placa.getText().toString().length()>1 &&
                        ip_placa.getText().toString().length()<6 )||
                        ip_modelo.getText().toString().length()>1 ||
                        ip_cp.getText().toString().length()>1){
                    RequestManager.POST(3, tt.toString());
                    Intent no = new Intent(v.getContext(), Carro.class);
                    startActivity(no);
                }
                else
                    datosIncompletos();
            }
        });

    }

    /**
     * metodo para cuando hay datos incompletos, despliega un popup diciendo que nada
     * sirve, que se ingresaron datos erroneos.
     */
    public void datosIncompletos(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Error");
        dialogo.setMessage("Existe campos incompletos, por favor completarlos");
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
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
