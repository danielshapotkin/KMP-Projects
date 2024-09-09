package com.example.kotlin

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
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
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private var code: String = ""
    private var accesToken: String = ""
    private var userId: String = ""
    private lateinit var tw: TextView
    private lateinit var btnReg: Button
    private lateinit var btnGet: Button


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e("Info", "onNewIntent ${intent.data}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tw = findViewById(R.id.tw)
        btnReg = findViewById(R.id.button)
        btnGet = findViewById(R.id.button2)
        Log.e("Info", "Oncreate ${intent.data}")
        handleDeepLink(intent?.data)



        btnReg.setOnClickListener {
           val state = UUID.randomUUID().toString()
          CustomChromeTab().openCustomTab(this, "https://www.instagram.com/oauth/authorize/third_party?client_id=438863165864035&redirect_uri=https%3A%2F%2Flocalhost.com%2Fcallback&scope=user_profile%2Cuser_media&response_type=code&logger_id=ba8bfc0b-2753-43a9-901a-0ac16a316a05&state=$state" )

        }
        btnGet.setOnClickListener{
            lifecycleScope.launch {
                accesToken = InstagramApi().fetchInstagramAccessToken(code)?.accessToken ?: ""
                userId = InstagramApi().fetchInstagramAccessToken(code)?.userId ?: ""
                tw.text = "Acces token: $accesToken \n userId = $userId"
            }
        }
    }



    private fun handleDeepLink(data: Uri?) {
        data?.let {
            code = it.getQueryParameter("code").toString()
            tw.text = "Authorization Code: $code"
        }
    }



}