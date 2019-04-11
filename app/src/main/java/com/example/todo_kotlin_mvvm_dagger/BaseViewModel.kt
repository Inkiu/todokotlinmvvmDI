package com.example.todo_kotlin_mvvm_dagger

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver
{
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    // Lifecycle
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {  }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() { }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() { }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() { }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() { }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() { }


    // Extensions
    protected fun Disposable.composite() = compositeDisposable.add(this)
}
