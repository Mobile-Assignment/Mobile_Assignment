package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_screen.*
import kotlinx.android.synthetic.main.login_screen.*


class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        track_trace.setOnClickListener{
            val intent = Intent(this,PostageCalculator::class.java)
            startActivity(intent)
        }
    }
}