package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.CreateTasks
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.GetTask
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.UpdateTask
import com.example.todo_kotlin_mvvm_dagger.events.Event
import io.reactivex.rxkotlin.subscribeBy

class AddEditViewModel(
    private val taskId: Long,
    private val getTask: GetTask,
    private val createTasks: CreateTasks,
    private val updateTask: UpdateTask
) : BaseViewModel() {

    val taskViewModel = MutableLiveData<TaskViewModel>().apply { value = TaskViewModel("", "", false) }

    /* events */
    val error = MutableLiveData<Event<ErrorType>>()
    val navigateBack = MutableLiveData<Event<Unit>>()

    override fun onCreate() {
        if (taskId != -1L) {
            getTask(GetTask.Param(taskId))
                .subscribeBy(
                    onSuccess = {
                        if (it is GetTask.Result.Success) {
                            taskViewModel.value = TaskViewModel(
                                it.task.title, it.task.description, it.task.completed
                            )
                        }
                        else { /* TODO */ }
                    }
                )
        }
    }

    fun onTitleChange(s: String) {
        if (s == taskViewModel.value!!.title) return
        taskViewModel.value = taskViewModel.value!!.copy(title = s)
    }

    fun onContentChange(s: String) {
        if (s == taskViewModel.value!!.content) return
        taskViewModel.value = taskViewModel.value!!.copy(content = s)
    }

    fun onSubmit() {
        if (taskViewModel.value!!.title.isEmpty()) {
            error.value = Event(ErrorType.EMPTY_TITLE)
        } else {
            saveTask(taskViewModel.value!!)
        }
    }

    private fun saveTask(taskViewModel: TaskViewModel) {
        if (taskId >= 0) {
            updateTask(UpdateTask.Param(taskId, taskViewModel.complete, taskViewModel.title, taskViewModel.content))
                .subscribeBy(
                    onError = { /* TODO */ },
                    onComplete = { navigateBack.value = Event(Unit) }
                )
        } else {
            val tasks = listOf(Task(0, taskViewModel.title, taskViewModel.content, false))
            createTasks(CreateTasks.Param(tasks))
                .subscribeBy {
                    navigateBack.value = Event(Unit)
                }
        }
    }

    enum class ErrorType {
        EMPTY_TITLE
    }

    data class TaskViewModel(
        val title: String,
        val content: String,
        val complete: Boolean
    )
}