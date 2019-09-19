package com.example.lab_16_dbase.banco


object Constantes {

    // Banco de Dados
    val BANCO = "impacta.db3"
    val VERSAO = 1

    // Passagem de parametros da Primeira tela para a Segunda
    val PARAMETRO_ID = "parametro_id"
}

fun converterIdade(idade: String): Int {
    try {
        val indice = Integer.parseInt(idade)
        //
        return if (indice >= 5) indice else -1
    } catch (e: Exception) {
        return -1
    }
}