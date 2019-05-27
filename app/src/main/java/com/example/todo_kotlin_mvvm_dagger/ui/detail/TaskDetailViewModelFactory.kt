package com.example.todo_kotlin_mvvm_dagger.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.DeleteTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTask
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.UpdateTask
import javax.inject.Inject

@PerActivity
class TaskDetailViewModelFactory @Inject constructor(
    private val taskId: Long,
    private val getTask: GetTask,
    private val updateTask: UpdateTask,
    private val deleteTasks: DeleteTasks
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            return TaskDetailViewModel(taskId, getTask, updateTask, deleteTasks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}