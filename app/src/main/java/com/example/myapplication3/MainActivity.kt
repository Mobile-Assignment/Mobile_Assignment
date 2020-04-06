package com.example.myapplication3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener{
            val intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
        }

        sign_up.setOnClickListener{
            val intent = Intent(this,SignUpScreen::class.java)
            startActivity(intent)
        }
    }
}
