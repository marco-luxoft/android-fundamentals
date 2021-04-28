package com.luxoft.films.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.*
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.luxoft.films.R
import com.luxoft.films.activity.FirstActivity.Constants.GLOBAL_KEY

class FirstActivity : AppCompatActivity() {

    object Constants {
        const val GLOBAL_KEY = "global"
    }

    private var imageCamera: ImageView? = null
    private var myString : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        if(savedInstanceState == null) {
            myString = "My string"
        } else {
            myString = savedInstanceState.getString(GLOBAL_KEY)
        }

        val editText = findViewById<EditText>(R.id.edit_message)
        val btn = findViewById<Button>(R.id.btn_send_message)
        val btnBrowser = findViewById<Button>(R.id.btn_open_browser)
        val btnCamera = findViewById<Button>(R.id.btn_camera)
        imageCamera = findViewById<ImageView>(R.id.img_camera)

        btn.setOnClickListener {
            val message = editText.text.toString()
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_MESSAGE", message)
            }
            val myBundle = Bundle()
            myBundle.putString("string_key", "test")
            intent.putExtra("MY_BUNDLE", myBundle)

            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            //startActivity(intent)
            //startActivityForResult(intent, CODE)
            startForResult.launch(intent)
        }

        btnBrowser.setOnClickListener {
            try {
                val intent = Intent(ACTION_VIEW, Uri.parse("https://www.luxoft.com/")).apply {
                    // The URL should either launch directly in a non-browser app (if it's
                    // the default), or in the disambiguation dialog.
                    addCategory(CATEGORY_BROWSABLE)
                }
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Only browser apps are available, or a browser is the default.
                // So you can open the URL directly in your app, for example in a
                // Custom Tab.
                e.printStackTrace()
            }
        }

        btnCamera.setOnClickListener {
            try {
                val intent = Intent(ACTION_IMAGE_CAPTURE)
                //startActivity(intent)
                startCameraForResult.launch(intent)
            } catch (e: ActivityNotFoundException) {
                // Only browser apps are available, or a browser is the default.
                // So you can open the URL directly in your app, for example in a
                // Custom Tab.
                e.printStackTrace()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(GLOBAL_KEY, myString)
        super.onSaveInstanceState(outState)
    }


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val resultFromAnotherActivity = intent?.getStringExtra("some_key")
            Toast.makeText(this, resultFromAnotherActivity, Toast.LENGTH_SHORT).show()
        }
    }

    private val startCameraForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imageCamera?.setImageBitmap(imageBitmap)
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE && resultCode == Activity.RESULT_OK) {
            val resultFromAnotherActivity = data?.getStringExtra("some_key")
            Toast.makeText(this, resultFromAnotherActivity, Toast.LENGTH_SHORT).show()
        }
    }*/

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

}