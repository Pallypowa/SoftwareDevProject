package com.example.studentcalendar

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() , OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    var locationManager: LocationManager? = null
    var locationListener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //define locationManager, locationListener
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            //tartózkodási hely változása esetén, kamera mozgatása
            override fun onLocationChanged(location: Location?) {
                if(location != null) {
                    var userLocation = LatLng(location!!.latitude, location!!.longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17f))
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }
            override fun onProviderEnabled(provider: String?) {
            }
            override fun onProviderDisabled(provider: String?) {
            }
        }

        //ha nincs, engedély kérése tartózkodási helyre
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        else {
            //2 percenként frissít
            locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2f, locationListener)
        }

        val jsonReader = JsonReader()
        jsonReader.getJsonDataFromAsset(applicationContext, "coords.json")
        var names = jsonReader.getMap().keys
        for(i in 0..jsonReader.getMap().size - 1){
            val curr = LatLng(jsonReader.getMap().getValue(names.elementAt(i)).first, jsonReader.getMap().getValue(names.elementAt(i)).second)
            mMap.addMarker(MarkerOptions()
                .position(curr)
                .title(names.elementAt(i) + " épület")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .alpha(0.7f)
            )
        }
        with(googleMap) {
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
            uiSettings.isCompassEnabled = true
        }


/*        // Add a marker in Veszprém and move the camera
        val vp = LatLng(47.088554, 17.906912)
        mMap.addMarker(
            MarkerOptions()
                .position(vp)
                .title("Átmeneti álló pozíció")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vp, 17f))

        val a = LatLng(47.088648, 17.908292)
        mMap.addMarker(
            MarkerOptions()
                .position(a)
                .title("A épület")
        )
*/
    }

    //ha már egyszer kaptunk engedélyt, update location
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.size > 0) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                //2 percenként frissít
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    2,
                    2f,
                    locationListener
                )
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
