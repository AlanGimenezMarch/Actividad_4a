package com.example.alan_.actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EstudianteActivity extends AppCompatActivity {

    //declaramos las variables de los datos a guardar
    private String nombre, edad, ciclo, curso, media;
    private EditText etNombre, etEdad, etCiclo, etCurso, etMedia;
    private Button btGuardar;

    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        //asignamos a cada variable su view
        etNombre = (EditText) this.findViewById(R.id.et_nombre);
        etEdad = (EditText) this.findViewById(R.id.et_edad);
        etCiclo = (EditText) this.findViewById(R.id.et_ciclo);
        etCurso = (EditText) this.findViewById(R.id.et_curso);
        etMedia = (EditText) this.findViewById(R.id.et_media);
        btGuardar = (Button) this.findViewById(R.id.bt_guardar);
        adaptador = new Adaptador(this);

        btGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //cogemos valores de los views y los asignamos a las variables
                nombre = String.valueOf(etNombre.getText());
                edad = String.valueOf(etEdad.getText());
                ciclo = String.valueOf(etCiclo.getText());
                curso = String.valueOf(etCurso.getText());
                media = String.valueOf(etMedia.getText());

                adaptador.open();

                adaptador.insertarEstudiante(nombre, Integer.valueOf(edad), ciclo, curso, Integer.valueOf(media));

                Intent intent = new Intent(EstudianteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
