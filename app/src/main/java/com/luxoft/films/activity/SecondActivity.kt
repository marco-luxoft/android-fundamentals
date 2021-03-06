package com.luxoft.films.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.luxoft.films.R


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val myBundle = intent.getBundleExtra("MY_BUNDLE")
        val nestedString = myBundle?.getString("string_key")
        val txtView = findViewById<TextView>(R.id.text_message)
        txtView.text = message
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                sendDataBack()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendDataBack() {
        val resultIntent = Intent()
        resultIntent.putExtra("some_key", "Hello world!")
        setResult(RESULT_OK, resultIntent)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        sendDataBack()
        super.onBackPressed()
    }
}