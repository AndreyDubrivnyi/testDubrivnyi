package com.busuu.android.testdubrivnyi

import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.busuu.android.testdubrivnyi.databinding.ActivityBeginGameBinding

class BeginGame : AppCompatActivity() {
    private lateinit var vvfgfgfg: ActivityBeginGameBinding
    private val kkggh6 = arrayOf(
        R.drawable.xs1,
        R.drawable.xs2,
        R.drawable.xs3,
        R.drawable.xs4,
        R.drawable.xs5
    )
    private var klvgfgfgf: Int = 0
    private var f888: Int = 0
    private var f4: ImageView? = null
    private var sadasd: ImageView? = null
    private var kghhg: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vvfgfgfg = ActivityBeginGameBinding.inflate(layoutInflater)
        setContentView(vvfgfgfg.root)
        setupCardClickListeners()
        vvfgfgfg.kvvg.setOnClickListener {
            kggg5()
        }
    }

    private fun setupCardClickListeners() {
        val vggdf = arrayOf(
            vvfgfgfg.kggg5,
            vvfgfgfg.bbggh6,
            vvfgfgfg.kasdasd4,
            vvfgfgfg.kgg5,
            vvfgfgfg.kk6,
            vvfgfgfg.kgg6,
            vvfgfgfg.iiig8,
            vvfgfgfg.kgggh8,
            vvfgfgfg.kvggg5,
            vvfgfgfg.ssss3
        )

        for (i in vggdf.indices) {
            val uss = i
            vggdf[i].setOnClickListener {
                asdfff4(it as ImageView, uss)
            }
        }
    }

    private fun asdfff4(asdasdsad: ImageView, vggg: Int) {
        if (f4 == null) {
            f4 = asdasdsad
            klvgfgfgf = kkggh6[vggg % 5]
            asdasdsad.setImageResource(klvgfgfgf)
            kghhg++
        } else if (f4 != null && sadasd == null) {
            sadasd = asdasdsad
            f888 = kkggh6[vggg % 5]
            asdasdsad.setImageResource(f888)
            kghhg++
            if (klvgfgfgf != f888) {
                val vggg = Handler()
                vggg.postDelayed({
                    f4!!.setImageResource(R.drawable.backforapp)
                    sadasd!!.setImageResource(R.drawable.backforapp)
                    f4 = null
                    sadasd = null
                }, 1111)
            }
            if (klvgfgfgf == f888) {
                f4 = null
                sadasd = null
            }
        }
    }

    private fun kggg5() {
        val bgggg = Handler()
        bgggg.postDelayed({
            vgggg()
            kfff()
            kggghh6()
        }, 1400)
    }

    private fun vgggg() {
        kkggh6.shuffle()
    }

    private fun kfff() {
        val asdfghj = arrayOf(
            vvfgfgfg.kggg5,
            vvfgfgfg.bbggh6,
            vvfgfgfg.kasdasd4,
            vvfgfgfg.kgg5,
            vvfgfgfg.kk6,
            vvfgfgfg.kgg6,
            vvfgfgfg.iiig8,
            vvfgfgfg.kgggh8,
            vvfgfgfg.kvggg5,
            vvfgfgfg.ssss3
        )

        for (i in asdfghj.indices) {
            asdfghj[i].setImageResource(kkggh6[i % 5])
        }
    }

    private fun kggghh6() {
        val vvbbghgh = arrayOf(
            vvfgfgfg.kggg5,
            vvfgfgfg.bbggh6,
            vvfgfgfg.kasdasd4,
            vvfgfgfg.kgg5,
            vvfgfgfg.kk6,
            vvfgfgfg.kgg6,
            vvfgfgfg.iiig8,
            vvfgfgfg.kgggh8,
            vvfgfgfg.kvggg5,
            vvfgfgfg.ssss3
        )

        for (card in vvbbghgh) {
            card.setImageResource(R.drawable.backforapp)
        }

        f4 = null
        sadasd = null
        klvgfgfgf = 0
        f888 = 0
        kghhg = 0
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.no_anim, R.anim.slide_bot)

    }
}