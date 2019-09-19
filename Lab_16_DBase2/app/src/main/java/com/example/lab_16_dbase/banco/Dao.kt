package com.example.lab_16_dbase.banco


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.lab_16_dbase.banco.Constantes
import com.example.lab_16_dbase.banco.SQLiteHelper
//import com.example.dbaseappkotlin.Constantes
//import com.example.dbaseappkotlin.SQLiteHelper

open class Dao(private val context: Context) {
    protected var db: SQLiteDatabase? = null

    fun abrirBanco() {
        val helper = SQLiteHelper(
            context,
            Constantes.BANCO,
            null,
            Constantes.VERSAO
        )

        this.db = helper.writableDatabase

    }

    fun fecharBanco() {
        if (db != null) {
            db!!.close()
        }
    }
}
