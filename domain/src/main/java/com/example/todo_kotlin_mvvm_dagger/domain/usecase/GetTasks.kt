package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ISingleUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val taskRepository: ITaskRepository
) : ISingleUseCase<GetTasks.Param, GetTasks.Result> {

    override fun invoke(param: Param): Single<Result> {
        return taskRepository.loadTasks()
            .map { Result.Success(it) }
            .cast(Result::class.java)
            .onErrorReturn { Result.Failure(it) }
    }

    data class Param(
        val filterType: TaskFilterType
    )

    sealed class Result {
        class Success(val tasks: List<Task>) : Result()
        class Failure(val e: Throwable) : Result()
    }
}