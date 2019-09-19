package com.example.lab_15_telas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.segundatela.*

class SegundaActivity : AppCompatActivity() {
    private var context: Context? = null
    private var tipo: Int = 0
    private var valor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.segundatela)
        initVars()
        initActions()
    }
    override fun onBackPressed(){
        if (tipo == 1){
            chamarPrimeiraTela()
        }else{
            finish()
        }
    }

    private fun initActions() {
        btn_multiplicar_5!!.setOnClickListener {
            val resultado_operacao = valor * 5
            val data = Intent()
            data.putExtra(Constantes.PARAMETRO_RETORNO, resultado_operacao)
            setResult(Activity.RESULT_OK,data)
            finish()
        }
    }

    private fun initVars() {
        context = this
        recuperaValores()
        btn_multiplicar_5!!.isEnabled = tipo != 1
        tv_valor_original!!.text = valor.toString()

    }

    private fun recuperaValores() {
        tipo = intent.getIntExtra(Constantes.PARAMETRO_TIPO, -1)
        valor = intent.getIntExtra(Constantes.PARAMETRO_VALOR, -1)
    }
    private fun chamarPrimeiraTela(){
        val mIntent = Intent(context, MainActivity::class.java)
        startActivity(mIntent)
        finish()
    }


}