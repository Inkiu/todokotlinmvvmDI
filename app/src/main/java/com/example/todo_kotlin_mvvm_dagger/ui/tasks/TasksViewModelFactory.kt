package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_kotlin_mvvm_dagger.data.PerActivity
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.DeleteTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.UpdateTask
import java.lang.IllegalArgumentException
import javax.inject.Inject

@PerActivity
class TasksViewModelFactory @Inject constructor(
    private val getTasks: GetTasks,
    private val updateTask: UpdateTask,
    private val deleteTasks: DeleteTasks
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(getTasks, updateTask, deleteTasks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}