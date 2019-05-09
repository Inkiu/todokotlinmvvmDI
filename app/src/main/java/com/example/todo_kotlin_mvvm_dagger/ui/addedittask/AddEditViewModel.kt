package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import androidx.lifecycle.MutableLiveData
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.events.Event

class AddEditViewModel(
    private val taskId: String
) : BaseViewModel() {

    private var content = Content("", "")

    /* events */
    val error = MutableLiveData<Event<ErrorType>>()

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
    }

    enum class ErrorType {
        EMPTY_TITLE
    }

    data class Content(
        val title: String,
        val content: String
    )
}