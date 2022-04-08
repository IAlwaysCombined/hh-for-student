package com.zaitsev.hhforstydent.feature.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zaitsev.hhforstydent.R
import com.zaitsev.hhforstydent.databinding.ActivityMainBinding
import com.zaitsev.hhforstydent.databinding.ActivityPlaceBinding

class PlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceBinding

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    lateinit var toolbar: MaterialToolbar
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavigatePlace) as NavHostFragment
        navController = navHostFragment.navController
        toolbar = binding.placeToolbar
        bottomNavigationView = binding.placeBottomNav
        setupBottomNavigationView()
        setupToolbar()
    }

    private fun setupBottomNavigationView(){
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}