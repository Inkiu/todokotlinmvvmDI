package com.example.todo_kotlin_mvvm_dagger.domain.repo

import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import io.reactivex.Completable
import io.reactivex.Single

interface ITaskRepository {
    fun loadTasks(filter: TaskFilterType): Single<List<Task>>
    fun loadTask(taskId: Long): Single<Task>

    fun saveTasks(tasks: List<Task>): Completable

    fun deleteTasks(taskIdList: List<Long>): Completable

    @Deprecated("use updateTask")
    fun completeTask(taskId: Long): Completable
    @Deprecated("use updateTask")
    fun activeTask(taskId: Long): Completable
    fun updateTask(task: Task): Completable
}