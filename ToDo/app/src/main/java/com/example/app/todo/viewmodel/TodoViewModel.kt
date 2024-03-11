package com.example.app.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.todo.model.Task
import com.example.app.todo.model.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        getTasks()
//        _tasks.value = listOf(Task(1, 1,"Task 1", true), Task(1, 2,"Task 2", false))
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val tasks = TaskRepository.getInstance()
                _tasks.value = tasks.getTasks()     // String list of task's title:  .map { it.title }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}