package com.example.myapplication3.contactus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.fragment_contact_us.*


class ContactUsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendmailto.setOnClickListener {
            val action = ContactUsFragmentDirections.actionToSendEmail()
            Navigation.findNavController(it).navigate(action )
        }
        sendphoneto.setOnClickListener{

            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:03-41450123")
            startActivity(callIntent)
        }
    }

}