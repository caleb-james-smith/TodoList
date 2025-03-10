package com.example.todolist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.model.Task
import com.example.todolist.ui.theme.*

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    var taskText by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }

    /*val tasks = remember { mutableStateListOf(
        Task("Email Andrew", false),
        Task("Email Brandon", false),
        Task("Email Caleb", false),
        Task("Buy apples", false),
        Task("Buy bananas", false),
        Task("Buy cucumbers", false),
        Task("Complete project 1", false),
        Task("Complete project 2", false),
        Task("Complete project 3", false),
        Task("Complete project 4", false),
        Task("Complete project 5", false),
        Task("Complete project 6", false),
        Task("Complete project 7", false),
        Task("Complete project 8", false),
        Task("Complete project 9", false),
        Task("Complete project 10", false)
    ) }*/

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("TaskMaster", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = TitleBlue
                )
            )
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                // Text field for a new task
                OutlinedTextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    label = { Text("Enter task") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Row to contain buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Button to add a new task
                    Button(
                        onClick = {
                            if (taskText.isNotBlank()) {
                                tasks.add(Task(taskText, false))
                                taskText = ""
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AddGreen,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Add Task")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Button to remote all completed tasks
                    Button(
                        onClick = { tasks.removeAll { it.isCompleted } },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RemoveRed,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Remove Tasks")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Display list of tasks
                TaskList(tasks = tasks) { index, isChecked ->
                    tasks[index] = tasks[index].copy(isCompleted = isChecked)
                }
            }
        }
    )
}
