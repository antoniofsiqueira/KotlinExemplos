package com.example.lab_13_credito

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.telainicial.*



class MainActivity : AppCompatActivity() {
    private var context: Context? = null
    private var mensagemErro: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)
        initVars()
        initActions()
    }

    private fun initActions() {
        context = this
    }

    private fun initVars() {
        btn_analisar_credito.setOnClickListener {
            if (validacao()) {
                analiseCredito()
            } else {
                exibirErro()
            }
        }
    }

    private fun exibirErro() {
        exibirMensagem(mensagemErro)
    }

    private fun analiseCredito() {
        val nome = formatacaoBD(et_nome.text.toString())
        val idade = convertIdade(et_idade.text.toString())
        var credito = 0.0
        if (cb_sp.isChecked) {
            if (idade >= 25) {
                credito = 5000.0
            } else {
                credito = 1000.0
            }
        } else {
            if (idade >= 25) {
                credito = 500.0
            }
        }
        exibirMensagem("$nome teve $credito liberado")
    }

    private fun validacao(): Boolean {
        val nome = et_nome.text.toString().trim()
        val idade = et_idade.text.toString().trim()
        if (nome.isEmpty()) {
            mensagemErro = getString(R.string.alerta_erro_nome_obrigatorio)
//
            return false
        }
//
        when (convertIdade(idade)) {
            -1 -> {
                mensagemErro = getString(R.string.alerta_erro_idade_obrigatoria)
//
                return false
            }
            -2 -> {
                mensagemErro = getString(R.string.alerta_erro_idade_invalida)
//
                return false
            }
            else -> {
                return true
            }
        }
    }

}
