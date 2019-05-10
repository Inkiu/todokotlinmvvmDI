package com.example.todo_kotlin_mvvm_dagger.domain.repo

import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Single

interface ITaskRepository {
    fun loadTasks(): Single<List<Task>>
    fun saveTasks(tasks: List<Task>): Completable
}