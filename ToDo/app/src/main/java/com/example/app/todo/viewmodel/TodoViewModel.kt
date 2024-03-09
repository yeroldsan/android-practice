package com.example.app.todo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel: ViewModel() {
    private val _tasks = MutableStateFlow<List<String>>(emptyList())
    val tasks: StateFlow<List<String>> = _tasks

    init {
        _tasks.value = listOf("Task 1", "Task 2", "Task 3")
    }
}