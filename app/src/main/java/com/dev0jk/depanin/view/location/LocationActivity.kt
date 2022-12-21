package com.dev0jk.depanin.view.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dev0jk.depanin.R
import com.dev0jk.depanin.databinding.ActivityLocationBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.type.LatLng

class LocationActivity : AppCompatActivity() , OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    lateinit var binding            : ActivityLocationBinding
    lateinit var supportMapFragment : SupportMapFragment
    lateinit var mGoogleMap         : GoogleMap
    lateinit var mLocation          : FusedLocationProviderClient


    private  var isPermissionGranted = false
    private  var isGPSenabled        = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getPermission()

        initMap()
        Places.initialize(applicationContext, "AIzaSyCN_5nQOhfOkRZprw2OaKkTO4mw7-SAWlo")
        var placesClient = Places.createClient(this)
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS)


        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                Log.e(TAG, "Place: ${place.name}, ${place.id}")
            }

            override fun onError(status: Status) {

                Log.i(TAG, "An error occurred: $status")
            }
        })

        mLocation = FusedLocationProviderClient(this)

        binding.btnMyLocation.setOnClickListener {
            getCurrLoc()
        }




    }
    @SuppressLint("MissingPermission")
    private fun getCurrLoc() {
       mLocation.lastLocation.addOnCompleteListener{
           if (it.isSuccessful){
               var location = it.result
               goToLocation(location.latitude, location.longitude)
           }
       }
    }

    private fun goToLocation(altitude: Double, longitude: Double) {

        var latLang = com.google.android.gms.maps.model.LatLng(altitude, longitude)

        var cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLang, 18F)

        mGoogleMap.moveCamera(cameraUpdate)
        mGoogleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    private fun initMap() {

        if (isPermissionGranted && ifGPSEnable()){
            supportMapFragment = supportFragmentManager.findFragmentById(R.id.map_view) as SupportMapFragment
            supportMapFragment.getMapAsync(this)
        }
    }


    fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.MANAGE_MEDIA
                    ),
                    200
                )
                isPermissionGranted = true
            }

            else
                isPermissionGranted = true
        }
    }

    fun ifGPSEnable() : Boolean{
        var verif = false
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        var providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (providerEnable){
           verif = true
        }
        return verif
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

        mGoogleMap = googleMap
        mGoogleMap.isMyLocationEnabled = true



    }

    override fun onConnected(p0: Bundle?) {
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

}