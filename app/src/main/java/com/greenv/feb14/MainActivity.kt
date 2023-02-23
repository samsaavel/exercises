package com.greenv.feb14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.greenv.feb14.ui.fragments.RaMFragment
import com.greenv.feb14.ui.fragments.ServiceFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RaMFragment()).commit()
        navController()
    }
    
    private fun navController() {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    loadFragment(RaMFragment())
                    true
                }
                R.id.page_2 -> {
                    loadFragment(ServiceFragment())
                    true
                }
                else -> {
               loadFragment(RaMFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}