package com.example.app.bmimvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BmiViewModel: ViewModel() {
    private val _height = MutableStateFlow("")
    private val _weight = MutableStateFlow("")
    private val _result = MutableStateFlow(0f)

    val height: StateFlow<String> = _height
    val weight: StateFlow<String> = _weight
    val result: StateFlow<Float> = _result

    fun setHeight(height: String) {
        viewModelScope.launch {
            _height.value = height
        }
    }
    fun setWeight(weight: String) {
        viewModelScope.launch {
            _weight.value = weight
        }
    }
    fun calculateBmi() {
        val height = _height.value.toFloatOrNull() ?: 0f
        val weight = _weight.value.toFloatOrNull() ?: 0f
        viewModelScope.launch {
            _result.value =
                if (height > 0f && weight > 0f) {
                    weight / ((height / 100) * (height / 100))
                } else {
                    0f
                }
        }
    }
}