package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sign_up_screen.*


class SignUpScreen : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.sign_up_screen)

            already_hav.setOnClickListener{
                val intent = Intent(this,LoginScreen::class.java)
                startActivity(intent)
            }

            register.setOnClickListener{
                val intent = Intent(this,LoginScreen::class.java)
                startActivity(intent)
            }
        }
    }