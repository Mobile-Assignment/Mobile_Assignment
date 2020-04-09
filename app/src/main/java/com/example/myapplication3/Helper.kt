package com.example.myapplication3

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message: String) =
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

fun Context.logout() {
    val intent = Intent(this,MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
    }
