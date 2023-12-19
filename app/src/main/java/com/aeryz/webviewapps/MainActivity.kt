package com.aeryz.webviewapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aeryz.webviewapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowSnap.setOnClickListener { openUrlFromWebView() }
    }

    private fun getUrl(): String {
        return if (binding.etSnapUrl.text.toString().isEmpty()) {
            "https://sample-demo-dot-midtrans-support-tools.et.r.appspot.com/snap-redirect/"
        } else {
            binding.etSnapUrl.text.toString()
        }
    }

    private fun openUrlFromWebView() {
        val intent = Intent(this, MyWebViewActivity::class.java)
        intent.putExtra("URL", getUrl())
        startActivity(intent)
    }
}