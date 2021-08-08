package com.hyperelement.mvvmdemo.ui.fragment.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.arch.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

//Fragment to select the city via map
class FragmentCitySelection :
    BaseFragment<CityVM>(R.layout.fragment_maps, CityVM::class) {
    val viewModel1 by viewModel<CityVM>()

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(23.022505, 72.571365)
        googleMap.addMarker(
            MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true)
        )

        //handle the drag of marker
        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragEnd(p0: Marker?) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(p0?.position));

                LocationAddress.getAddressFromLocation(
                    p0!!.position.latitude, p0!!.position.longitude,
                    context, GeocoderHandler()
                )
            }

            override fun onMarkerDragStart(p0: Marker?) {
            }

            override fun onMarkerDrag(p0: Marker?) {
            }
        })

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f));

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            getString(R.string.lbl_msg_map), Snackbar.LENGTH_LONG
        ).show()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

    }

    inner class GeocoderHandler : Handler() {
        override fun handleMessage(@SuppressLint("HandlerLeak") message: Message?) {
            val locationAddress: String?
            when (message?.what) {
                1 -> {
                    val bundle = message?.data
                    val loc = bundle.getString("address")
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        "$loc Added", Snackbar.LENGTH_LONG
                    ).show()
                    viewModel1.insertCityIntoDB(loc!!)
                    findNavController().popBackStack()
                }
                else -> {
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        getString(R.string.lbl_err_msg), Snackbar.LENGTH_LONG
                    ).show()
                    Timber.e("null")
                }
            }
        }
    }
}