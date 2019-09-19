package com.example.bankapp

import android.content.Context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.telainicial.*
import java.lang.Double.parseDouble

class MainActivity : AppCompatActivity() {

    private var context: Context? = null
    private var mensagem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        mensagem = ""
    }

    private fun initActions() {
    btn_login.setOnClickListener {
        if(validate()){
            prosseguir()
        }else{
            exibeErro()
        }
     }
    }

    private fun exibeErro() {
        exibirMensagem(mensagem)
    }

    private fun validate(): Boolean {
        val usuario = et_nome.text.toString().trim()
        val senha = et_senha.text.toString().trim()

        var numeric = true

        if (usuario.isEmpty()) {
            mensagem = getString(R.string.alerta_erro_usuario_obrigatorio)
            limpaCampos()
            return false
        }
        if (senha.isEmpty()) {
            mensagem = getString(R.string.alerta_erro_senha_obrigatoria)
            limpaCampos()
            return false
        }
        // Confere se no usuário vem CPF ou Email
        try {
            val num = parseDouble(usuario)
        } catch (e: NumberFormatException) {
            numeric = false
        }
        if (numeric && isCpf(usuario)){
            mensagem = getString(R.string.alerta_erro_credencial_valido)
            limpaCampos()
            return false
        }
        if(!numeric && isEmail(usuario)) {
            mensagem = getString(R.string.alerta_erro_credencial_valido)
            limpaCampos()
            return false
        }
        return true
    }

    private fun limpaCampos(){
        et_nome.setText("")
        et_senha.setText("")
        et_nome.requestFocus()
    }

    private fun prosseguir() {
        //mensagem = getString(R.string.alerta_credenciais_validas)
        //exibeErro()
    }
/*
    private inner class Sincronismo : AsyncTask<MyParams, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg voids: Void): java.util.ArrayList<Contato>?
        {
            var contatos = ArrayList<String>()
            try {
                val jsonArray = JSONArray(gerarContatos_JSONObject(3))
                val transmissao = JSONObject()
                transmissao.put(getString(R.string.tag_contatos), jsonArray)
                val resultado = comunicacao(
                    Constantes.web_service,
                    transmissao.toString()
                )
                val jsonObjectResposta = JSONObject(resultado)
                val jsonArrayResposta = jsonObjectResposta.getJSONArray(getString(R.
                    string.tag_contatos))
                for (i in 0 until jsonArrayResposta.length()) {
                    contatos.add(Contato(jsonArrayResposta.getJSONObject(i)))
                }
            } catch (e: Exception) {
                val res = e.toString()
            }
            return contatos
        }
        override fun onPostExecute(contatos: java.util.ArrayList<Contato>) {
            super.onPostExecute(contatos)
            lv_contatos.adapter = context?.let {
                ArrayAdapter<Contato>(
                    it,
                    android.R.layout.simple_list_item_1,
                    contatos
                )
            } as ListAdapter?
        }
    }
*/
}
