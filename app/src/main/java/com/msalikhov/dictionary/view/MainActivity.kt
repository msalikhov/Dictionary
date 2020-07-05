package com.msalikhov.dictionary.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.msalikhov.dictionary.R
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.addOnBackStackChangedListener(this)
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackStackChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackStackChanged() {
        val hasBackStack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBackStack)
    }
}