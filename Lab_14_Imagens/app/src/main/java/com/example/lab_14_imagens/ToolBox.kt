package com.example.lab_14_imagens

import android.content.Context
import android.widget.Toast


/**
 * Metodo para exibicao de mensagens ao usuario
*/
fun Context.exibirMensagem(mensagem: String?){
    Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show()
}

