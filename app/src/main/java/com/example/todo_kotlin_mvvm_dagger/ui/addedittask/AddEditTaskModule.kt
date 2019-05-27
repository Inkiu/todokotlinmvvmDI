package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.content.Context
import androidx.annotation.Nullable
import com.example.todo_kotlin_mvvm_dagger.data.ActivityContext
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.example.todo_kotlin_mvvm_dagger.data.PerFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddEditTaskModule {

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic fun provideTaskId(activity: AddEditTaskActivity): Long {
            return activity.intent.getLongExtra(AddEditTaskActivity.ARGUMENT_EDIT_TASK_ID, -1L)
        }
    }

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