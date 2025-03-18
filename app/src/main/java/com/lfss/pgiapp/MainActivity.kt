package com.lfss.pgiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val createOccurrenceFragment = CreateOccurrenceFragment()
        val listOccurrencesFragment = ListOccurrencesFragment()
        val qrCodeFragment = QrCodeFragment()

        setCurrentFragment(createOccurrenceFragment)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

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
            replace(R.id.flFragment, fragment)
            commit()
        }
}