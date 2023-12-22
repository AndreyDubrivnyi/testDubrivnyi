package com.busuu.android.testdubrivnyi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InfoAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.slide_bot)

    }
}