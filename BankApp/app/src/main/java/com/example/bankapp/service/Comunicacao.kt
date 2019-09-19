package com.example.bankapp.service

import android.os.AsyncTask

class MyParams {
    var paramName: String = ""
    var paramaddress: String = ""
    var paramlatitude: Float = 0.toFloat()
    var paramlongitude: Float = 0.toFloat()

    constructor(funName: String, funAddress: String, funLatitude: Float, funLongitude: Float) {
        this.paramName = funName
        this.paramaddress = funAddress
        this.paramlatitude = funLatitude
        this.paramlongitude = funLongitude
    }
}

class Comunicacao : AsyncTask<MyParams, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(httpResponseMsg: String) {
        super.onPostExecute(httpResponseMsg)
        ...
    }

    override fun doInBackground(vararg params: MyParams): String {
        var name = params[0].paramName
        var address = params[0].paramaddress
        var latuitude = params[0].paramlatitude
        var longitude  = params[0].paramlongitude
        hashMap["name"] = name
        hashMap["address"] = address
        hashMap["latuitude"] = latuitude
        hashMap["longitude"] = longitude

        finalResult = httpParse.postRequest(hashMap, HttpURL)

        println("responseeeeeeeee-----="+finalResult)
        return finalResult
    }


}