package com.lfss.pgiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        val createOccurrenceFragment = CreateOccurrenceFragment()
        val listOccurrencesFragment = ListOccurrencesFragment()
        val qrCodeFragment = QrCodeFragment()

        setCurrentFragment(createOccurrenceFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.create_occurrence -> setCurrentFragment(createOccurrenceFragment)
                R.id.list_occurrences -> setCurrentFragment(listOccurrencesFragment)
                R.id.qr_code -> setCurrentFragment(qrCodeFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.menu_frame_layout, fragment)
            commit()
        }
}