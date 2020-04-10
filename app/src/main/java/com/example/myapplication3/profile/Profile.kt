package com.example.myapplication3.profile


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication3.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.profile_screen.*


class Profile : Fragment() {
    private val currentUser = FirebaseAuth.getInstance().currentUser


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUser?.let { user ->
            Glide.with(this)
                .load(user.photoUrl)
                .into(imageView5)
            account_id.text=user.uid
           email_id.text=user.email
        }

        history.setOnClickListener {
            view.findNavController().navigate(R.id.action_profile_to_historyList)
        }

        profile_set.setOnClickListener {
            view.findNavController().navigate(R.id.action_profile_to_profileSetting)
        }
    }
}