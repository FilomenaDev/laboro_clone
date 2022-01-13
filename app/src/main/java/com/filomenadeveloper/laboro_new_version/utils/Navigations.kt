package com.filomenadeveloper.laboro_new_version.utils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.newScreenWithoutHistory(anyClass: Class<*>, bundle: Bundle?) {
    val intent = Intent(applicationContext, anyClass)
    intent.putExtras(bundle!!)
    startActivity(intent)
    finish()
}

fun AppCompatActivity.newScreenWithHistory(anyClass: Class<*>, bundle: Bundle?) {
    val intent = Intent(applicationContext, anyClass)
    intent.putExtras(bundle!!)
    startActivity(intent)
}