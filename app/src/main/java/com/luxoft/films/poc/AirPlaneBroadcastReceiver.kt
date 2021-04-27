package com.luxoft.films.poc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneBroadcastReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeOn = intent?.getBooleanExtra("state", false) as Boolean
        if(isAirplaneModeOn) {
            //Do something else
            Toast.makeText(context, "Airplane mode $isAirplaneModeOn", Toast.LENGTH_SHORT).show()
        } else {
            //Do something else when turning off
            Toast.makeText(context, "Airplane mode $isAirplaneModeOn", Toast.LENGTH_SHORT).show()
        }
    }
}