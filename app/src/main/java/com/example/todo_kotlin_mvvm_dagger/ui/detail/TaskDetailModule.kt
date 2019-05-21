package com.example.todo_kotlin_mvvm_dagger.ui.detail

import android.content.Context
import com.example.todo_kotlin_mvvm_dagger.data.ActivityContext
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.example.todo_kotlin_mvvm_dagger.data.PerFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class TaskDetailModule {
    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic fun provideTaskId(activity: TaskDetailActivity): Long {
            return activity.intent.getLongExtra(TaskDetailActivity.ARGUMENT_EDIT_TASK_ID, 0)
        }
    }

    // Fragment Component Builders
    @ContributesAndroidInjector
    @PerFragment
    abstract fun taskDetailFragment(): TaskDetailFragment

    // Logic Classes
    @PerActivity
    @Binds
    @ActivityContext
    abstract fun bindContext(addEditTaskActivity: TaskDetailActivity): Context
}