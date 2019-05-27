package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R

import kotlinx.android.synthetic.main.addtask_act.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddEditTaskActivity : BaseActivity() {

    companion object {
        const val REQUEST_ADD_TASK = 1
        const val REQUEST_EDIT_TASK = 2
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun createIntent(context: Context, taskId: Long = -1L): Intent {
            return Intent(context, AddEditTaskActivity::class.java).apply {
                putExtra(ARGUMENT_EDIT_TASK_ID, taskId)
            }
        }
    }

    private val viewModel: AddEditViewModel by viewModel {
        parametersOf(intent.getLongExtra(ARGUMENT_EDIT_TASK_ID, -1L))
    }
    private val fragment: AddEditTaskFragment by currentScope.inject()

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)

        // Set up the toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Fragment
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
                addFragment(fragment, R.id.contentFrame)

        // Setup Floating Action Button
        fab_edit_task_done.setOnClickListener { viewModel.onSubmit() }

        // observe
        observe(viewModel)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun observe(viewModel: AddEditViewModel) {
        viewModel.navigateBack.observe(this, Observer {
            setResult(Activity.RESULT_OK)
            finish()
        })
    }
}