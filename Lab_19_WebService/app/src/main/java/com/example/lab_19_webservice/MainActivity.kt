package com.example.lab_19_webservice

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.telainicial.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)
        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        Sincronismo().execute()
    }

    private fun initActions() {
    }

    private fun gerarContatos_JSONObject(quantidade: Int): ArrayList<JSONObject> {
        val contatos = ArrayList<JSONObject>()
        //
        for (i in 1..quantidade) {
            val cAux = Contato()
            cAux.idcontato = i.toLong()
            cAux.nome = "Nome - " + i.toString()
            cAux.idade = i * 2
            //
            contatos.add(cAux.toJSONObject()!!)
        }
        //
        return contatos
    }

    private inner class Sincronismo : AsyncTask<Void, Int, java.util.ArrayList<Contato>>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }
        override fun doInBackground(vararg voids: Void): java.util.ArrayList<Contato>?
        {
            var contatos = ArrayList<Contato>()
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
}
