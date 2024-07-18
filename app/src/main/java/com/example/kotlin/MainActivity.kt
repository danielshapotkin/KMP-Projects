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
        test(null)
        val array = arrayOf(1, 2, 3, 4, 5) // Массив из 5 элементов, каждый элемент равен 0

            Log.d("4", "3dkm ${2+2}")

        val items = listOf("apple", "banana", "orange")
        mutableListOf("apple", "banana", "orange")

        for (item in items){
            Log.d("", item)
        }
        var index = 0
        while (index<items.size){
            Log.d("", items[index])
            index++
        }
    fun test (str: String){
        Log.d("", str)
    }


    }
    fun test(vararg words: String?) {
        words.forEach {
            val result = it ?: "String is null"//Elvis опертаор
            Log.d("", result)


            //When

        }
    }

}