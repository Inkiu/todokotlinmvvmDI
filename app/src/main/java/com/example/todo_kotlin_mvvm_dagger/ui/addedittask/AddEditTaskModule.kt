package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.ActivityContext
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.example.todo_kotlin_mvvm_dagger.data.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddEditTaskModule {

    // Fragment Component Builders
    @ContributesAndroidInjector
    @PerFragment
    abstract fun addEditFragment(): AddEditTaskFragment

    // Logic Classes
    @PerActivity
    @Binds
    @ActivityContext
    abstract fun bindContext(addEditTaskActivity: AddEditTaskActivity): Context
}