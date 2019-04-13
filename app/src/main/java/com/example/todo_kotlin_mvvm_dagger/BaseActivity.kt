package com.example.todo_kotlin_mvvm_dagger

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.support.DaggerAppCompatActivity
import java.lang.NullPointerException

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    protected fun BaseActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager?.run {
            beginTransaction()
                .add(frameId, fragment)
                .commit()
        } ?: throw NullPointerException("FragmentManager is null")
    }
}