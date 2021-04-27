package com.luxoft.films.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.luxoft.films.R
import com.luxoft.films.activity.SharePreferenceActivity.PreferencesConstants.KEY_NAME
import com.luxoft.films.activity.SharePreferenceActivity.PreferencesConstants.PREFERENCE_FILE_NAME

class SharePreferenceActivity : AppCompatActivity() {

    object PreferencesConstants {
        const val PREFERENCE_FILE_NAME = "test_preference"
        const val KEY_NAME = "key_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preference)

        title = getString(R.string.title_preferences) //change action bar title
        supportActionBar?.subtitle = getString(R.string.subtitle_preferences)

        val txtPreferences = findViewById<TextView>(R.id.text_preference)
        val btnWritePreference = findViewById<Button>(R.id.btn_write_preference)
        val btnReadPreference = findViewById<Button>(R.id.btn_read_preference)
        val btnClearPreference = findViewById<Button>(R.id.btn_clear_preference)

        val sharedPref = getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE) //Get the preferences

        btnWritePreference.setOnClickListener {
            with(sharedPref.edit()) {
                putString(KEY_NAME, "Marco")
                apply()
            }
        }

        btnReadPreference.setOnClickListener {
            txtPreferences.text = sharedPref.getString(KEY_NAME, "unavailable")
        }

        btnClearPreference.setOnClickListener {
            with(sharedPref.edit()) {
                clear()
                apply()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.basic_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show() //TODO call strings(R.strings)
                true
            }
            R.id.menu_history -> {
                Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}