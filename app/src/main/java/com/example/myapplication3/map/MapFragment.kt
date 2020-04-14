package com.example.myapplication3.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myapplication3.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment(), OnMapReadyCallback{

    private lateinit var googleMap : GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        map_view.onCreate(savedInstanceState)
        map_view.onResume()

        map_view.getMapAsync(this)
    }
    override fun onMapReady(map: GoogleMap?) {
        map?.let{
            googleMap = it
            googleMap.isMyLocationEnabled = true
            val location = LatLng(3.215133, 101.728426)
            googleMap.addMarker((MarkerOptions().position(location).title("EZ SHIPPING HQ")))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

            val location1 = LatLng(5.415616, 100.322744)
            googleMap.addMarker((MarkerOptions().position(location1).title("PENANG BRANCH")))

            val location2 = LatLng(2.195947, 102.247380)
            googleMap.addMarker((MarkerOptions().position(location2).title("MALACCA BRANCH")))

            val location3 = LatLng(2.726522, 101.937697)
            googleMap.addMarker((MarkerOptions().position(location3).title("SEREMBAN BRANCH")))

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }
}
