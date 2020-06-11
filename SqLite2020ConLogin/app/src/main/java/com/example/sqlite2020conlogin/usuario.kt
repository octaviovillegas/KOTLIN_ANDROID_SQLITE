package com.example.sqlite2020conlogin

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alta_usuario.*

class usuario
{
    var id:Int=0
    var nombre:String=""
    var clave:String=""
    constructor(nombre:String,clave:String)
    {
        this.nombre=nombre
        this.clave=clave
    }
    companion object{


        fun retornarArrayUsuarios(contexto: Context): MutableList<String> {
            val listado: MutableList<String> = ArrayList()
            val admin = Admin_baseDeDatos(contexto, "SqLite2020", null, 2)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select id,nombre,clave from usuarios", null)
            if (fila.moveToFirst()) {

                do {
                    val id: String = fila.getString(0)
                    val nombre: String = fila.getString(1)
                    val clave: String = fila.getString(2)
                    listado.add(id +" "+nombre+" "+clave)
                } while (fila.moveToNext())

            } else{
                Toast.makeText(contexto, "No hay usuarios",  Toast.LENGTH_SHORT).show()
            }
            bd.close()
            return listado
        }
    }


    public fun GuardarEnSqLite(contexto:Context): Long {
        var retorno:Long= 0L
        try {
            val admin = Admin_baseDeDatos(contexto,"SqLite2020", null, 2)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre",this.nombre)
            registro.put("clave",this.clave)
            retorno=bd.insert("usuarios", null, registro)
            bd.close()
            Toast.makeText(contexto, "se creo el ID: $retorno", Toast.LENGTH_SHORT).show()
        }catch (e:Throwable)
        {
            Toast.makeText(contexto, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }
    public fun ActualizarEnSqLite(contexto:Context): Long {
        var retorno:Long= 0L
        try {
            val admin = Admin_baseDeDatos(contexto,"SqLite2020", null, 2)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("nombre",this.nombre)
            registro.put("clave",this.clave)
            retorno=bd.insert("usuarios", null, registro)
            bd.close()
            Toast.makeText(contexto, "se creo el ID: $retorno", Toast.LENGTH_SHORT).show()
        }catch (e:Throwable)
        {
            Toast.makeText(contexto, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }
}