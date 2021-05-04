package com.luxoft.films.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.luxoft.films.R
import com.luxoft.films.poc.MyBackgroundTask
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class POCActivity : AppCompatActivity() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_o_c)
        val pocText = findViewById<TextView>(R.id.poc_text)
        val task = MyBackgroundTask() //Create my object

        job = lifecycleScope.launch {
            //Running on the UI/Main Thread
            print("Another logic")
            val result = task.doWorkOnBackground()
            //Update UI
            //Running on the UI/Main Thread
            pocText.text = result
        }
        print("Do something else")
    }

    override fun onDestroy() {
        job?.let {
            if(it.isActive) {
                it.cancel()
            }
        }
        super.onDestroy()
    }

}