package com.example.todo_kotlin_mvvm_dagger.domain.repo

import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import io.reactivex.Completable
import io.reactivex.Single

interface ITaskRepository {
    fun loadTasks(filter: TaskFilterType): Single<List<Task>>
    fun saveTasks(tasks: List<Task>): Completable
    fun completeTask(taskId: Long): Completable
    fun activeTask(taskId: Long): Completable
}