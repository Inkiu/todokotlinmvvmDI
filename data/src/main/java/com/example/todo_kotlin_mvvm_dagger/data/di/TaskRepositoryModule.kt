package com.example.todo_kotlin_mvvm_dagger.data.di

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.repo.TaskRepository
import com.example.todo_kotlin_mvvm_dagger.domain.repo.ITaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class TaskRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTaskRespotory(taskRepository: TaskRepository): ITaskRepository
}