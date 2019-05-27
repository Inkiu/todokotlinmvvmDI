package com.example.todo_kotlin_mvvm_dagger.di

import com.example.todo_kotlin_mvvm_dagger.ui.detail.TaskDetailActivity
import com.example.todo_kotlin_mvvm_dagger.ui.detail.TaskDetailFragment
import com.example.todo_kotlin_mvvm_dagger.ui.detail.TaskDetailViewModel
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksActivity
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksFragment
import com.example.todo_kotlin_mvvm_dagger.ui.tasks.TasksViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val taskViewModule = module {
    scope(named<TasksActivity>()) {
        scoped { TasksFragment() }
    }
    viewModel { TasksViewModel(get(), get(), get()) }
}

val taskDetailViewModule = module {
    scope(named<TaskDetailActivity>()) {
        scoped { TaskDetailFragment() }
    }
    viewModel { (taskId: Long) -> TaskDetailViewModel(taskId, get(), get(), get()) }
}

val viewModules = listOf(taskViewModule, taskDetailViewModule)