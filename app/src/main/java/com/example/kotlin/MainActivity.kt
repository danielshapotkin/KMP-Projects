package com.example.kotlin

import android.health.connect.datatypes.units.Length
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


       val square = Rectangle(2.2, 2.2)
        val rec = Rectangle(2.2, 2.2)
        if (square == rec){
            tw. text = "yes"
        }
    }
}


 data class Rectangle (var height: Double, var length: Double){
    var perim = (height + length) * 2

    var description = "description"
        get() = field
        set(value) {field = value}

}