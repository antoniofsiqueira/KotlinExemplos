package com.example.lab_19_webservice

import org.json.JSONObject
class Contato {
    var idcontato: Long = 0
    var nome: String? = null
    var idade: Int = 0

    companion object {
        val TABELA = "contatos"
        val IDCONTATO = "idcontato"
        val NOME = "nome"
        val IDADE = "idade"
    }
    constructor() {
        this.idcontato = -1L
        this.nome = "sem nome"
        this.idade = -1
    }
    constructor(idcontato: Long, nome: String, idade: Int) {
        this.idcontato = idcontato
        this.nome = nome
        this.idade = idade
    }
    constructor(jsonObject: JSONObject) {
        try {
            this.idcontato = jsonObject.getLong(IDCONTATO)
            this.nome = jsonObject.getString(NOME)
            if (jsonObject.has(IDADE)) {
                this.idade = jsonObject.getInt(IDADE)
            } else {
                this.idade = -1
            }
        } catch (e: Exception) {
            this.idcontato = -1L
            this.nome = "sem nome"
            this.idade = -1
        }
    }
    // Capaz de retornar a representacao do contato na forma de texto
    override fun toString(): String {
        return nome!!
    }
    // Metodo especializado em fazer a representacao da classe no formato JSON
    fun toJSONObject(): JSONObject? {
        try {
            val jsonObject = JSONObject()
            jsonObject.put(IDCONTATO, idcontato)
            jsonObject.put(NOME, nome)
            jsonObject.put(IDADE, idade)
            //
            return jsonObject
        } catch (e: Exception) {
            return null
        }
    }
}