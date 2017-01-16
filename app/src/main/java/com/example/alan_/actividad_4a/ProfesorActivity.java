package com.example.alan_.actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfesorActivity extends AppCompatActivity {

    //declaramos las variables de los datos a guardar
    private String nombre, edad, ciclo, curso, tutor, despacho;
    private EditText etNombre, etEdad, etCiclo, etCurso, etTutor, etDespacho;
    private Button btGuardar;

    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        //asignamos a cada variable su view
        etNombre = (EditText) this.findViewById(R.id.et_nombre);
        etEdad = (EditText) this.findViewById(R.id.et_edad);
        etCiclo = (EditText) this.findViewById(R.id.et_ciclo);
        etCurso = (EditText) this.findViewById(R.id.et_curso);
        etTutor = (EditText) this.findViewById(R.id.et_tutor);
        etDespacho = (EditText) this.findViewById(R.id.et_despacho);
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
                tutor = String.valueOf(etTutor.getText());
                despacho = String.valueOf(etDespacho.getText());

                adaptador.open();

                adaptador.insertarProfe(nombre, Integer.valueOf(edad), ciclo, curso, tutor, despacho);

                Intent intent = new Intent(ProfesorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
