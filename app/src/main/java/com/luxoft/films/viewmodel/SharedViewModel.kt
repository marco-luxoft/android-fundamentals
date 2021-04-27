package com.luxoft.films.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    private var name: MutableLiveData<String>? = null

    fun setNameData(nameData: String) {
        name?.value = nameData
    }

    fun getNameData(): LiveData<String>? {
        if (name == null) {
            name = MutableLiveData()
        }
        return name
    }

    override fun onCleared() {
        super.onCleared()
    }
}