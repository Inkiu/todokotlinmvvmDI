package com.example.todo_kotlin_mvvm_dagger.di

import com.example.todo_kotlin_mvvm_dagger.data.repo.TaskRepository
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { TaskRepository(androidContext()) as ITaskRepository }
}

val useCaseModule = module {
    single { CreateTasks(get()) }
    single { DeleteTasks(get()) }
    single { GetTask(get()) }
    single { GetTasks(get()) }
    single { UpdateTask(get()) }
}

val appModules = listOf(repositoryModule, useCaseModule)