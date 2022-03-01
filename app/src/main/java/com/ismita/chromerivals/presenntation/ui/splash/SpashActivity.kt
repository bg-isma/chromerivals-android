package com.ismita.chromerivals.presenntation.ui.splash

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ismita.chromerivals.databinding.ActivitySpashBinding
import android.content.Intent
import android.os.Handler
import com.ismita.chromerivals.presenntation.ui.main.MainNavigationActivity

class SpashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySpashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            openActivity()
        }, 3000)

    }

    private fun openActivity() {
        val i = Intent(baseContext, MainNavigationActivity::class.java)
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }


}