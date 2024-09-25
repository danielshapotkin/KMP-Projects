package com.example.kotlin.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.kotlin.R
import com.example.kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private var code: String = ""
    private var accessToken: String = ""
    private var userId: String = ""

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
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

        mainViewModel.events.observe(this, eventsListener)
        mainViewModel.states.observe(this, uiStateListener)

        binding.button.setOnClickListener {
            mainViewModel.hideButtons()
            mainViewModel.getAuthCode().toString()
        }

        binding.button2.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.getToken(code)
                mainViewModel.getUsername()
            }
        }

        binding.button3.setOnClickListener(){
            val callbackUrl = "https://aichatbotauth/callback"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(callbackUrl)
            }
            startActivity(intent)
        }

handleIntent(intent)
    }
    private fun handleIntent(intent: Intent?) {
        intent?.data?.let { uri ->
            if (uri.host == "aichatbotauth" && uri.path == "/callback") {
                // Сработала ссылка
                Toast.makeText(this, "Сработала ссылка", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun handleDeepLink(data: Uri?) {
        data?.let {
            mainViewModel.showButtons()
            code = it.getQueryParameter("code").toString()
            (binding.webView.parent as? ViewGroup)?.removeView(binding.webView)
        }
    }

    private val eventsListener: (MainViewModel.Events) -> Unit = { event ->
        when (event) {
            MainViewModel.Events.Nothing -> { /* No action needed */
            }

            is MainViewModel.Events.OpenUrl -> {
                binding.webView.loadUrl(event.url)
            }

            MainViewModel.Events.HideButtons -> {
                binding.button.visibility = View.GONE
                binding.button2.visibility = View.GONE
            }

            MainViewModel.Events.ShowButtons -> {
                binding.button.visibility = View.VISIBLE
                binding.button2.visibility = View.VISIBLE
            }
        }
    }

    private val uiStateListener: (MainViewModel.States) -> Unit = { states ->
        binding.tw.text = states.text
        binding.progressBar.visibility = if (states.isShowProgress) View.VISIBLE else View.GONE
    }
}
