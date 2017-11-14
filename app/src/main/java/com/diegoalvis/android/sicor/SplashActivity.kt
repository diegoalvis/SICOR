package com.diegoalvis.android.sicor

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startArmyLogoAnimation()
    }

    private fun startArmyLogoAnimation() {
        imageArmy.animate().alpha(1.0f).setDuration(700).withEndAction({
            imageArmy.animate().translationX(imageArmy.measuredWidth / 1.3f).setDuration(300).withEndAction({
                startCorecLogoAnimation()
            })
        })
    }

    private fun startCorecLogoAnimation() {
        imageCorec.animate().alpha(1.0f).setDuration(700).withEndAction({
            imageCorec.animate().translationX(-(imageCorec.measuredWidth/ 1.3f)).setDuration(300).withEndAction({
                startAppNanemAnimation()
            })
        })
    }

    private fun startAppNanemAnimation() {
        textNameApp.animate().alpha(1.00f).setDuration(800).withEndAction({ launchMainActivity() })
    }

    private fun launchMainActivity() {
        textNameApp.postDelayed({
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                            }, 1000)
    }

}
