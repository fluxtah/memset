package com.citizenwarwick.memset.core

import androidx.compose.Composable
import androidx.compose.Pivotal
import androidx.compose.onActive
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

var observeCounter = 0
@Composable
fun <T> observe(@Pivotal data: LiveData<T>, block: ObserveScope<T>.() -> Unit) {
    observeCounter++
    onActive {
        val context = ObserveScope<T>()
        block(context)
        context.onStartBlock()

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                val resultScope = ObserveScope.OnResultScope(this, data, t)
                context.onResultBlock(resultScope)
            }
        }

        with(data) {
            observeForever(observer)
            onDispose {
                removeObserver(observer)
            }
        }
    }
}

class ObserveScope<T> {
    internal var onStartBlock: () -> Unit = {}
    fun onStart(block: () -> Unit) {
        onStartBlock = block
    }

    internal var onResultBlock: OnResultScope<T>.() -> Unit = {}
    fun onResult(block: OnResultScope<T>.() -> Unit) {
        onResultBlock = block
    }

    class OnResultScope<T>(
        private val observer: Observer<T>,
        private val data: LiveData<T>,
        val result: T
    ) {
        fun stopObserving() {
            data.removeObserver(observer)
        }
    }
}
