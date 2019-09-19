package com.example.realmapp


import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class InitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("example.realm").build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}