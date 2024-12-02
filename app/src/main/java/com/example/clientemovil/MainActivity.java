package com.example.clientemovil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = findViewById(R.id.btnEnviar);
        TextView txtRespuesta = findViewById(R.id.txtRespuesta);

        // Configuramos el botón para enviar la solicitud
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamar a la función para hacer la solicitud
                hacerSolicitud(txtRespuesta);
            }
        });
    }

    private void hacerSolicitud(final TextView txtRespuesta) {
        // URL del servidor
        String url = "http://10.10.13.65:3000/";

        // Crear la solicitud HTTP usando Volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Si la solicitud es exitosa
                        txtRespuesta.setText(response); // Mostrar la respuesta en el TextView
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Si hay un error en la solicitud
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Crear un RequestQueue y hacer la solicitud
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
