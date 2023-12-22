package com.busuu.android.testdubrivnyi

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.busuu.android.testdubrivnyi.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.vieaska.restoreState(savedInstanceState)
        } else {
            jojo1()
            jojo2()
            jojo3()
            requestPermissions()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.vieaska.saveState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SettingWew.REQUEST_SELECT_FILE) {
            if (SettingWew.uploadMessage == null) return
            SettingWew.uploadMessage!!.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    data
                )
            )
            SettingWew.uploadMessage = null
        }
        if (SettingWew.uploadMessage == null) return

        val result = if (data == null || resultCode != Activity.RESULT_OK) null else data.data
        SettingWew.uploadMessage?.onReceiveValue(result?.let { arrayOf(it) } ?: arrayOf())
        SettingWew.uploadMessage = null
    }
    private fun requestPermissions() {
        val permissionsNeeded = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val permissionsToRequest = permissionsNeeded.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest, REQUEST_PERMISSIONS_CODE)
        }
    }

    companion object {
        const val REQUEST_PERMISSIONS_CODE = 1000
    }
    private fun jojo2() {
        binding.vieaska.webChromeClient = SettingWew(this)
        binding.vieaska.isNestedScrollingEnabled = true
        binding.vieaska.settings.apply {
            val userAgent = userAgentString
            userAgentString = "$userAgent Mobile"
            userAgentString = userAgentString
            allowFileAccessFromFileURLs = true
            loadsImagesAutomatically = true
            setRenderPriority(android.webkit.WebSettings.RenderPriority.HIGH)
            allowUniversalAccessFromFileURLs = true
            setSupportZoom(false)
            cacheMode = android.webkit.WebSettings.LOAD_DEFAULT
            savePassword = true
            saveFormData = true
            loadWithOverviewMode = true
            allowContentAccess = true
            mixedContentMode = 0
            allowFileAccess = true
            databaseEnabled = true
            domStorageEnabled = true
            setEnableSmoothTransition(true)
            useWideViewPort = true
            setSupportMultipleWindows(false)
            pluginState = WebSettings.PluginState.ON
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
        }
        binding.vieaska.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("whatsapp:")) {

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    ).apply {
                        addCategory(Intent.CATEGORY_BROWSABLE)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        setPackage("com.whatsapp")
                    }
                    view.context?.startActivity(intent)
                    return true
                }
                if (url.startsWith("tg:")) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    ).apply {
                        addCategory(Intent.CATEGORY_BROWSABLE)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        setPackage("org.telegram.messenger")
                    }
                    view.context?.startActivity(intent)
                    return true
                }
                if (url.startsWith("viber:")) {

                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    ).apply {
                        addCategory(Intent.CATEGORY_BROWSABLE)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        setPackage("com.viber.voip")
                    }
                    view.context?.startActivity(intent)
                    return true
                }
                if (url.startsWith("tel:")) {
                    val intent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse(url)
                    )
                    view.context?.startActivity(intent)
                    return true
                }
                if (url.startsWith("mailto:")) {
                    val intent = Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse(url)
                    )
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                    intent.putExtra(Intent.EXTRA_TEXT, "Email body")
                    view.context?.startActivity(intent)
                    return true
                }
                return if (url.contains("http://")) {
                    false
                } else if (url.contains("bnc")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    try {
                        view.context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                    }
                    true
                } else !url.contains("https://")
                return false
            }

        }
    }

    private fun jojo3() {
        binding.vieaska.loadUrl("https://fex.net/")
    }


    private fun jojo1() {
        android.webkit.CookieManager.getInstance().flush()
        android.webkit.CookieManager.getInstance().setAcceptCookie(true)
        android.webkit.CookieManager.getInstance().setAcceptThirdPartyCookies(binding.vieaska, true)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if (binding.vieaska.canGoBack()) {
            binding.vieaska.goBack()
        } else {
            moveTaskToBack(true)

        }
    }
}
