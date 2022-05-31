package com.example.keno

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.keno.base.FragmentBase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        //val navController = navHostFragment?.navController

        //navController?.currentBackStackEntry?.destination?.id
        val fragments = navHostFragment?.childFragmentManager?.fragments

        fragments?.lastOrNull()?.apply {
            if (this is FragmentBase &&
                !this.onBackPressed()
            ) {
                super.onBackPressed()
            }
        }
    }
}
