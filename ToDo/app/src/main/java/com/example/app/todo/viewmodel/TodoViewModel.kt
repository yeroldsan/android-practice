package com.example.app.todo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.todo.model.Task
import com.example.app.todo.model.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface TodoUiState {
    data class Success(val tasks: List<Task>): TodoUiState
    data object Error: TodoUiState
    data object Loading: TodoUiState
}
class TodoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState: StateFlow<TodoUiState> = _uiState

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val tasks = TaskRepository.getInstance()
                _uiState.value = TodoUiState.Success(tasks.getTasks())     // String list of task's title:  .map { it.title }
            } catch (e: Exception) {
                Log.d("TodoViewModel", e.message.toString())
                _uiState.value = TodoUiState.Error
            }
        }
    }
}