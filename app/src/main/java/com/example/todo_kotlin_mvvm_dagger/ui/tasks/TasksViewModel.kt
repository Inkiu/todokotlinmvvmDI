package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import io.reactivex.rxkotlin.subscribeBy

class TasksViewModel(
    private val getTasks: GetTasks
) : BaseViewModel() {
    override fun onCreate() {
        getTasks(GetTasks.Param(TaskFilterType.ACTIVE_TASKS))
            .subscribeBy {
                when (it) {
                    is GetTasks.Result.Success -> Log.d("tmpLog", "onCreate : ${it.tasks}" )
                    is GetTasks.Result.Failure -> Log.d("tmpLog", "onCreate : ${it.e}" )
                }
            }
    }
}