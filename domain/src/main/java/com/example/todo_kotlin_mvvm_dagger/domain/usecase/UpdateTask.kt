package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ICompletableUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable
import javax.inject.Inject


class UpdateTask @Inject constructor(
    private val taskRepository: ITaskRepository
) : ICompletableUseCase<UpdateTask.Param> {

    override fun invoke(param: Param): Completable {
        return if (param.complete) taskRepository.completeTask(param.taskId)
        else taskRepository.activeTask(param.taskId)
    }

    data class Param(
        val taskId: Long,
        val complete: Boolean
    )
}