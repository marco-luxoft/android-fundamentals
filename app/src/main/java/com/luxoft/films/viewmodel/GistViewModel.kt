package com.luxoft.films.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luxoft.films.dto.Gist
import com.luxoft.films.repository.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GistViewModel : ViewModel() {

    private var job: Job? = null
    private var gistsLiveData: MutableLiveData<List<Gist>>? = null
    private val errorsLiveData: MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE) { // Default lazy(LazyThreadSafetyMode.SYNCHRONIZED)
        MutableLiveData<String>()
    }

    private val updateListLiveData: MutableLiveData<Int> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Int>()
    }

    fun getGists(): LiveData<List<Gist>>? {
        if (gistsLiveData == null) {
            gistsLiveData = MutableLiveData()
        }
        loadGists()
        return gistsLiveData
    }

    fun getErrorLiveData() : LiveData<String> = errorsLiveData
    fun getListPositionLiveData() : LiveData<Int> = updateListLiveData
    fun setListPosition(position : Int) {
        updateListLiveData.value = position
    }

    private fun loadGists() {
        val service = RetrofitFactory.makeRetrofitService()
        job = viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                service.getGists()
            }
            if (response.isSuccessful) {
                gistsLiveData?.value = response.body()
            } else {
                errorsLiveData.value = "Error"
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}