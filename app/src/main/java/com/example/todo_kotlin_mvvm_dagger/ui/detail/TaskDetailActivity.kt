package com.example.todo_kotlin_mvvm_dagger.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.ui.addedittask.AddEditTaskActivity
import kotlinx.android.synthetic.main.taskdetail_act.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf

class TaskDetailActivity : BaseActivity() {

    companion object {
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun createIntent(context: Context, taskId: Long): Intent {
            return Intent(context, TaskDetailActivity::class.java).apply {
                putExtra(ARGUMENT_EDIT_TASK_ID, taskId)
            }
        }
    }

    private val viewModel: TaskDetailViewModel by viewModel {
        parametersOf(intent.getLongExtra(ARGUMENT_EDIT_TASK_ID, -1L))
    }

    private val detailFragment: TaskDetailFragment by currentScope.inject()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskdetail_act)

        // Set up the toolbar.
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Fragment
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
                addFragment(detailFragment, R.id.contentFrame)

        // Setup Floating Action Button
        fab_edit_task.setOnClickListener {
            viewModel.onTaskEdit()
        }

        // observe
        observe(viewModel)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun observe(viewModel: TaskDetailViewModel) {
        viewModel.navigateEditTask.observe(this, Observer {
            it.getContentIfNotHandled()?.let {  taskId ->
                startActivity(AddEditTaskActivity.createIntent(this, taskId))
                finish()
            }
        })
        viewModel.navigateBack.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })
    }
}