package com.example.scanner.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scanner.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartsViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {
    private val _chartData = MutableLiveData<List<Float>>()
    val chartData: LiveData<List<Float>> get() = _chartData

    fun generateChartData() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.generateChartData()
            _chartData.postValue(data)
        }
    }
}