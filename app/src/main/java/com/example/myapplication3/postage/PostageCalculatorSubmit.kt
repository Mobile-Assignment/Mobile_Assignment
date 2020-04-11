package com.example.myapplication3.postage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication3.R
import kotlinx.android.synthetic.main.postage_calculator_submit.*
import org.w3c.dom.Text


class PostageCalculatorSubmit : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.postage_calculator_submit, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        count.setOnClickListener {
     /*       val action = PostageCalculatorSubmitDirections.actionPostageCalculator()
            Navigation.findNavController(it).navigate(action)*/
            calculation()
        }
    }

    private fun calculation(){
        volumetric_result.text =
            "Volumetric Weight: " + (length_cm_.text.toString().toInt() * width_cm_.text.toString()
                .toInt() * height_cm_.text.toString().toInt() / 5000) +"kg"
        /*
        val length = R.id.length_cm_
        val height = R.id.height_cm_
        val width = R.id.width_cm_
        val result = R.id.volumetric_result*/
    }


}
