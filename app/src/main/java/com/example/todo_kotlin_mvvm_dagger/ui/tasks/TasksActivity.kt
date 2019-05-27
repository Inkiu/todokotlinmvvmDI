package com.example.todo_kotlin_mvvm_dagger.ui.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.example.todo_kotlin_mvvm_dagger.BaseActivity
import com.example.todo_kotlin_mvvm_dagger.BaseViewModel
import com.example.todo_kotlin_mvvm_dagger.R
import com.example.todo_kotlin_mvvm_dagger.ui.addedittask.AddEditTaskActivity
import com.example.todo_kotlin_mvvm_dagger.ui.detail.TaskDetailActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.tasks_act.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class TasksActivity : BaseActivity() {

    private val viewModel: TasksViewModel by viewModel()
    private val taskFragment: TasksFragment by currentScope.inject()

    override fun getViewModel(): BaseViewModel { return viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_act)

        // Set up the toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the navigation drawer
        drawer_layout.setStatusBarBackground(R.color.colorPrimaryDark)
        nav_view?.also { setupDrawerContent(it) }

        // Setup Floating Action Button
        fab_add_task.setOnClickListener { viewModel.onAddNewTask() }

        // Fragment
        supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
            addFragment(taskFragment, R.id.contentFrame)

        // observe
        observe(viewModel)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // TODO - correct implementation
        if (AddEditTaskActivity.REQUEST_ADD_TASK == requestCode && resultCode == Activity.RESULT_OK) {
            viewModel.onActivityResult()
        }
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

    private fun observe(viewModel: TasksViewModel) {
        viewModel.navigateToAddTask.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                startActivityForResult(AddEditTaskActivity.createIntent(this), AddEditTaskActivity.REQUEST_ADD_TASK)
            }
        })
        viewModel.navigateToDetailTask.observe(this, Observer {
            it.getContentIfNotHandled()?.let {  task ->
                startActivity(TaskDetailActivity.createIntent(this, task.uuid))
            }
        })
    }
}
