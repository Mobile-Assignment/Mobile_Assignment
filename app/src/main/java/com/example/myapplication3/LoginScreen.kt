package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_screen.*


class LoginScreen : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.login_screen)

            sign_in.setOnClickListener{
                val intent = Intent(this,HomeScreen::class.java)
                startActivity(intent)
            }

            forgot_pass.setOnClickListener{
                val intent = Intent(this,ResetPassword::class.java)
                startActivity(intent)
            }
        }
    }
