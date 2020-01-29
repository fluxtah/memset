package com.citizenwarwick.memset.core

import androidx.compose.effectOf
import androidx.compose.onActive
import androidx.compose.stateFor
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> observe(observable: () -> LiveData<T>, result: (T) -> Unit) {
    +effectOf<T?> {
        val thing = +stateFor<T?> { null }
        +onActive {
            val observer =
                Observer<T> {
                    thing.value = it
                    result(it)
                }
            with(observable()) {
                observeForever(observer)
                onDispose {
                    removeObserver(observer)
                }
            }

        }
        thing.value
    }
}
