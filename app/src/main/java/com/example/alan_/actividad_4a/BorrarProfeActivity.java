package com.example.alan_.actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BorrarProfeActivity extends AppCompatActivity {

    //declaramos las variables de los datos a guardar
    private String idProfe;
    private EditText etIdProfe;
    private Button btBorrar, btAceptar;
    private String[] datos;
    private TextView tvRegistro, tvPregunta;

    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_profe);

        //asignamos a cada variable su view
        etIdProfe = (EditText) this.findViewById(R.id.et_idProfe);
        btBorrar = (Button) this.findViewById(R.id.bt_borrar);
        adaptador = new Adaptador(this);
        tvRegistro = (TextView) this.findViewById(R.id.tv_registro);
        tvPregunta = (TextView) this.findViewById(R.id.tv_pregunta);
        btAceptar = (Button) this.findViewById(R.id.bt_aceptar);
        adaptador = new Adaptador(this);
        adaptador.open();
        datos = new String[6];

        btBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //cogemos valores de los views y los asignamos a las variables
                idProfe = String.valueOf(etIdProfe.getText());

                tvPregunta.setVisibility(View.VISIBLE);
                tvRegistro.setVisibility(View.VISIBLE);
                btAceptar.setVisibility(View.VISIBLE);

                datos = adaptador.seleccionar("profesores", idProfe, datos);
                tvRegistro.setText(datos[0]+" "+datos[1]+" "+datos[2]+" "+datos[3]+" "+datos[4]+" "+datos[5]);
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adaptador.borrar("profesores", idProfe);

                Intent intent = new Intent(BorrarProfeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
