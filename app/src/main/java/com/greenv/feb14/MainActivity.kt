package com.greenv.feb14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment()).commit()
        navController()
    }
    
    private fun navController() {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    loadFragment(FirstFragment())
                    true
                }
                R.id.page_2 -> {
                    loadFragment(SecondFragment())
                    true
                }
                else -> {
               loadFragment(FirstFragment())
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