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
import com.luxoft.films.poc.AlarmBroadcastReceiver
import com.luxoft.films.poc.AirPlaneBroadcastReceiver


class BroadcastActivity : AppCompatActivity() {

    private var broadcastReceiver: BroadcastReceiver? = null
    private var alarmBroadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        val editText = findViewById<EditText>(R.id.edit_text_second)
        val btn = findViewById<Button>(R.id.btn_seconds)

        btn.setOnClickListener {

            val i: Int = editText.text.toString().toInt()
            val intent = Intent(this, AlarmBroadcastReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 234324243, intent, 0)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager[AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + i * 1000] = pendingIntent
            Toast.makeText(
                this, "Alarm set in $i seconds",
                Toast.LENGTH_LONG
            ).show()

        }

        broadcastReceiver = AirPlaneBroadcastReceiver()
        alarmBroadcastReceiver = AlarmBroadcastReceiver()

        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(broadcastReceiver, filter)
    }

    override fun onDestroy() {
        broadcastReceiver?.let { unregisterReceiver(it) }
        alarmBroadcastReceiver?.let { unregisterReceiver(it) }
        super.onDestroy()
    }

    /*
          val receiver = ComponentName(this, AlarmBroadcastReceiver::class.java)
        val pm: PackageManager = packageManager

        pm.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
     */
}