package com.luxoft.films.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.luxoft.films.BuildConfig
import com.luxoft.films.R
import com.luxoft.films.activity.PermissionsActivity.Constants.MY_STATIC_VAL


class PermissionsActivity : AppCompatActivity() {

    private val TAG = "PermissionDemo"
    private val RECORD_REQUEST_CODE = 101

    object Constants {
        const val MY_STATIC_VAL = 101
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        val btn = findViewById<Button>(R.id.btn_request_permission)
        btn.setOnClickListener {
            setupPermissions()
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {

        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    //Log.i(TAG, "Permission has been denied by user")

                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.RECORD_AUDIO
                        )
                    ) {
                        //
                        Log.i(TAG, "now, user has denied permission (but not permanently!)")
                    } else {
                        Log.i(TAG, "now, user has denied permission permanently!")
                        showDialog()
                    }

                } else {
                    Log.i(TAG, "Permission has been granted by user")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle(getString(R.string.title_permissions))
            setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    Log.i(TAG, "OK")
                    startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                        )
                    )
                })
            setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    Log.i(TAG, "Cancel")
                })
        }
        builder.create()
        builder.show()
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun neverAskAgainSelected(activity: Activity, permission: String?): Boolean {
        val prevShouldShowStatus: Boolean = getRatinaleDisplayStatus(activity, permission)
        val currShouldShowStatus = activity.shouldShowRequestPermissionRationale(permission!!)
        return prevShouldShowStatus != currShouldShowStatus
    }

    fun getRatinaleDisplayStatus(context: Context, permission: String?): Boolean {
        val genPrefs: SharedPreferences =
            context.getSharedPreferences("GENERIC_PREFERENCES", Context.MODE_PRIVATE)
        return genPrefs.getBoolean(permission, false)
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest()
        } else {
            print("Permission granted!")
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            MY_STATIC_VAL
        )
    }
}