package com.example.lab_16_dbase.view

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

import com.example.lab_16_dbase.R
import com.example.lab_16_dbase.banco.Constantes
import com.example.lab_16_dbase.banco.HMAux
import com.example.lab_16_dbase.dao.ContatoDao
import kotlinx.android.synthetic.main.telainicial.*

class MainActivity : AppCompatActivity() {

    private var context: Context? = null

    private var contatoDao: ContatoDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        //
        contatoDao = ContatoDao(context!!)
        //
        val De = arrayOf(HMAux.TEXTO_01)
        val Para = intArrayOf(android.R.id.text1)

        lv_contatos!!.adapter = SimpleAdapter(
            context,
            contatoDao!!.obterListaContatos(),
            android.R.layout.simple_list_item_1,
            De,
            Para
        )
    }

    private fun initActions() {

        lv_contatos!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val hmAux = parent.getItemAtPosition(position) as HMAux

            chamarTD(java.lang.Long.parseLong(hmAux[HMAux.ID]!!))
        }

    }

    private fun chamarTD(id: Long) {
        val mIntent = Intent(context, DetalheActivity::class.java)
        mIntent.putExtra(Constantes.PARAMETRO_ID, id)

        startActivity(mIntent)

        finish()
    }

    // Ele só exibe o menu de opcoes. + mostra esse elemento
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Processar o item selecionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.actionhugo_novo_contato) {

            chamarTD(-1L)

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
