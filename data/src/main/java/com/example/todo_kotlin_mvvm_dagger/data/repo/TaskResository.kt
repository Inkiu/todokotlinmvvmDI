package com.example.todo_kotlin_mvvm_dagger.data.repo

import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Single

class TaskResository(

) : ITaskRepository {
    override fun loadTasks(): Single<List<Task>> {
        return Single.fromCallable {
            listOf(Task(0, "TEST 1", "This is fake task.", false))
        }
    }
}