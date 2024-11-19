package com.example.laba_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MockLiveData<T>(private val data: T) : LiveData<T>() {
    init {
        postValue(data)
    }
}

