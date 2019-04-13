package com.example.todo_kotlin_mvvm_dagger.data.repo

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Single
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val context: Context
) : ITaskRepository {
    override fun loadTasks(): Single<List<Task>> {
        return Single.fromCallable {
            listOf(Task(0, "TEST 1", "This is fake task.", false))
        }
    }
}