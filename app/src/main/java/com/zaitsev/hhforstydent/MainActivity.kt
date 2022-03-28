package com.zaitsev.hhforstydent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zaitsev.hhforstydent.databinding.ActivityMainBinding
import com.zaitsev.hhforstydent.feature.activity.AuthActivity
import com.zaitsev.hhforstydent.utils.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    lateinit var toolbar: MaterialToolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentNavigate) as NavHostFragment
        navController = navHostFragment.navController
        toolbar = binding.mainToolbar
        bottomNavigationView = binding.mainBottomNav
        initFirebase()
        initFunc()
        setupBottomNavigationView()
        setupToolbar()
    }

    //Initial functions
    private fun initFunc() {
        if (AUTH.currentUser != null) {
            Log.d("TAG", AUTH.currentUser.toString())
        } else {
            replaceActivity(AuthActivity())
        }
    }

    private fun setupBottomNavigationView(){
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}