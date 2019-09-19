package com.example.lab_16_dbase.dao


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.lab_16_dbase.banco.Dao
import com.example.lab_16_dbase.banco.HMAux
import com.example.lab_16_dbase.model.Contato

import java.util.ArrayList

class ContatoDao(context: Context) : Dao(context) {

    companion object {

        val TABELA = "contatos"
        val IDCONTATO = "idcontato"
        val NOME = "nome"
        val TELEFONE = "telefone"
        val IDADE = "idade"
    }

    fun inserirContato(contato: Contato) {
        abrirBanco()
        //
        val cv = ContentValues()
        cv.put(IDCONTATO, contato.idcontato)
        cv.put(NOME, contato.nome)
        cv.put(TELEFONE, contato.telefone)
        cv.put(IDADE, contato.idade)
        //
        db!!.insert(TABELA, null, cv)
        //
        fecharBanco()
    }

    fun alterarContato(contato: Contato) {
        abrirBanco()
        //
        val cv = ContentValues()
        //
        val filtro = "$IDCONTATO = ? "
        val argumentos = arrayOf(contato.idcontato.toString())
        //
        cv.put(NOME, contato.nome)
        cv.put(TELEFONE, contato.telefone)
        cv.put(IDADE, contato.idade)
        //
        db!!.update(TABELA, cv, filtro, argumentos)
        //
        fecharBanco()
    }

    fun apagarContato(idcontato: Long) {
        abrirBanco()
        //
        val filtro = "$IDCONTATO = ? "
        val argumentos = arrayOf(idcontato.toString())
        //
        db!!.delete(TABELA, filtro, argumentos)
        //
        fecharBanco()
    }

    fun obterContatoByID(idcontato: Long): Contato? {
        var cAux: Contato? = null
        //
        abrirBanco()
        //
        var cursor: Cursor?
        //
        try {
            val comando = " select * from contatos where idcontato = ? "
            val argumentos = arrayOf(idcontato.toString())

            cursor = db!!.rawQuery(comando.toLowerCase(), argumentos)

            while (cursor!!.moveToNext()) {
                cAux = Contato()
                cAux.idcontato = cursor.getLong(cursor.getColumnIndex(IDCONTATO))
                cAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                cAux.telefone = cursor.getString(cursor.getColumnIndex(TELEFONE))
                cAux.idade = cursor.getInt(cursor.getColumnIndex(IDADE))
            }

            cursor.close()
            cursor = null

        } catch (e: Exception) {
        }

        //
        fecharBanco()
        //
        return cAux
    }

    fun proximoID(): Long {
        var proID = 1L
        //
        abrirBanco()
        //
        var cursor: Cursor? = null
        //
        try {
            val comando = " select max(idcontato) + 1 as id from contatos "

            cursor = db!!.rawQuery(comando.toLowerCase(), null)

            while (cursor!!.moveToNext()) {
                proID = cursor.getLong(cursor.getColumnIndex("id"))
            }

            if (proID == 0L) {
                proID = 1
            }

            cursor.close()
            cursor = null

        } catch (e: Exception) {
        }

        //
        fecharBanco()
        //
        return proID
    }

    fun obterListaContatos(): ArrayList<HMAux> {
        val dados = ArrayList<HMAux>()
        //
        abrirBanco()
        //
        var cursor: Cursor? = null
        //
        try {
            val comando = " select idcontato, nome from contatos order by nome "

            cursor = db!!.rawQuery(comando.toLowerCase(), null)

            while (cursor!!.moveToNext()) {
                val hmAux = HMAux()
                //
                hmAux[HMAux.ID] = cursor.getString(cursor.getColumnIndex(IDCONTATO))
                hmAux[HMAux.TEXTO_01] = cursor.getString(cursor.getColumnIndex(NOME))
                //
                dados.add(hmAux)
            }

            cursor.close()
            cursor = null

        } catch (e: Exception) {
        }

        //
        fecharBanco()
        //
        return dados
    }
}

