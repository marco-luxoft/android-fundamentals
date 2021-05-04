package com.luxoft.films.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luxoft.films.R
import com.luxoft.films.activity.BroadcastActivity.Constant.CONSTANT
import com.luxoft.films.poc.AirPlaneBroadcastReceiver
import com.luxoft.films.poc.AlarmBroadcastReceiver


class BroadcastActivity : AppCompatActivity() {

    object Constant {
        const val CONSTANT = 234324243
    }

    private var airPlaneBroadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        val editText = findViewById<EditText>(R.id.edit_text_second)
        val btn = findViewById<Button>(R.id.btn_seconds)

        btn.setOnClickListener {

            val i: Int = editText.text.toString().toInt()
            val intent = Intent(this, AlarmBroadcastReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, CONSTANT, intent, 0)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + i * 1000] = pendingIntent
            Toast.makeText(
                this, "Alarm set in $i seconds",
                Toast.LENGTH_LONG
            ).show()
        }

        airPlaneBroadcastReceiver = AirPlaneBroadcastReceiver()

        val airPlaneFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(airPlaneBroadcastReceiver, airPlaneFilter)
    }

    override fun onDestroy() {
        airPlaneBroadcastReceiver?.let { unregisterReceiver(it) }
        disableAlarmManager()
        super.onDestroy()
    }

    private fun disableAlarmManager() {
        val aManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(baseContext, AlarmBroadcastReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, CONSTANT, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        aManager.cancel(pIntent)
    }

}