package com.example.lab_17_service_contador_2

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class Contador_Service : Service() {
    companion object {
        var ISRUNNING = false
    }
    private var mThread: Thread? = null
    override fun onCreate() {
        super.onCreate()
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (!ISRUNNING) {
            ISRUNNING = true
            mThread = Thread(Runnable {
                try {
                    var indice = 0

                    while (++indice <= 25) {
                        Thread.sleep(500)

                        Log.d("DEMO", indice.toString())
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }


                stopSelf()
            })

            mThread!!.start()
        } else {
        }
        return Service.START_STICKY
    }
    override fun onDestroy() {
        mThread?.interrupt()
//
        ISRUNNING = false
//
        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}