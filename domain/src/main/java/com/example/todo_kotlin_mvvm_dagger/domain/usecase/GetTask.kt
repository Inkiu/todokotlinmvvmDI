package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ISingleUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTask @Inject constructor(
    private val taskRepository: ITaskRepository
) : ISingleUseCase<GetTask.Param, GetTask.Result> {

    override fun invoke(param: Param): Single<Result> {
        return taskRepository.loadTask(param.taskId)
            .map { Result.Success(it) }
            .cast(Result::class.java)
            .onErrorReturn { Result.Failure(it) }
    }

    data class Param(
        val taskId: Long
    )

    sealed class Result {
        class Success(val task: Task) : Result()
        class Failure(val e: Throwable) : Result()
    }
}