package com.lfss.pgiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lfss.pgiapp.createoccurrence.CreateOccurrenceFragment
import com.lfss.pgiapp.listoccurrences.ListOccurrencesFragment
import com.lfss.pgiapp.qrcodescanner.QrCodeScannerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        val createOccurrenceFragment = CreateOccurrenceFragment()
        val listOccurrencesFragment = ListOccurrencesFragment()
        val qrCodeScannerFragment = QrCodeScannerFragment()

        setCurrentFragment(createOccurrenceFragment)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.create_occurrence -> setCurrentFragment(createOccurrenceFragment)
                R.id.list_occurrences -> setCurrentFragment(listOccurrencesFragment)
                R.id.qr_code -> setCurrentFragment(qrCodeScannerFragment)
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