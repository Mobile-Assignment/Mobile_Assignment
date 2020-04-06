package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        login.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,LoginScreen::class.java))
            finish()
        })


        sign_up.setOnClickListener{
            val intent = Intent(this,SignUpScreen::class.java)
            startActivity(intent)
        }

    }
}
