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
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_postage_confirm.*
import kotlinx.android.synthetic.main.fragment_postage_confirm.storecitytown
import kotlinx.android.synthetic.main.fragment_postage_confirm.storepostcode
import kotlinx.android.synthetic.main.fragment_postage_confirm.storestate
import kotlinx.android.synthetic.main.fragment_postage_confirm.storestreet
import kotlinx.android.synthetic.main.fragment_postage_cost.*
import java.util.*


class PostageConfirmFragment : Fragment() {
    companion object {
        private val ALLOWED_CHARACTERS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_postage_confirm, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val suid = FirebaseAuth.getInstance().currentUser!!.uid
        val srootRef = FirebaseDatabase.getInstance().reference
        val suidRef = srootRef.child("postage").child(suid)
        val seventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val propNameTxt = dataSnapshot.child("volumetric").value.toString()
                val propCity = dataSnapshot.child("city").value.toString()
                val propState = dataSnapshot.child("state").value.toString()
                val propStreet = dataSnapshot.child("street").value.toString()
                val propPostcode = dataSnapshot.child("postcode").value.toString()
                val propCost = dataSnapshot.child("postagecost").value.toString()
                val propRename = dataSnapshot.child("recipientname").value.toString()
                val propRephone = dataSnapshot.child("recipientphone").value.toString()
                storevolumetric.text = propNameTxt
                storecitytown.text = propCity
                storestate.text= propState
                storestreet.text = propStreet
                storepostcode.text=propPostcode
                storecost.text=propCost
                storeName.text=propRename
                storeNumber.text=propRephone
                val propPostageService = dataSnapshot.child("postageservice").value.toString()
                your_item_h.text =propPostageService


            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        suidRef.addListenerForSingleValueEvent(seventListener)

        /*val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val rootRef = FirebaseDatabase.getInstance().reference
        val uidRef = rootRef.child("users").child(uid)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val propName = dataSnapshot.child("name").value.toString()
                val propPhone = dataSnapshot.child("phone").value.toString()
                storeName.text= propName
                storeNumber.text = propPhone
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        }
        uidRef.addListenerForSingleValueEvent(eventListener)*/

        generatenumber.setOnClickListener {
            getRandomString(16)
            try{
                val encoder=BarcodeEncoder()
                val bitmap = encoder.encodeBitmap(here_is_you.text.toString(), BarcodeFormat.QR_CODE,
                500,500)
                image_qr.setImageBitmap(bitmap)
            }catch (e:Exception){
                e.printStackTrace()
            }
            saveToDatabase()
        }
    }

    private fun getRandomString(sizeOfRandomString: Int): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        here_is_you.text = sb
        return sb.toString()
    }
    private fun saveToDatabase() {
        val uid=FirebaseAuth.getInstance().uid ?: ""
        val ref= FirebaseDatabase.getInstance().getReference("/postage/$uid")
        val user = EUser(
            uid,
            storevolumetric.text.toString(),
            storeNumber.text.toString(),
            storeName.text.toString(),
            storecitytown.text.toString(),
            storestate.text.toString(),
            storepostcode.text.toString(),
            storestreet.text.toString(),
            storecost.text.toString(),
            your_item_h.text.toString(),
            here_is_you.text.toString()
        )
        ref.setValue(user)
    }


}
class EUser(val uid:String, val volumetric:String, val recipientphone:String, val recipientname:String, val city:String,
            val state:String, val postcode: String, val street:String, val postagecost:String, val postageservice:String, val referenceid:String)