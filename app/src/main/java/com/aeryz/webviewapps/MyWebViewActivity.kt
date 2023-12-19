package com.aeryz.webviewapps

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aeryz.webviewapps.databinding.ActivityMyWebViewBinding

class MyWebViewActivity : AppCompatActivity() {

    private val binding: ActivityMyWebViewBinding by lazy {
        ActivityMyWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        val url = intent.getStringExtra("URL")
        openUrlFromWebView(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openUrlFromWebView(url: String?) {
        val webView = binding.myWebView
        webView.webViewClient = object : WebViewClient() {
            val pd = ProgressDialog(this@MyWebViewActivity)

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                //return super.shouldOverrideUrlLoading(view, request)
                val requestUrl = request?.url.toString()
                if (requestUrl.contains("gojek://")
                    || requestUrl.contains("shopeeid://")
                    || requestUrl.contains("//wsa.wallet.airpay.co.id/")
                    || requestUrl.contains("/gopay/partner/")
                    || requestUrl.contains("/shopeepay/")
                ) {
                    val intent = Intent(Intent.ACTION_VIEW, request?.url)
                    startActivity(intent)
                    // `true` means for the specified URL, it will be handled by OS by starting Intent
                    return true
                } else {
                    // `false` means any other URL will be loaded normally by the WebView
                    return false
                }
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                pd.setMessage("loading")
                pd.show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                pd.hide()
                super.onPageFinished(view, url)
            }

        }
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl(url.toString())
    }


    private fun setClickListener() {
        binding.btnCloseSnap.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}