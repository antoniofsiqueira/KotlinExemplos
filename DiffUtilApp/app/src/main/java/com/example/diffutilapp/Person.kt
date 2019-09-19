package com.example.diffutilapp


data class Person(var name: String, var status: String) : Comparable<Person> {


    override fun compareTo(other: Person): Int {
        if (other.name == (this.name) && other.status == this.status) {
            return 0
        }
        return 1
    }


}