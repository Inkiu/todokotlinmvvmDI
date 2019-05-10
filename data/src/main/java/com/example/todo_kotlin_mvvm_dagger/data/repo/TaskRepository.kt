package com.example.todo_kotlin_mvvm_dagger.data.repo

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.ApplicationContext
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class TaskRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : ITaskRepository {

    private val tasksDatabase = mutableListOf(
        Task(0, "TEST 1", "This is fake task. 1", true),
        Task(1, "TEST 2", "This is fake task. 2", false)
    )

    override fun loadTasks(): Single<List<Task>> {
        return Single.fromCallable { tasksDatabase }
    }

    override fun saveTasks(tasks: List<Task>): Completable {
        return Completable.fromAction {
            tasks.forEach {
                tasksDatabase.add(it.copy(uuid = tasksDatabase.size.toLong()))
            }
        }
    }
}