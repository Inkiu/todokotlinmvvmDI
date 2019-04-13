package com.example.todo_kotlin_mvvm_dagger.domain.repo

import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import io.reactivex.Single

interface ITaskRepository {
    fun loadTasks(): Single<List<Task>>
}