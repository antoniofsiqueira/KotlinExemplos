package com.example.lab_15_telas

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.telainicial.*

class MainActivity : AppCompatActivity() {
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        initVars()
        initActions()
    }

    private fun initActions() {
        btn_cs.setOnClickListener{
            val mIntent = Intent(context,SegundaActivity::class.java)
            mIntent.putExtra(Constantes.PARAMETRO_TIPO, 1)
            mIntent.putExtra(Constantes.PARAMETRO_VALOR, 10)
            startActivity(mIntent)
            finish()
        }

        btn_cr.setOnClickListener {
            val mIntent = Intent(context, SegundaActivity::class.java)
            mIntent.putExtra(Constantes.PARAMETRO_TIPO, 2)
            mIntent.putExtra(Constantes.PARAMETRO_VALOR,20)

            startActivityForResult(mIntent, PROCESSO_MULTIPLICAR_5)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            PROCESSO_MULTIPLICAR_5 ->processarRM5(resultCode, data)
            else ->{

            }
        }
    }

    private fun processarRM5(resultCode: Int, data: Intent?) {
        var resultado: String?

        if(resultCode == Activity.RESULT_OK){
            resultado = data!!.getIntExtra(Constantes.PARAMETRO_RETORNO, -1).toString()

        }else{
            resultado = getString(R.string.telainicial_alerta_retorno_cancelado)
        }
    Toast.makeText(context,resultado,Toast.LENGTH_SHORT).show()
    }

    private fun initVars() {
      context = this
    }

    private companion object{
        private val PROCESSO_MULTIPLICAR_5 = 10
    }
}
