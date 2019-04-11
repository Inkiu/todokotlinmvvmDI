package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.clean.domain.HelloUseCase
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_tasks.*
import javax.inject.Inject

class TasksActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var useCase: HelloUseCase

    private var integer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
    }

    override fun onResume() {
        super.onResume()
        integer += 1
        useCase.invoke(HelloUseCase.Param(integer))
            .subscribeBy(
                onError = { /* no-op */ },
                onSuccess = { taskTextView.text = it.greetingMessage }
            )
    }
}
