package com.luxoft.films.poc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyBackgroundTask() {

    suspend fun doWorkOnBackground () : String {

        val result = withContext(Dispatchers.IO) {
            //Working on background thread
            delay(5000)
            "Hello world!"
        }
        return  result
    }
}
