package com.example.kotlin.data

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.domain.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(
    private val context: Context,
    private val webView: WebView, // Сделаем webView свойством, чтобы оно было доступно в других методах
    private val handleDeepLink: (Uri) -> Unit // Лямбда для обработки deep link
) :IRepository{

    init {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                if (url.contains("code=")) {
                    handleDeepLink(Uri.parse(url))
                    return true
                }
                return false
            }
        }
    }

    override fun getAuthCode() {
        webView.loadUrl("https://instagram.com/oauth/authorize?client_id=438863165864035&redirect_uri=https://localhost/callback&scope=user_profile,user_media&response_type=code")
    }

    override suspend fun getToken(authCode: String): String {
            val response = withContext(Dispatchers.IO) {
                Api().fetchInstagramAccessToken(authCode)
            }
            val accesToken = response?.accessToken ?: "null"
            val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("token", accesToken)
            editor.apply()
        return accesToken
    }

}
