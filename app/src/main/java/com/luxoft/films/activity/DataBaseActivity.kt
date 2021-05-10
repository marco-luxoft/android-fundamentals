package com.luxoft.films.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.luxoft.films.R
import com.luxoft.films.db.AppDatabase
import com.luxoft.films.db.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataBaseActivity : AppCompatActivity() {
    private var count: Int = 23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        val btnInsertDB = findViewById<Button>(R.id.btn_insert_in_db)
        val btnReadDB = findViewById<Button>(R.id.btn_read_db)
        val textViewPerson = findViewById<TextView>(R.id.txt_person_db)
        val db = AppDatabase(this)
        btnInsertDB.setOnClickListener {
            lifecycleScope.launch {
                val localCount = count++
                val person = PersonEntity("Android_$localCount", localCount)
                withContext(Dispatchers.IO) {
                    db.personDao().insertAll(person)
                }
                Toast.makeText(it.context, person.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        btnReadDB.setOnClickListener {
            lifecycleScope.launch {
                val listOfPeople = withContext(Dispatchers.IO) {
                    val people = db.personDao().getAll()
                    people.forEach {
                        println(it)
                    }
                    people.toString() //converts to string
                }
                textViewPerson.text = "People : $listOfPeople"
            }

        }
    }
}