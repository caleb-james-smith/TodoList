package com.example.todolist.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.todolist.model.Task

@Composable
fun TaskList(tasks: List<Task>, onTaskChecked: (Int, Boolean) -> Unit) {
    LazyColumn {
        itemsIndexed(tasks) { index, task ->
            TaskItem(task = task, onCheckedChange = { isChecked ->
                onTaskChecked(index, isChecked)
            })
        }
    }
}
