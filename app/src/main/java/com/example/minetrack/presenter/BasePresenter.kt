package com.example.minetrack.presenter

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

interface BasePresenter {

    val scope: CoroutineScope
    get() = MainScope()

    fun onViewCreated()

    fun onViewDestroyed()

    @CallSuper
    fun onDestroy() {
        scope.cancel()
    }

}