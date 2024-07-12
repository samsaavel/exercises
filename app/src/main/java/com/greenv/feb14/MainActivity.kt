package com.greenv.feb14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.greenv.feb14.databinding.ActivityMainBinding
import com.greenv.feb14.ui.fragments.BottomSheetFragment
import com.greenv.feb14.ui.fragments.RaMFragment
import com.greenv.feb14.ui.fragments.CounterFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation = binding.bottomNavigation
        navController()
        //load the initial fragment
        if (savedInstanceState == null)
            loadFragment(CounterFragment())
    }

    private fun navController() {
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ram -> {
                    loadFragment(CounterFragment())
                    true
                }

                R.id.random -> {
                    loadFragment(RaMFragment())
                    true
                }

                R.id.third -> {
                    loadFragment(BottomSheetFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}