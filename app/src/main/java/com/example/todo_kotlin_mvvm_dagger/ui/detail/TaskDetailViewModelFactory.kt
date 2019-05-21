package com.example.todo_kotlin_mvvm_dagger.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTask
import javax.inject.Inject

class TaskDetailViewModelFactory @Inject constructor(
    private val getTask: GetTask
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            return TaskDetailViewModel(getTask) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}