package com.example.lab_21_gps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.telainicial.*

class MainActivity : AppCompatActivity() {

    private var context: Context? = null

    private var latitude = 0.0
    private var longitude = 0.0

    private var lm: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        initVars()
        initActions()
    }
    @SuppressLint("MissingPermission")
    private fun initActions() {

        btn_gps.setOnClickListener {
            habilitarDesabilitar(false)
            limpar()
//
            if (hasGPSPermition()) {
                lm?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, servico_localizacao);
            } else {
                requestGPSPermition()
                habilitarDesabilitar(true)
            }
        }
        btn_rede.setOnClickListener {
            habilitarDesabilitar(false)
            limpar()
//
            if (hasGPSPermition()) {
                lm?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L,
                    0f, servico_localizacao);
            } else {
                requestGPSPermition()
                habilitarDesabilitar(true)
            }
        }
        btn_parar.setOnClickListener {
            habilitarDesabilitar(true)
//
            lm?.removeUpdates(servico_localizacao)
//
            val urlM = "geo:0,0?q=" +
                    latitude.toString().replace(",", ".") +
                    "," +
                    longitude.toString().replace(",", ".")
            val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlM))
            startActivity(mIntent)
        }
    }

    private fun requestGPSPermition() {
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,0)
    }

    private fun hasGPSPermition(): Boolean {

        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            return true
        } else {
            return false
        }
    }

    private fun limpar() {
        tv_latitude.text = "0.0"
        tv_longitude.text = "0.0"
    }

    private fun habilitarDesabilitar(status: Boolean) {
        btn_gps.isEnabled = status
        btn_rede.isEnabled = status
    }

    private fun initVars() {
        context = this
        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

    }

    private val servico_localizacao = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            latitude = location.getLatitude()
            longitude = location.getLongitude()
//
            tv_latitude.text = latitude.toString()
            tv_longitude.text = longitude.toString()
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle)
        {
        }
        override fun onProviderEnabled(provider: String) {
        }
        override fun onProviderDisabled(provider: String) {
        }
    }
}
