package com.example.lab_16_dbase.banco


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {

        try {
            val sb = StringBuilder()
            //
            sb.append(
                "CREATE TABLE IF NOT EXISTS [contatos] (\n" +
                        "  [idcontato] INT NOT NULL, \n" +
                        "  [nome] TEXT NOT NULL, \n" +
                        "  [telefone] TEXT NOT NULL, \n" +
                        "  [idade] INT NOT NULL, \n" +
                        "  CONSTRAINT [] PRIMARY KEY ([idcontato]));"
            )

            val comandos = sb.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            for (i in comandos.indices) {
                db.execSQL(comandos[i].toLowerCase())
            }
        } catch (e: Exception) {
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            val sb = StringBuilder()
            //
            sb.append("DROP TABLE IF EXISTS  [contatos];")

            //Segunda Tabela
            sb.append("DROP TABLE IF EXISTS  [contatos_n];")

            val comandos = sb.toString().split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            for (i in comandos.indices) {
                db.execSQL(comandos[i].toLowerCase())
            }
        } catch (e: Exception) {
        }

        onCreate(db)
    }
}

