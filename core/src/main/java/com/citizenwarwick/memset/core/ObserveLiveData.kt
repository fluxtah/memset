package com.citizenwarwick.memset.core

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.stateFor
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Composable
fun <T> observe(data: LiveData<T>, block: ObserveContext<T>.() -> Unit) {
    val thing = stateFor<T?> { null }
    onActive {
        val context = ObserveContext<T>()
        block(context)
        context.onStart()

        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                if (context.once) {
                    data.removeObserver(this)
                }
                thing.value = t
                context.onResult(t)
            }
        }

        with(data) {
            observeForever(observer)
            onDispose {
                removeObserver(observer)
            }
        }
    }
    thing.value
}

class ObserveContext<T> {
    var onStart: () -> Unit = {}
    var onResult: (T) -> Unit = {}
    var once = false
}
