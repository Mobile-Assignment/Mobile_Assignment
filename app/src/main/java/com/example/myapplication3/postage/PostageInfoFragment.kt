package com.example.myapplication3.postage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication3.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_postage_info.*


class PostageInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_postage_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        volumetric_.setOnClickListener{
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
        }
        showinfo.setOnClickListener {
                val uid = FirebaseAuth.getInstance().currentUser!!.uid
                val rootRef = FirebaseDatabase.getInstance().reference
                val uidRef = rootRef.child("users").child(uid)
                val eventListener: ValueEventListener = object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val propName = dataSnapshot.child("name").value.toString()
                        val propPhone = dataSnapshot.child("phone").value.toString()
                        recipient_n.text= propName
                        phone_number.text = propPhone
                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                }
                uidRef.addListenerForSingleValueEvent(eventListener)
            }
            save_as_dra.setOnClickListener {
/*                val city = city_town.text.toString().trim()
                val  state = stateinfo.text.toString().trim()
                val postcode = postcodeinfo.text.toString().trim()
                val streetadd= street_address.text.toString().trim()*/
                if(street_address.text.toString().trim().isEmpty()){
                    street_address.error="Stress Address Required"
                    street_address.requestFocus()
                    return@setOnClickListener
                }
                if(city_town.text.toString().isEmpty()){
                    city_town.error="City/Town Required"
                    city_town.requestFocus()
                    return@setOnClickListener
                }
                if( stateinfo.text.toString().trim().isEmpty()){
                    stateinfo.error="State Required"
                    stateinfo.requestFocus()
                    return@setOnClickListener
                }
                if(postcodeinfo.text.toString().trim().isEmpty()){
                    postcodeinfo.error="Postcode Required"
                    postcodeinfo.requestFocus()
                    return@setOnClickListener
                }
                saveToDatabase()
                val action=PostageInfoFragmentDirections.actionPostageCost()
                Navigation.findNavController(it).navigate(action)

            }
        }
        private fun saveToDatabase() {
            val uid=FirebaseAuth.getInstance().uid ?: ""
            val ref= FirebaseDatabase.getInstance().getReference("/postage/$uid")
            val user = mUser(
                uid,
                volumetric.text.toString(),
                phone_number.text.toString(),
                recipient_n.text.toString(),
                city_town.text.toString(),
                stateinfo.text.toString(),
                postcodeinfo.text.toString(),
                street_address.text.toString()
            )
            ref.setValue(user)
        }
}
class mUser(val uid:String, val volumetric:String, val phone:String, val name:String, val city:String,
           val state:String, val postcode: String, val street:String)
