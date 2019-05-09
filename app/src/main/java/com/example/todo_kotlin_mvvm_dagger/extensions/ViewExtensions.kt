package com.example.todo_kotlin_mvvm_dagger.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addSimpleTextChangeListener(listener: (s: CharSequence?, count: Int) -> Unit) {
    addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) { /* no-op */ }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /* no-op */ }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let { listener(it, count) }
        }
    })
}