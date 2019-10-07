package com.example.amconsofttestproject.presentation.activities.second

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.amconsofttestproject.R
import com.example.amconsofttestproject.di.component.ViewModelComponent
import com.example.amconsofttestproject.domain.UserViewModel
import com.example.amconsofttestproject.presentation.base.BaseActivity
import com.example.amconsofttestproject.usecases.repository.database.entity.UserEntity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_second.*
import javax.inject.Inject

class DetailSecondActivity : BaseActivity(), OnMapReadyCallback {

    companion object {
        @JvmStatic
        fun newInstance(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailSecondActivity::class.java)
            intent.putExtra(context.getString(R.string.EXTRAS_ID), id)
            return intent
        }
    }

    var viewModel: UserViewModel? = null
        @Inject set

    private lateinit var map: GoogleMap
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_second)
        initViewModel()
        initUI()
    }

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
    }

    private fun initViewModel() {
        userId = intent.getIntExtra(getString(R.string.EXTRAS_ID), 0)
        viewModel?.getUser(userId)
        viewModel?.getLiveData()?.observe(this, Observer { it?.let { initUser(it) } })
    }

    private fun initUI() {
        var mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentGoogleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initUser(user: UserEntity) {
        tvDetailUserName.text = user.name
        tvDetailUserMail.text = user.email
        tvDetailUserPhone.text = user.phone
        tvDetailUserWebsite.text = user.website
        tvDetailAddressCity.text = user.address.city
        tvDetailAddressStreet.text = user.address.street
        tvDetailAddressSuite.text = user.address.suite
        tvDetailAddressZip.text = user.address.zipcode
        tvDetailCompanyName.text = user.company.name
        tvDetailCompanyBs.text = user.company.bs
        var latLng = LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
        initMarker(latLng, user.name)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3f))
    }

    private fun initMarker(latLng: LatLng, title: String): Marker {
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title(title)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        return this.map.addMarker(markerOptions)
    }
}

