package com.example.todo_kotlin_mvvm_dagger.di.modules

import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [TasksModule::class])
    abstract fun tasksActivity(): TasksActivity
}