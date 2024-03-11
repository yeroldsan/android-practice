package com.example.app.todo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.app.todo.R
import com.example.app.todo.model.Task
import com.example.app.todo.viewmodel.TodoUiState

@Composable
fun TaskListScreen(innerPadding: PaddingValues, uiState: TodoUiState) {
    when (uiState) {
        is TodoUiState.Success -> TaskList(innerPadding,uiState.tasks)
        is TodoUiState.Loading -> LoadingScreen()
        is TodoUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "Loading",
        )
        Text(text = "Loading...")
    }
}

@Composable
fun ErrorScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(
            text = "Failed to load tasks. Please try again later.",
            modifier = Modifier.padding(16.dp)
        )
    }

}

@Composable
fun TaskList(innerPadding: PaddingValues, tasks: List<Task>) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(tasks) { task ->
            TaskCard(task)
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (task.completed) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface,
                textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
