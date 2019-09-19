package com.example.lab_14_imagens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.telainicial.*

class MainActivity : AppCompatActivity() {
    private var context: Context? = null
    private var adapterAct: ActionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)
        initVars()
        initActions()
    }

    private fun initActions() {
        lv_actions.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as HMAux
            adapterAct?.setSelectedId(id)
            exibirMensagem(item[HMAux.TEXTO_02])
        }
    }

    private fun initVars() {
        context = this
        adapterAct = ActionsAdapter(
            context!!,
            R.layout.celula,
            gerarRegistros()
        )
        lv_actions.adapter = adapterAct
    }

    private fun gerarRegistros(): List<HMAux> {
        val icons = intArrayOf(
            R.drawable.ic_beenhere_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_build_black_24dp,
            R.drawable.ic_business_center_black_24dp,
            R.drawable.ic_contact_phone_black_24dp
        )
        val dados = ArrayList<HMAux>()
//
        for (i in icons.indices) {
            val aux = HMAux()
            aux[HMAux.ID] = (i + 1).toString()
            aux[HMAux.TEXTO_01] = "Acao " + (i + 1).toString()
            aux[HMAux.TEXTO_02] = "Descricao " + (i + 1).toString()
            aux[HMAux.TEXTO_03] = icons[i].toString()
//
            dados.add(aux)
        }
//
        return dados
    }
}
