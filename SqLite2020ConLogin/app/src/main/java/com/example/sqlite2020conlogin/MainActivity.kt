package com.example.sqlite2020conlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnUsuariosRegistro= findViewById<Button>(R.id.btnUsuariosRegistro)
        val btnUsuarioListado= findViewById<Button>(R.id.btnUsuarioListado)
        val btnLogin= findViewById<Button>(R.id.btnLogin)
        btnUsuariosRegistro.setOnClickListener{
            val intento1 = Intent(this, Alta_usuario::class.java)
            startActivity(intento1)
        }
        btnUsuarioListado.setOnClickListener{
            val intento1 = Intent(this, Listado_usuarios::class.java)
            startActivity(intento1)
        }
        btnLogin.setOnClickListener{
            val intento1 = Intent(this, Login_usuario::class.java)
            startActivity(intento1)
        }
    }
}
