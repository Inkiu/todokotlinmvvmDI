package com.example.todo_kotlin_mvvm_dagger.di.modules

import com.example.todo_kotlin_mvvm_dagger.clean.data.HelloRepository
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksFragment
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class TasksModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideViewModelFactory(getTasks: GetTasks): TasksViewModelFactory {
            return TasksViewModelFactory(getTasks)
        }
    }

    @ContributesAndroidInjector
    abstract fun tasksFragment(): TasksFragment
}