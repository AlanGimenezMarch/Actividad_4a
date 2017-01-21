package com.example.alan_.actividad_4a;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity {

    private CheckBox cbEstudiantes, cbProfesores;
    private EditText etBuscaCiclo, etBuscaCurso;
    private Button btBuscar;
    private String ciclo, curso;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        cbEstudiantes = (CheckBox) this.findViewById(R.id.cb_estudiantes);
        cbProfesores = (CheckBox) this.findViewById(R.id.cb_profesores);
        etBuscaCiclo = (EditText) this.findViewById(R.id.et_buscaCiclo);
        etBuscaCurso = (EditText) this.findViewById(R.id.et_buscaCurso);
        btBuscar = (Button) this.findViewById(R.id.bt_buscar);
        adaptador = new Adaptador(this);

        adaptador.open();

        btBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ciclo = etBuscaCiclo.getText().toString();
                curso = etBuscaCurso.getText().toString();
                Intent intent = new Intent(BuscarActivity.this, ListadoActivity.class);

                String busqueda = "nadie";

                if (cbEstudiantes.isChecked()){
                    busqueda = "estu";
                    if (cbProfesores.isChecked()){
                        busqueda = "estuYprofe";
                        if (ciclo.isEmpty()==false){
                            busqueda = "estuProfeCiclo";
                            if (curso.isEmpty()==false){
                                busqueda = "clase";
                            }
                        }else if (curso.isEmpty()==false){
                            busqueda = "estuProfeCurso";
                        }
                    }else if (ciclo.isEmpty()==false){
                        busqueda = "estuCiclo";
                        if (curso.isEmpty()==false){
                            busqueda = "estuCicloCurso";
                        }
                    }else if (curso.isEmpty()==false){
                        busqueda = "estuCurso";
                    }
                }else if (cbProfesores.isChecked()){
                    busqueda = "profe";
                    if (ciclo.isEmpty()==false){
                        busqueda = "profeCiclo";
                        if (curso.isEmpty()==false){
                            busqueda = "profeCicloCurso";
                        }
                    }else if (curso.isEmpty()==false){
                        busqueda = "profeCurso";
                    }
                }

                intent.putExtra("busqueda", busqueda);
                intent.putExtra("ciclo", ciclo);
                intent.putExtra("curso", curso);

                startActivity(intent);
            }
        });
    }
}
