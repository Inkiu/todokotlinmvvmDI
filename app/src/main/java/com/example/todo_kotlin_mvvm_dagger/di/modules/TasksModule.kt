package com.example.todo_kotlin_mvvm_dagger.di.modules

import com.example.todo_kotlin_mvvm_dagger.clean.data.HelloRepository
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase
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
        fun provideHelloUseCase(helloRepository: HelloRepository): HelloUseCase {
            return HelloUseCase(helloRepository)
        }

        @JvmStatic
        @Provides
        fun provideViewModelFactory(helloUseCase: HelloUseCase): TasksViewModelFactory {
            return TasksViewModelFactory(helloUseCase)
        }

    }

    @ContributesAndroidInjector
    abstract fun tasksFragment(): TasksFragment
}