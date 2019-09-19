package com.example.lab_17_service_contador_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.telainicial.*

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
    }
    private fun initActions() {
        btn_play!!.setOnClickListener {
            val mIntent = Intent(context, Contador_Service::class.java)
            startService(mIntent)
        }
        btn_stop!!.setOnClickListener {
            val mIntent = Intent(context, Contador_Service::class.java)
            stopService(mIntent)
        }
    }

}
