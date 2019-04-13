package com.example.todo_kotlin_mvvm_dagger.domain.model

data class Task(
    val uuid: String,
    val title: String,
    val description: String,
    val completed: Boolean
)