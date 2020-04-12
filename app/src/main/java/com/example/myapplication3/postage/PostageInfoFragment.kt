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


        val suid = FirebaseAuth.getInstance().currentUser!!.uid
        val srootRef = FirebaseDatabase.getInstance().reference
        val suidRef = srootRef.child("postage").child(suid)
        val seventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val propNameTxt = dataSnapshot.child("volumetric").value.toString()
                volumetric.text = propNameTxt
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        suidRef.addListenerForSingleValueEvent(seventListener)


/*                val uid = FirebaseAuth.getInstance().currentUser!!.uid
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
                uidRef.addListenerForSingleValueEvent(eventListener)*/

            save_as_dra.setOnClickListener {
                if(recipient_n.text.toString().trim().isEmpty()){
                    recipient_n.error="Recipient Name Required"
                    recipient_n.requestFocus()
                    return@setOnClickListener
                }
                if(phone_number.text.toString().trim().isEmpty()) {
                    phone_number.error="Recipient Phone Number Required"
                    phone_number.requestFocus()
                    return@setOnClickListener
                }
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
            val user = MUser(
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
class MUser(val uid:String, val volumetric:String, val recipientphone:String, val recipientname:String, val city:String,
           val state:String, val postcode: String, val street:String)
