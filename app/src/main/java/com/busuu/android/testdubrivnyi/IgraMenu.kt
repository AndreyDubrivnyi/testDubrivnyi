package com.busuu.android.testdubrivnyi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import com.busuu.android.testdubrivnyi.databinding.ActivityIgraMenuBinding
import com.onesignal.OneSignal

class IgraMenu : AppCompatActivity() {
    private lateinit var binding: ActivityIgraMenuBinding
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIgraMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        oneSingal()
        binding.webview.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
        binding.go.setOnClickListener{
            val params: ViewGroup.LayoutParams = binding.go.layoutParams as ViewGroup.LayoutParams
            params.width -= 5
            params.height -= 5
            binding.go.layoutParams = params
            handler.postDelayed({
                params.width += 5
                params.height += 5
                binding.go.layoutParams = params
                val intent = Intent(this, BeginGame::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_top, R.anim.no_anim)
            }, 150)

        }
        binding.info.setOnClickListener{
            val params: ViewGroup.LayoutParams = binding.info.layoutParams as ViewGroup.LayoutParams
            params.width -= 5
            params.height -= 5
            binding.info.layoutParams = params
            handler.postDelayed({
                params.width += 5
                params.height += 5
                binding.info.layoutParams = params
            }, 150)
            val intent = Intent(this, InfoAct::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_top, R.anim.no_anim)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.slide_bot)

    }
    private fun oneSingal() {
        Log.d("MY_TAG","OSU HERE")
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId("") //HERE ONESINGAL ID
        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.initWithContext(this)
        OneSignal.promptForPushNotifications()
    }
}