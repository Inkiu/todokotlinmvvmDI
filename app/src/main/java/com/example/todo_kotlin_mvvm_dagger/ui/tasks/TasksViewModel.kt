package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import com.example.todo_kotlin_mvvm_dagger.events.Event
import io.reactivex.rxkotlin.subscribeBy

class TasksViewModel(
    private val getTasks: GetTasks
) : BaseViewModel() {

    val taskData = MutableLiveData<List<Task>>()
    val loadingState = MutableLiveData<Boolean>()
    val currentFilterType = MutableLiveData<TaskFilterType>().apply { value = TaskFilterType.ALL_TASKS }

    /* events */
    val navigateToAddTask = MutableLiveData<Event<Unit>>()

    override fun onCreate() {
        loadTasks(currentFilterType.value!!)
    }

    fun onActivityResult() {
        loadTasks(currentFilterType.value!!)
    }

    fun onTaskRefresh() {
        loadTasks(currentFilterType.value!!)
    }

    fun onAddNewTask() {
        navigateToAddTask.value = Event(Unit)
    }

    fun onFilterSelected(filterType: TaskFilterType) {
        currentFilterType.value = filterType
        loadTasks(currentFilterType.value!!)
    }

    private fun loadTasks(filterType: TaskFilterType = TaskFilterType.ALL_TASKS) {
        getTasks(GetTasks.Param(filterType))
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