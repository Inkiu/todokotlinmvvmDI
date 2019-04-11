package com.example.todo_kotlin_mvvm_dagger.di.modules

import com.example.todo_kotlin_mvvm_dagger.tasks.TasksActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [TestModule::class])
    abstract fun tasksActivity(): TasksActivity
}