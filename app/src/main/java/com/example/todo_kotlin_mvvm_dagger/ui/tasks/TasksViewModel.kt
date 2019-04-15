package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import io.reactivex.rxkotlin.subscribeBy

class TasksViewModel(
    private val getTasks: GetTasks
) : BaseViewModel() {

    val taskData = MutableLiveData<List<Task>>()
    val loadingState = MutableLiveData<Boolean>()

    override fun onCreate() {
        loadTasks()
    }

    fun onTaskRefresh() {
        loadTasks()
    }

    private fun loadTasks() {
        getTasks(GetTasks.Param(TaskFilterType.ACTIVE_TASKS))
            .doOnSubscribe { loadingState.value = true }
            .doFinally { loadingState.value = false }
            .subscribeBy {
                when (it) {
                    is GetTasks.Result.Success -> { taskData.value = it.tasks }
                    is GetTasks.Result.Failure -> Log.d("tmpLog", "onCreate : ${it.e}" )
                }
            }
    }
}