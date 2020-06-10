package com.example.sqlite2020conlogin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Admin_baseDeDatos(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, clave text)")
        db.execSQL("create table Jugadas(id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion text,jugador text,fecha text)")
        /*
        * INSERT INTO datetime_text (d1, d2) VALUES(datetime('now'),datetime('now', 'localtime'));
        */
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}