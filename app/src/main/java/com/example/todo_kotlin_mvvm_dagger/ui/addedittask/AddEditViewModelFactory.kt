package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.CreateTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTask
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.UpdateTask
import java.lang.IllegalArgumentException

class AddEditViewModelFactory(
    private val taskId: Long,
    private val getTask: GetTask,
    private val createTasks: CreateTasks,
    private val updateTask: UpdateTask
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {
            return AddEditViewModel(taskId, getTask, createTasks, updateTask) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}