package com.epitech.vlcremote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
* Created by bonett_w on 1/29/18.
*/

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start the home activity

        val intent = Intent(this, HomeActivity::class.java)

        startActivity(intent)

        finish()
    }
}
