package com.luxoft.films.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.luxoft.films.R
import com.luxoft.films.poc.MyBackgroundTask
import kotlinx.coroutines.*

class POCActivity : AppCompatActivity() {

    private var job: Job? = null
    private var job2: Job? = null
    private val tag = "luxoft-android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_o_c)
        val pocText = findViewById<TextView>(R.id.poc_text)
        val coroutineText = findViewById<TextView>(R.id.txt_coroutine)
        val task = MyBackgroundTask() //Create my object

        job = lifecycleScope.launch {
            //Running on the UI/Main Thread
            print("Another logic")
            val result = task.doWorkOnBackground()
            //Update UI
            //Running on the UI/Main Thread
            pocText.text = result
        }

        Log.i(tag,"Do something else")

        job2 = lifecycleScope.launch {
            launch {
                Log.i(tag, "inside child 1")
                val text = withContext(Dispatchers.IO) {
                    delay(500)
                    "After 500 milliseconds"
                }
                coroutineText.text = text
            }
            launch {
                Log.i(tag,"inside child 2")
                val text = withContext(Dispatchers.IO) {
                    delay(1000)
                    "After 1 second"
                }
                coroutineText.text = text
            }
        }
        Log.i(tag,"Hey yo!")
    }

    override fun onDestroy() {
        job?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
        job2?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
        super.onDestroy()
    }

}