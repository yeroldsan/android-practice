package com.example.app.todo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.todo.viewmodel.TodoViewModel

@Composable
fun TaskListScreen(vModel: TodoViewModel = viewModel()) {
    val tasks by vModel.tasks.collectAsState()
    TaskList(tasks)
}

@Preview
@Composable
fun TaskListPreview() {
    TaskListScreen()
}

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(tasks) { task ->
            TaskCard(task)
        }
    }
}

@Composable
fun TaskCard(task: String) {
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = task,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
