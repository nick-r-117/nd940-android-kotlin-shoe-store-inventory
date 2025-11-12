package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udacity.shoestore.models.NuxConstants
import timber.log.Timber
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        Timber.plant(Timber.DebugTree())

        // Setup our custom NUX
        val prefs = this.getSharedPreferences(NuxConstants.SHOE_STORE_NUX_PREFS, MODE_PRIVATE)
        prefs.edit { putBoolean(NuxConstants.HAS_USER_ONBOARDED, false) }
    }
}
