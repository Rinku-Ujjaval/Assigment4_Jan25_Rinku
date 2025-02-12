package com.example.assigment4_jan25_rinku.screen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.assigment4_jan25_rinku.viewmodel.CollegeViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CollegeWebScreen(collegeViewModel: CollegeViewModel, url: String?) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl(url.toString())
        }
    )

}