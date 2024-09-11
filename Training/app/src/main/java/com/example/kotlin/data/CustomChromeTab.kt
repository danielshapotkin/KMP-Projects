package com.example.kotlin.data

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

class CustomChromeTab {

    fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val customTabsIntent = CustomTabsIntent.Builder()
            .build()
        customTabsIntent.launchUrl(context, uri)
    }
}