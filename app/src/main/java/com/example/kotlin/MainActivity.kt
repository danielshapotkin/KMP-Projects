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


tw.text = groupWords(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")).toString()


    }
    fun groupWords(words: Array<String>): List<List<String>>{
        val result: MutableList<List<String>> = mutableListOf()
        val map = mutableMapOf<String, MutableList<String>>()

        for (word in words){
            val sortedWord = word.toCharArray().sorted().joinToString("")
            if(map.containsKey(sortedWord))
                map[sortedWord]?.add(word)
            else {
                map[sortedWord] = mutableListOf(word)
            }
        }

        for ((key, value) in map){
            result.add(value)
        }


        return result
    }
}

