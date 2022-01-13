package com.filomenadeveloper.laboro_new_version.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.authentication.Screen_Login
import com.filomenadeveloper.laboro_new_version.utils.newScreenWithoutHistory
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val splahsViewModel: SplahsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_splash)
    }

    override fun onStart() {
        super.onStart()
        splahsViewModel.run {
            splashTime(delay = 3000){
                newScreenWithoutHistory(
                    anyClass = WelcomeActivity::class.java,
                    bundle = Bundle()
                )
            }
        }
    }
}