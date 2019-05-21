package com.example.todo_kotlin_mvvm_dagger.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R
import dagger.Lazy
import kotlinx.android.synthetic.main.taskdetail_act.*
import javax.inject.Inject

class TaskDetailActivity : BaseActivity() {

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun createIntent(context: Context, taskId: Long): Intent {
            return Intent(context, TaskDetailActivity::class.java).apply {
                putExtra(ARGUMENT_EDIT_TASK_ID, taskId)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: TaskDetailViewModelFactory
    private val viewModel: TaskDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(TaskDetailViewModel::class.java)
    }

    @Inject
    lateinit var taskDetailFragmentProvider: Lazy<TaskDetailFragment>

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskdetail_act)

        // Set up the toolbar.
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Fragment
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
                addFragment(taskDetailFragmentProvider.get(), R.id.contentFrame)

        // Setup Floating Action Button
        fab_edit_task.setOnClickListener { /* TODO */ }

        // observe
        observe(viewModel)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun observe(viewModel: TaskDetailViewModel) {
    }
}