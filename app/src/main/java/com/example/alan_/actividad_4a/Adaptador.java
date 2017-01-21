package com.example.alan_.actividad_4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alan_ on 03/01/2017.
 */

public class Adaptador {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbAct4a.db";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiantes";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String MEDIA = "media";
    private static final String TUTOR = "tutor";
    private static final String DESPACHO = "despacho";


    private static final String DATABASE_CREATE_TABLE_E = "CREATE TABLE "+DATABASE_TABLE_ESTUDIANTES+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, media integer);";
    private static final String DATABASE_CREATE_TABLE_P = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso text, tutor text, despacho text);";
    private static final String DATABASE_DROP_E = "DROP TABLE IF EXISTS "+DATABASE_TABLE_ESTUDIANTES+";";
    private static final String DATABASE_DROP_P = "DROP TABLE IF EXISTS "+DATABASE_TABLE_PROFESORES+";";


    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public Adaptador (Context c){
        context = c;
        //creamos el nuevo MyDbHelper (recuerda que ésta clase está creada más abajo y extiende de SQLiteOpenHelper) y lo asignamos a la variable
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        //Creará la base de datos si no existe o la abrirá si existe
        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //creamos la base de datos con 2 tablas
            db.execSQL(DATABASE_CREATE_TABLE_E);
            db.execSQL(DATABASE_CREATE_TABLE_P);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //borramos tablas y creamos base de datos nueva
            db.execSQL(DATABASE_DROP_E);
            db.execSQL(DATABASE_DROP_P);
            onCreate(db);
        }
    }

    public void insertarEstudiante(String n, int e, String c, String q, int m){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,c);
        newValues.put(CURSO,q);
        newValues.put(MEDIA,m);

        db.insert(DATABASE_TABLE_ESTUDIANTES,null,newValues);
    }

    public void insertarProfe(String n, int e, String c, String q, String t, String d){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,n);
        newValues.put(EDAD,e);
        newValues.put(CICLO,c);
        newValues.put(CURSO,q);
        newValues.put(TUTOR,t);
        newValues.put(DESPACHO,d);

        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);
    }

    public void borrar(String tabla, String clave){
        db.delete(tabla, "_id="+clave, null);
    }

    public String[] seleccionar(String tabla, String clave, String[] datos){

        //hago un select de el registro según su _id
        Cursor cursor = db.rawQuery("SELECT * FROM "+tabla+" WHERE _id="+clave+" ", null);

        if (tabla == DATABASE_TABLE_ESTUDIANTES){
            if (cursor != null && cursor.moveToFirst()){
                for (int i=0; i<5; i++){
                    datos[i] = cursor.getString(i+1);
                }
            }
        }else  if (tabla == DATABASE_TABLE_PROFESORES){
            if (cursor != null && cursor.moveToFirst()){
                for (int i=0; i<6; i++){
                    datos[i] = cursor.getString(i+1);
                }
            }
        }
        return datos;
    }

    public void borrarBBDD(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

    public ArrayList<UnElemento> recuperar(String nombreTabla, String var1, String var2, String[] args3){
        ArrayList<UnElemento> registros = new ArrayList<UnElemento>();
        String consulta = null;

        if (var1!=null){
            consulta = var1+"=?";
            if (var2!=null){
                consulta = var1 +" =? AND " + var2 +" =?";
            }
        }else if (var2!=null){
            consulta = var2+"=?";
        }

        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(nombreTabla,null,consulta,args3,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                registros.add(new UnElemento(cursor.getString(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return registros;
    }

    public ArrayList<UnElemento> recuperar2Tablas(String var1, String var2, String[] args3){
        ArrayList<UnElemento> registros = new ArrayList<UnElemento>();

        String consulta = null;

        if (var1!=null){
            consulta = var1+"=?";
            if (var2!=null){
                consulta = var1 +" =? AND " + var2 +" =?";
            }
        }else if (var2!=null){
            consulta = var2+"=?";
        }

        //Recuperamos en un cursor la consulta realizada
        Cursor cursorE = db.query(DATABASE_TABLE_ESTUDIANTES,null,consulta,args3,null,null,null);
        Cursor cursorP = db.query(DATABASE_TABLE_PROFESORES,null,consulta,args3,null,null,null);
        //Recorremos el cursor
        if (cursorE != null && cursorE.moveToFirst()){
            do{
                registros.add(new UnElemento(cursorE.getString(0), cursorE.getString(1)));
            }while (cursorE.moveToNext());
        }

        if (cursorP != null && cursorP.moveToFirst()){
            do{
                registros.add(new UnElemento(cursorP.getString(0), cursorP.getString(1)));
            }while (cursorP.moveToNext());
        }

        return registros;
    }
}