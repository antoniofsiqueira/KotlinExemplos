package com.example.realmapp


import io.realm.RealmObject

open class Pokemon : RealmObject() {

    var name: String? = null
    var type: String? = null
}