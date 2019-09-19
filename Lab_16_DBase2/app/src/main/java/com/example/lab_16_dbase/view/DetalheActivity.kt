package com.example.lab_16_dbase.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_16_dbase.R
import com.example.lab_16_dbase.banco.Constantes
import com.example.lab_16_dbase.banco.converterIdade
import com.example.lab_16_dbase.dao.ContatoDao
import com.example.lab_16_dbase.model.Contato
import kotlinx.android.synthetic.main.detalhes.*

class DetalheActivity : AppCompatActivity() {

    private var context: Context? = null
    private var contatoDao: ContatoDao? = null

    private var idAtual: Long = 0

    private var mensagem: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhes)

        initVars()
        initActions()
    }

    private fun initVars() {
        context = this
        contatoDao = ContatoDao(context!!)

        idAtual = intent.getLongExtra(Constantes.PARAMETRO_ID, 0)

        if (idAtual == -1L) {
            btn_apagar!!.isEnabled = false
        } else {
            val cAux = contatoDao!!.obterContatoByID(idAtual)
            //
            et_codigo!!.setText(cAux!!.idcontato.toString())
            et_nome!!.setText(cAux.nome)
            et_telefone!!.setText(cAux.telefone)
            et_idade!!.setText(cAux.idade.toString())
            //
            btn_apagar!!.isEnabled = true
        }
    }

    private fun initActions() {

        btn_apagar!!.setOnClickListener {
            contatoDao!!.apagarContato(idAtual)
            //
            chamarPrimeiraTela()
        }

        btn_salvar!!.setOnClickListener {
            if (validacao()) {
                salvar()
            } else {
                exibirMensagemAlerta(getString(R.string.detalhes_alerta_erro_validacao), mensagem)
            }
        }
    }

    private fun validacao(): Boolean {
        val nome = et_nome!!.text.toString().trim { it <= ' ' }
        val telefone = et_telefone!!.text.toString().trim { it <= ' ' }
        val idade = converterIdade(et_idade!!.text.toString())

        if (nome.length == 0) {
            mensagem = getString(R.string.detalhes_alerta_erro_nome_obrigatorio)

            return false
        }

        if (telefone.length == 0) {
            mensagem = getString(R.string.detalhes_alerta_erro_telefone_obrigatorio)

            return false
        }

        if (idade == -1) {
            mensagem = getString(R.string.detalhes_alerta_erro_idade_invalida)

            return false
        }

        return true
    }

    private fun salvar() {
        val cAux = Contato()
        //
        cAux.nome = et_nome!!.text.toString().trim { it <= ' ' }
        cAux.telefone = et_telefone!!.text.toString().trim { it <= ' ' }
        cAux.idade = converterIdade(et_idade!!.text.toString())
        //
        if (idAtual != -1L) {
            cAux.idcontato = idAtual
            //
            contatoDao!!.alterarContato(cAux)
        } else {
            idAtual = contatoDao!!.proximoID()
            cAux.idcontato = idAtual
            //
            contatoDao!!.inserirContato(cAux)
            //
            btn_apagar!!.isEnabled = true
            //
            et_codigo!!.setText(idAtual.toString())
        }

        exibirMensagemAlerta(getString(R.string.detalhes_alerta_titulo_salvar_contato), getString(R.string.detalhes_alerta_mensagem_salvar_contato))
    }

    private fun exibirMensagemAlerta(titulo: String, texto: String?) {
        val alerta = AlertDialog.Builder(this)
        alerta.setTitle(titulo)
        alerta.setMessage(texto)
        alerta.setPositiveButton(getString(R.string.detalhes_alerta_rotulo_btn_ok), null)
        //
        alerta.show()
    }


    private fun chamarPrimeiraTela() {
        val mIntent = Intent(context, MainActivity::class.java)
        startActivity(mIntent)
        //
        finish()
    }

    override fun onBackPressed() {
        val alerta = AlertDialog.Builder(this)
        alerta.setTitle(getString(R.string.detalhes_alerta_titulo_sair))
        alerta.setMessage(getString(R.string.detalhes_alerta_mensagem_sair))
        alerta.setPositiveButton(getString(R.string.detalhes_alerta_rotulo_btn_sim)) { dialog, which -> chamarPrimeiraTela() }
        alerta.setNegativeButton(getString(R.string.detalhes_alerta_rotulo_btn_nao), null)
        //
        alerta.show()
    }
}