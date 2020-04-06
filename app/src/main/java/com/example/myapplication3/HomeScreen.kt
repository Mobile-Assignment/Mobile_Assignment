package com.example.myapplication3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.home_screen.*


class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        sign_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(baseContext, "Logout Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
/*    private fun out(){
        FirebaseAuth.getInstance().signOut()

    }*/

}