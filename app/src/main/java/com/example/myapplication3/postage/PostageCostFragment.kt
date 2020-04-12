package com.example.myapplication3.postage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication3.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_postage_cost.*



class PostageCostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_postage_cost, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val rootRef = FirebaseDatabase.getInstance().reference
            val uidRef = rootRef.child("postage").child(uid)
            val eventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val propNameTxt = dataSnapshot.child("volumetric").value.toString()
                    volumetric.text = propNameTxt
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            }
            uidRef.addListenerForSingleValueEvent(eventListener)

        postage_show.setOnClickListener {
            if (input_volume.text.toString().trim().isEmpty()) {
                input_volume.error = "Info Required"
                input_volume.requestFocus()
                return@setOnClickListener
            }
            doCalculation()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun doCalculation(){
        cost_result.text = "Postage Cost: RM " + (input_volume.text.toString().toDouble() * 0.8 )
    }


}
