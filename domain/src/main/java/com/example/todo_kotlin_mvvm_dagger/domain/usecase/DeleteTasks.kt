package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ICompletableUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable

class DeleteTasks(
    private val taskRepository: ITaskRepository
) : ICompletableUseCase<DeleteTasks.Param> {

    override fun invoke(param: Param): Completable {
        return taskRepository.deleteTasks(param.taskIdList)
    }

    data class Param(
        val taskIdList: List<Long>
    )
}