package com.xtec.auth;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//nuevo
import com.xtec.pvalidarDATIC;
import com.xtec.MainActivity;
import com.xtec.R;


public class RequestManager {

    public static String recent_data;
    public static String ID;
    //Obtiene el json del URL
    public static void GET(int option, String parameter) {
        Log.d("data", parameter);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(parameter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Obtiene datos solo si el json no viene vacio
        if (jsonArray != null && jsonArray.length() > 0) {
            JSONObject childJsonArray = jsonArray.optJSONObject(0);
            try {
                if (option == 1){
                    //Obtiene la contraseña del usuario de DATIC
                    pvalidarDATIC.getClaveDatic = childJsonArray.getString("uS_contra");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static OkHttpClient client = new OkHttpClient();

    // Agregar a la base de datos
    // Parametros: option -> para ingresar al URL necesario
    // data -> es el json preparado para ser enviado a la base de datos
    public static void POST(int option, String data){
        String URL;
        if (option == 1) {
            //Realiza post de un usuario
            URL = "https://carpoolingtecwebapi22019.azurewebsites.net/api/Usuario/POST/";
        }
        else if (option == 3) {
            URL = "https://carpoolingtecwebapi22019.azurewebsites.net/api/Carro/Post/";
        } else {
            //Realiza un telefono del usuario
            URL = "https://carpoolingtecwebapi22019.azurewebsites.net/api/Telefono/POST/";
        }

        try{
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, data);
            //Obtiene el json por medio del Builder() con el detalle de post()
            final Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                // En caso de no responder, muestra un error
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Error", "request ->" + call);
                    e.printStackTrace();
                }
                //Obtiene el json de la base de datos
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    SAVE_RESPONSE_DATA(response.body().string());
                    Log.i("Response", GET_REQUEST_DATA());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Funcion para eliminar en la base de datos
    public static void DELETE(String parameter, String data){

        String URL =  "http://192.168.1.6:7500/MediTECServer/meditec/patient/" + parameter;
        //String URL =  "http://172.19.12.235:7500/MediTECServer/meditec/patient/" + parameter;

        try{
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody body = RequestBody.create(JSON, data);
            //Obtiene el json por medio del Builder() con el detalle de delete()
            final Request request = new Request.Builder()
                    .url(URL)
                    .delete(body)
                    .build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Error", "request ->" + call);
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    SAVE_RESPONSE_DATA(response.body().string());
                    Log.i("Response", GET_REQUEST_DATA());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Guarda el dato de la base de datos en una variable local
    private static void SAVE_RESPONSE_DATA(String data) {
        recent_data = data;
    }

    //Obtiene el dato de la variable local que contiene el json
    public static String GET_REQUEST_DATA() {
        return recent_data;
    }
}
