package com.example.lab_13_credito

import android.content.Context
import android.widget.Toast
/**
 * Metodo para validar a idade
 */
fun convertIdade(idade:String):Int{
    try {
        val  idadeAux = Integer.parseInt(idade)
        return if (idadeAux <= 150 && idadeAux >=5){
            idadeAux

        }else{
            -2
        }
    } catch (e:Exception){
         return -1
    }
}
/**
 * Metodo para exibicao de mensagens ao usuario
 */
fun Context.exibirMensagem(mensagem: String?){
    Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show()
}
/**
 * Metodo que elimina espacos em branco para uma possivel gravacao em banco de
dados
*/
fun formatacaoBD(texto: String):String{
    return texto.trim()
}
