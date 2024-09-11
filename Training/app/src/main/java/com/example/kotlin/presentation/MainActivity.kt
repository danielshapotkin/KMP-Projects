package com.example.kotlin.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.R
import com.example.kotlin.data.Api
import com.example.kotlin.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var code: String = ""
    private var accesToken: String = ""
    private var userId: String = ""
    private lateinit var tw: TextView
    private lateinit var webView: WebView
    private lateinit var btnReg: Button
    private lateinit var btnGet: Button
    lateinit var repository: Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        tw = findViewById(R.id.tw)
        btnReg = findViewById(R.id.button)
        btnGet = findViewById(R.id.button2)
        repository = Repository(this, webView) { uri ->
            handleDeepLink(uri)
        }
        btnReg.setOnClickListener {
           code = repository.getAuthCode().toString()
        }

        btnGet.setOnClickListener {
            lifecycleScope.launch {
                repository.getToken(code)
            }
        }
    }

    private fun handleDeepLink(data: Uri?) {
        data?.let {
            Toast.makeText(this, "Deeplink", Toast.LENGTH_LONG).show()
            code = it.getQueryParameter("code").toString()
            (webView.parent as? ViewGroup)?.removeView(webView)
        }
    }


}