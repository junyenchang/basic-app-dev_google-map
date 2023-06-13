package com.example.googlemap

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val MINIMUM_DISTANCE_CHANGE_FOR_UPDATES: Long = 1  //設定超過一個距離(尺)他就會做更新location的動作
    private val MINIMUM_TIME_BETWEEN_UPDATES: Long = 1000 //設定超過一個時間(毫秒)他就會做更新location的動作
    protected var locationManager: LocationManager? = null
    var location1: Location? = null
    var total:Float = 0F


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    @SuppressLint("MissingPermission")
    private inner class MyLocationListener: LocationListener{
        override fun onLocationChanged(location: Location) {
            if (location1 == null) {
                location1 = location
            }
            val location2 = LatLng(location.latitude, location.longitude)
            mMap.addMarker(MarkerOptions().position(location2).title(""))    //addMarker加Mark
            mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(location2, 18F)
            )
            total += location.distanceTo(location1)
            location1 = location
            Toast.makeText(this@MapsActivity, total.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val IMF = LatLng(24.78846815015478, 120.99862845158995)
        mMap.addMarker(MarkerOptions().position(IMF).title("中正堂"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IMF, 18F))

        val ATM1 = LatLng(24.78815646012756, 120.99855871415558)
        mMap.addMarker(MarkerOptions().position(ATM1).title("玉山銀行").draggable(true).icon(BitmapDescriptorFactory.defaultMarker
        (BitmapDescriptorFactory.HUE_CYAN)))


        val ATM2 = LatLng(24.788171070615086, 120.99836023068848)
        mMap.addMarker(MarkerOptions().position(ATM2).title("中華郵政").draggable(true).icon(BitmapDescriptorFactory.defaultMarker
        (123f)).alpha(0.9f))

        val ATM3 = LatLng(24.789018475947206, 120.99939556336825)
        mMap.addMarker(MarkerOptions().position(ATM3).title("國泰世華").draggable(true).icon(BitmapDescriptorFactory.defaultMarker
        (23f)))

    }
}