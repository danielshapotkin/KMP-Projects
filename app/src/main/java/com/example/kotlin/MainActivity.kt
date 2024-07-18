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
        testWhen(1)


    }

    fun testWhen(input: Any) {
        when(input) {
        1-> Log.d("", "1")
        is String -> Log.d("", "Строка")
        else -> Log.d("", "Something else")
        }
    }





    }

}