package com.example.todo_kotlin_mvvm_dagger.domain.model

data class Task(
    val uuid: Long,
    val title: String,
    val description: String,
    val completed: Boolean
)