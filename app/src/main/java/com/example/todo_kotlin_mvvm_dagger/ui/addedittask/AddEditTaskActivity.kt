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
import dagger.Lazy

import kotlinx.android.synthetic.main.addtask_act.*
import javax.inject.Inject

class AddEditTaskActivity : BaseActivity() {

    companion object {
        const val REQUEST_ADD_TASK = 1
        const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"

        fun createIntent(context: Context, taskId: String = ""): Intent {
            return Intent(context, AddEditTaskActivity::class.java).apply {
                putExtra(ARGUMENT_EDIT_TASK_ID, taskId)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: AddEditViewModelFactory
    private val viewModel: AddEditViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AddEditViewModel::class.java)
    }

    @Inject
    lateinit var addEditTaskFragmentProvider: Lazy<AddEditTaskFragment>

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
                addFragment(addEditTaskFragmentProvider.get(), R.id.contentFrame)

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
