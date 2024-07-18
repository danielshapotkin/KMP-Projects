package com.example.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Array

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.tw).text = "fghj"
        val a = listOf(1, 2, 3, 4)
        testLambda(a)


    }
//Lambda


    fun testLambda(input: List<Int>) {
   input.forEach { println(it*2) }
        input.map { println(it*2)  }
        println(input.filter { number-> number%2==0 })
        println(input.reduce { sum, number -> sum + number})



    }

}