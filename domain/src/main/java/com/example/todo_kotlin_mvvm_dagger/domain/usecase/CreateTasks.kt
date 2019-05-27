package com.example.todo_kotlin_mvvm_dagger.domain.usecase

import com.example.todo_kotlin_mvvm_dagger.domain.ICompletableUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import io.reactivex.Completable

class CreateTasks(
    private val tasksRepository: ITaskRepository
) : ICompletableUseCase<CreateTasks.Param> {

    override fun invoke(param: Param): Completable {
        return tasksRepository.saveTasks(param.tasks)
    }

    data class Param(
        val tasks: List<Task>
    )
}