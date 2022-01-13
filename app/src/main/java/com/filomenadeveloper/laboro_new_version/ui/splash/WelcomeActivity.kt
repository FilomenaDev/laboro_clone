package com.filomenadeveloper.laboro_new_version.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.authentication.Screen_Login
import com.filomenadeveloper.laboro_new_version.ui.authentication.Screen_registro

class WelcomeActivity : AppCompatActivity() {
    private lateinit var buttonRegisto: AppCompatButton
    private lateinit var buttonLogin: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        buttonLogin = findViewById(R.id.btnOfScreenLogin)
        buttonRegisto = findViewById(R.id.btnOfScreenRgistro)

        buttonRegisto.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@WelcomeActivity,Screen_registro::class.java))
        })

        buttonLogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@WelcomeActivity,Screen_Login::class.java))
        })
    }
}