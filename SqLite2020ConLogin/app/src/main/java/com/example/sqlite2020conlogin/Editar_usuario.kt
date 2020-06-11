package com.example.sqlite2020conlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Editar_usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_usuario)

        var miUsuario= this.retornameUnUsuario(33)


    }



    public fun retornameUnUsuario(id:Long): usuario {
        var usuarioAux =usuario("lalalala","lalalal")

        //select

        usuarioAux.id=id.toInt()
        usuarioAux.nombre="33"
        usuarioAux.clave="22"





        return usuarioAux

    }


}
