package com.example.kotlin.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.R
import com.example.kotlin.data.InstagramApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private var code: String = ""
    private var accesToken: String = ""
    private var userId: String = ""
    private lateinit var tw: TextView
    private lateinit var webView: WebView
    private lateinit var btnReg: Button
    private lateinit var btnGet: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        // Открывать ссылки внутри WebView вместо внешнего браузера
        webView.webViewClient = WebViewClient()

        webView.clearCache(true)
        tw = findViewById(R.id.tw)
        btnReg = findViewById(R.id.button)
        btnGet = findViewById(R.id.button2)


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if (url.contains("code=")) {
                    handleDeepLink(Uri.parse(url))
                    return true
                }
                return false
            }
        }
        btnReg.setOnClickListener {
           val state = UUID.randomUUID().toString()
            //CustomChromeTab().openCustomTab(this, "https://www.instagram.com/oauth/authorize/third_party?client_id=438863165864035&redirect_uri=https%3A%2F%2Flocalhost.com%2Fcallback&scope=user_profile%2Cuser_media&response_type=code&logger_id=ba8bfc0b-2753-43a9-901a-0ac16a316a05&state=$state" )
            webView.loadUrl("https://instagram.com/oauth/authorize?client_id=438863165864035&redirect_uri=https://localhost/callback&scope=user_profile,user_media&response_type=code")
        }


        btnGet.setOnClickListener{
           lifecycleScope.launch {
         val response = withContext(Dispatchers.IO){
             InstagramApi().fetchInstagramAccessToken(code)
         }
               accesToken = response?.accessToken ?: "null"
               userId = response?.userId ?: "null"
               val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
               val editor = sharedPreferences.edit()
               editor.putString("token", accesToken)
               editor.apply()
               val username = withContext(Dispatchers.IO){
                   InstagramApi().getUsername(accesToken)?.username ?: ""
               }
               tw.text = username
               Log.d("Username", username)

            }
        }
    }



    private fun handleDeepLink(data: Uri?) {
        data?.let {
            Toast.makeText(this, "Deeplink", Toast.LENGTH_LONG).show()
            code = it.getQueryParameter("code").toString()
            tw.text = "Authorization Code: $code"
            (webView.parent as? ViewGroup)?.removeView(webView)
        }
    }



}