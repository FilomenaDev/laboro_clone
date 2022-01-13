package com.filomenadeveloper.laboro_new_version.ui.splash

import androidx.lifecycle.ViewModel
import java.util.*

class SplahsViewModel : ViewModel() {

    fun splashTime(delay: Long, callback: () -> Unit) {
        Timer().schedule(object : TimerTask() {
            override fun run() = callback()
        }, delay)
    }
}