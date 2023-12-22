package com.busuu.android.testdubrivnyi

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SettingWew(private val activity: WebViewActivity) : WebChromeClient() {

    companion object {
        const val REQUEST_SELECT_FILE = 1000
        const val REQUEST_CAMERA_PERMISSION = 2000
        var uploadMessage: ValueCallback<Array<Uri>>? = null
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: WebChromeClient.FileChooserParams?
    ): Boolean {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
            return false
        }

        if (uploadMessage != null) {
            uploadMessage!!.onReceiveValue(null)
        }
        uploadMessage = filePathCallback

        try {
            val intent = fileChooserParams?.createIntent()
            if (intent != null) {
                activity.startActivityForResult(intent, REQUEST_SELECT_FILE)
            }
        } catch (e: ActivityNotFoundException) {
            uploadMessage = null
            Toast.makeText(activity, "Cannot open file chooser", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}