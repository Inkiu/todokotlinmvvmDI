package com.example.todo_kotlin_mvvm_dagger.ui.addedittask

import android.os.Bundle
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
        setSupportActionBar(toolbar)

        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
                addFragment(addEditTaskFragmentProvider.get(), R.id.contentFrame)
    }

}
