package com.example.alan_.actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //declaramos las variables que van a interactuar
    private Button btNuevoEstudiante, btNuevoProfe, btBorrarEstudiante, btBorrarProfe, btBorrarBbdd, btBuscar;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignamos a cada variable su view
        btNuevoEstudiante = (Button) this.findViewById(R.id.bt_nuevoEstudiante);
        btNuevoProfe = (Button) this.findViewById(R.id.bt_nuevoProfe);
        btBorrarEstudiante = (Button) this.findViewById(R.id.bt_borrarEstudiante);
        btBorrarProfe = (Button) this.findViewById(R.id.bt_borrarProfe);
        btBorrarBbdd = (Button) this.findViewById(R.id.bt_borrarBbdd);
        btBuscar = (Button) this.findViewById(R.id.bt_buscar);

        btNuevoEstudiante.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EstudianteActivity.class);
                startActivity(intent);
            }
        });

        btNuevoProfe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfesorActivity.class);
                startActivity(intent);
            }
        });

        btBorrarEstudiante.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BorrarEstudianteActivity.class);
                startActivity(intent);
            }
        });

        btBorrarProfe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BorrarProfeActivity.class);
                startActivity(intent);
            }
        });

        btBorrarBbdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                adaptador = new Adaptador(MainActivity.this);
                adaptador.borrarBBDD(MainActivity.this);
            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuscarActivity.class);
                startActivity(intent);
            }
        });
    }
}
