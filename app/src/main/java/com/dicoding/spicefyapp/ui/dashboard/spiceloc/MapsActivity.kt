package com.dicoding.spicefyapp.ui.dashboard.spiceloc

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.dicoding.spicefyapp.R
import com.dicoding.spicefyapp.databinding.ActivityMapsBinding
import com.dicoding.spicefyapp.data.model.FakeSpiceData
import com.dicoding.spicefyapp.data.model.SpiceModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private var clickedSpice: SpiceModel? = null
    private var mList = ArrayList<SpiceModel>()
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        addManyMarker()
    }

    private fun addManyMarker() {
        mList.addAll(FakeSpiceData.spice)

        mList.forEach { spice ->
            val latlng = LatLng(spice.lat, spice.lot)
            mMap.addMarker(MarkerOptions()
                .position(latlng)
                .title(spice.name)
                .snippet(spice.description))?.setIcon(vectorToBitmap(R.drawable.ic_logo))
//            marker?.tag = spice
        }
        mMap.setOnMarkerClickListener { marker ->
            clickedSpice = marker.tag as? SpiceModel // Mendapatkan objek Spice dari tag marker
//            showDetailBottomSheet(clickedSpice)
            true
        }

        mMap.setOnMapClickListener(this)
    }

    private fun showDetailBottomSheet(spice: SpiceModel?) {
        val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.item_detailmaps_bottomsheet,
            findViewById<LinearLayout>(R.id.bottomSheet)
        )

        // Isi data pada BottomSheet sesuai dengan objek Spice
        val imageTempatView = bottomSheetView.findViewById<ImageView>(R.id.ivDMImage)
        val namaTempatTextView = bottomSheetView.findViewById<TextView>(R.id.tvDMName)
        val deskripsiTempatTextView = bottomSheetView.findViewById<TextView>(R.id.tvDMDesc)

        if (spice != null) {
            namaTempatTextView.text = spice.name
            deskripsiTempatTextView.text = spice.description
            imageTempatView.setImageResource(spice.picture)

        }

        bottomSheetView.findViewById<View>(R.id.btn_close).setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    override fun onMapClick(m: LatLng) {
        // Reset clickedSpice saat peta diklik
        clickedSpice = null
        showDetailBottomSheet(clickedSpice)

    }

    private fun vectorToBitmap(@DrawableRes id: Int): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            Log.e("BitmapHelper", "Resource not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}