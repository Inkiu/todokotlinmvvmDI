package com.example.todo_kotlin_mvvm_dagger.tasks

import android.os.Bundle
import com.example.todo_kotlin_mvvm_dagger.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_tasks.*
import javax.inject.Inject

class TasksActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var testString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        taskTextView.text = testString
    }
}
