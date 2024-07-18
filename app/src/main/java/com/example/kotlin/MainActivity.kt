package com.example.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.NonCancellable.children

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tw = findViewById<TextView>(R.id.tw)

        val anna = Person("anna", 11, "shap")
        val dan = Person("dan", 11, "shap", anna)
        tw.text = "${anna.firstName} ${dan.firstName}"
    }
}

class Person(var firstName: String, var age: Int, var lastName: String) {
    var childrens: MutableList<Person> = mutableListOf()

    // Инициализатор класса
    init {
        println("Person $firstName is created")
    }

    // Вторичный конструктор
    constructor(firstName: String, age: Int, lastName: String, children: Person):
        this (firstName, age, lastName){
            childrens.add(children)
        }

}
