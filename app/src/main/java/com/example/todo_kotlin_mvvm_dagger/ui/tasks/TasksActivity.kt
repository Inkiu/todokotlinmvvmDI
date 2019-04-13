package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R
import com.google.android.material.navigation.NavigationView
import dagger.Lazy
import kotlinx.android.synthetic.main.tasks_act.*
import javax.inject.Inject

class TasksActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: TasksViewModelFactory
    private val viewModel: TasksViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(TasksViewModel::class.java)
    }

    @Inject
    lateinit var taskFragmentProvider: Lazy<TasksFragment>

    override fun getViewModel(): BaseViewModel { return viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        // Set up the toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // Set up the navigation drawer
        drawer_layout.setStatusBarBackground(R.color.colorPrimaryDark)
        nav_view?.also { setupDrawerContent(it) }

        // Fragment
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
            addFragment(taskFragmentProvider.get(), R.id.contentFrame)

        // Consume savedInstance
        savedInstanceState?.also { consumeStavedInstanceState(it) }

        observe(viewModel)
    }

    private fun observe(viewModel: TasksViewModel) {
//        viewModel.helloLiveData.observe(this, Observer { taskTextView.text = it })
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_navigation_menu_item -> { } // TODO
                R.id.statistics_navigation_menu_item -> { } // TODO
                else -> { } // TODO
            }
            menuItem.isChecked = true
            drawer_layout.closeDrawers()
            true
        }
    }

    private fun consumeStavedInstanceState(state: Bundle) {

    }
}
