package com.example.sqlite2020conlogin

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alta_usuario.*
import java.io.IOException

class Alta_usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_usuario)
        val txtListar=findViewById<TextView>(R.id.txtListar)
        val txtId=findViewById<TextView>(R.id.txtId)
        val txtNombre=findViewById<TextView>(R.id.txtNombre)
        val txtClave=findViewById<TextView>(R.id.txtClave)


        val btnBorrarUsuarios=findViewById<Button>(R.id.btnBorrarUsuarios)
        val btnBorrarUnUsuario=findViewById<Button>(R.id.btnBorrarUnUsuario)
        val btnAltaUsuario=findViewById<Button>(R.id.btnGuardarUsuario)

        btnBorrarUsuarios.setOnClickListener {
            this.borrarTodosLosUsuario()
            this.listarUsuarios()
        }

        btnBorrarUnUsuario.setOnClickListener {
            if(txtId.getText().isNotEmpty()){
                val id=(txtId.getText().toString().toInt())
                this.borrarUsuario(id)
                txtId.setText("")
                this.listarUsuarios()
            }else{
                Toast.makeText(this, "Se debe pasar un ID ", Toast.LENGTH_SHORT).show()
            }
        }

        btnAltaUsuario.setOnClickListener {
            if( this.altaDeUsuario()>0 ){
                this.listarUsuarios()
                txtNombre.setText("")
                txtClave.setText("")
            }
        }
    }



    override fun onStart() {
        super.onStart()
        this.listarUsuarios()
    }
    private fun borrarTodosLosUsuario()
    {

        try {
            val admin = Admin_baseDeDatos(this,"SqLite2020", null, 2)
            val bd = admin.writableDatabase
            val cant = bd.delete("usuarios", "1", null)
            if (cant == 1){
                Toast.makeText(this, "Se borraron", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No existe usuarios para borrar", Toast.LENGTH_SHORT).show()
            }
            bd.close()
        }catch (e: IOException){
            Toast.makeText(this, "Error al borrar "+ e.message, Toast.LENGTH_SHORT).show()
        }

    } /*fun borrar */
    private fun borrarUsuario(idParaBorrar: Int)
    {
        if (idParaBorrar >0 && idParaBorrar.toString().isNotEmpty())
        {
            try {
                val admin = Admin_baseDeDatos(this,"SqLite2020", null, 2)
                val bd = admin.writableDatabase
                val cant = bd.delete("usuarios", "id=$idParaBorrar", null)
                if (cant == 1){
                    Toast.makeText(this, "Se borró el usuario con  ID= $idParaBorrar", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "No existe un usuario con dicho ID", Toast.LENGTH_SHORT).show()
                }
                bd.close()
            }catch (e: IOException){
                Toast.makeText(this, "Error al borrar "+ e.message, Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Se debe pasar un ID ", Toast.LENGTH_SHORT).show()
        }
    } /*fun borrar */

    private fun altaDeUsuario(): Long {
        var retorno:Long= 0L
        Toast.makeText(this, "btnAltaUsuario", Toast.LENGTH_SHORT).show()
        if(this.txtNombre.getText().isNotEmpty()&&this.txtClave.getText().isNotEmpty() )
        {

            var nombre=this.txtNombre.getText().toString()
            var clave=this.txtClave.getText().toString()
            try {
                val admin = Admin_baseDeDatos(this,"SqLite2020", null, 2)
                val bd = admin.writableDatabase
                val registro = ContentValues()
                registro.put("nombre",nombre)
                registro.put("clave",clave)
                val newId=bd.insert("usuarios", null, registro)
                retorno= newId
                bd.close()
                Toast.makeText(this, "se creo el ID: $newId", Toast.LENGTH_SHORT).show()
            }catch (e:Throwable)
            {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }else
        {
            Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show()
        }
        return retorno
    }/*fin altaDeUsuario*/



    private fun listarUsuarios()
    {
        this.txtListar.setText("")
        try {
            val admin = Admin_baseDeDatos(this, "SqLite2020", null, 2)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select id,nombre,clave from usuarios", null)
            if (fila.moveToFirst()) {
                do {
                    val id: String = fila.getString(0)
                    val nombre: String = fila.getString(1)
                    val clave: String = fila.getString(2)
                    this.txtListar.append(id +" "+nombre+" "+clave+" \n")
                } while (fila.moveToNext())
            } else{
                Toast.makeText(this, "No hay Usuarios para listar",  Toast.LENGTH_SHORT).show()
            }
            bd.close()
        }catch (e:Throwable)
        {
            Toast.makeText(this, "No hay artículo ${e.message}",  Toast.LENGTH_SHORT).show()
            this.txtListar.append(e.message)
        }

    }




}
