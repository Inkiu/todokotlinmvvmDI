package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ICompletableUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable


class UpdateTask (
    private val taskRepository: ITaskRepository
) : ICompletableUseCase<UpdateTask.Param> {

    override fun invoke(param: Param): Completable {
        return taskRepository.loadTask(param.taskId)
            .flatMapCompletable {
                val newTask = Task(it.uuid, param.title ?: it.title, param.content ?: it.description, param.complete ?: it.completed)
                taskRepository.updateTask(newTask)
            }
    }

    // TODO nullable?
    data class Param(
        val taskId: Long,
        val complete: Boolean? = null,
        val title: String? = null,
        val content: String? = null
    )
}