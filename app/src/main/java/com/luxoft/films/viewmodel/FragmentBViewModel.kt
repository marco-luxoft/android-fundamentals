package com.luxoft.films.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxoft.films.dto.Person

class FragmentBViewModel : ViewModel() {

    init {
        print("Constructor")
    }

    private var myLiveData: MutableLiveData<Person>? =  MutableLiveData()
    private var count: Int = 0

    fun updateData() {
        val person = Person()
        person.age = count++
        person.name = "Android"
        myLiveData?.value = person
    }

    fun getLiveData(): LiveData<Person>? {
        return myLiveData
    }

    override fun onCleared() {
        Person.myJavaFunction()
        count = 0
        super.onCleared()
    }
}