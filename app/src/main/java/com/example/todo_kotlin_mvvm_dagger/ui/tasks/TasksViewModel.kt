package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.model.TaskFilterType
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.DeleteTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.UpdateTask
import com.example.todo_kotlin_mvvm_dagger.events.Event
import io.reactivex.rxkotlin.subscribeBy

// TODO remove !!
class TasksViewModel(
    private val getTasks: GetTasks,
    private val updateTask: UpdateTask,
    private val deleteTasks: DeleteTasks
) : BaseViewModel() {

    val taskData = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val loadingState = MutableLiveData<Boolean>().apply { value = false }
    val currentFilterType = MutableLiveData<TaskFilterType>().apply { value = TaskFilterType.ALL_TASKS }

    /* events */
    val navigateToAddTask = MutableLiveData<Event<Unit>>()
    val navigateToDetailTask = MutableLiveData<Event<Task>>()

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

    fun onTaskSelected(task: Task) {
        navigateToDetailTask.value = Event(task)
    }

    fun onTaskButtonSelected(task: Task) {
        updateTask(UpdateTask.Param(task.uuid, !task.completed))
            .doOnSubscribe { loadingState.value = true }
            .subscribeBy(
                onComplete = { loadTasks(currentFilterType.value!!) },
                onError = { /* TODO */ }
            )
    }

    fun onClearTask() {
        val completed = taskData.value!!.filter { it.completed }.map { it.uuid }
        deleteTasks(DeleteTasks.Param(completed))
            .doOnSubscribe { loadingState.value = true }
            .subscribeBy(
                onComplete = { loadTasks(currentFilterType.value!!) },
                onError = { /* TODO */ }
            )
    }

    private fun loadTasks(filterType: TaskFilterType = TaskFilterType.ALL_TASKS) {
        getTasks(GetTasks.Param(filterType))
            .doOnSubscribe { loadingState.value = true }
            .doFinally { loadingState.value = false }
            .subscribeBy {
                when (it) {
                    is GetTasks.Result.Success -> { taskData.value = it.tasks }
                    is GetTasks.Result.Failure -> { /* TODO */  }
                }
            }
    }
}