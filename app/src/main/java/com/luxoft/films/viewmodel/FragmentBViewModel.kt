package com.luxoft.films.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxoft.films.dto.PersonJavaTest

class FragmentBViewModel : ViewModel() {

    init {
        print("Constructor")
    }

    private var myLiveData: MutableLiveData<PersonJavaTest>? =  MutableLiveData()
    private var count: Int = 0

    fun updateData() {
        val person = PersonJavaTest()
        person.age = count++
        person.name = "Android"
        myLiveData?.value = person
    }

    fun getLiveData(): LiveData<PersonJavaTest>? {
        return myLiveData
    }

    override fun onCleared() {
        PersonJavaTest.myJavaFunction()
        count = 0
        super.onCleared()
    }
}