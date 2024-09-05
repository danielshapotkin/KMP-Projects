package com.example.kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    @Serializable
    data class Test(
        val id: Int,
        val name: String
    )

    object ApiClient {
        private const val BASE_URL = "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize?\n" +
                "client_id=e86a9e2d-48cb-431f-8f21-b43ff05f057a\n" +
                "&response_type=code\n" +
                "&redirect_uri=myapp://auth/callback\n" +
                "&response_mode=query\n" +
                "&scope=https%3A%2F%2Fgraph.microsoft.com%2Fmail.read\n" +
                "&state=12345198e049d7d05"

        val apiService: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tw = findViewById<TextView>(R.id.tw)
        val btn = findViewById<Button>(R.id.button)

        handleDeepLink(intent?.data)

        btn.setOnClickListener {
           // ApiClient.apiService.getToken()
            val uri = Uri.parse("myapp://auth/callback?code=AUTHORIZATION_CODE&state=12345")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    private fun handleDeepLink(data: Uri?) {
        data?.let {
            val code = it.getQueryParameter("code")
            val state = it.getQueryParameter("state")
            val tw = findViewById<TextView>(R.id.tw)
            tw.text = "Authorization Code: $code\nState: $state"
        }
    }
}