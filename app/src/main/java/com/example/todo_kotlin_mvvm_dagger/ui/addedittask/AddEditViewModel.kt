package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.domain.model.Task
import com.example.todo_kotlin_mvvm_dagger.domain.usecase.CreateTasks
import com.example.todo_kotlin_mvvm_dagger.events.Event
import io.reactivex.rxkotlin.subscribeBy

class AddEditViewModel(
    private val taskId: String,
    private val createTasks: CreateTasks
) : BaseViewModel() {

    private var content = Content("", "")

    /* events */
    val error = MutableLiveData<Event<ErrorType>>()
    val navigateBack = MutableLiveData<Event<Unit>>()

    fun onTitleChange(s: String) {
        content = content.copy(title = s)
    }

    fun onContentChange(s: String) {
        content = content.copy(content = s)
    }

    fun onSubmit() {
        if (content.title.isEmpty()) {
            error.value = Event(ErrorType.EMPTY_TITLE)
        }
        saveTask(content)
    }

    private fun saveTask(content: Content) {
        val tasks = listOf(Task(0, content.title, content.content, false))
        createTasks(CreateTasks.Param(tasks))
            .subscribeBy {
                navigateBack.value = Event(Unit)
            }
    }

    enum class ErrorType {
        EMPTY_TITLE
    }

    data class Content(
        val title: String,
        val content: String
    )
}