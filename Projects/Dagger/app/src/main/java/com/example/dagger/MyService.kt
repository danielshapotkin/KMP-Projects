package com.example.dagger

import android.content.Context

class MyService(
    val context: Context,
    val id: Int
) {
    fun doNetworkRequest() {
        println("Doing network request with ID $id and context $context")
    }
}