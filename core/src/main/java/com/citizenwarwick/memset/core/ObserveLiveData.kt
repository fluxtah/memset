package com.citizenwarwick.memset.core

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.stateFor
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Composable
fun <T> observe(observable: () -> LiveData<T>, result: (T) -> Unit) {
    val thing = stateFor<T?> { null }

    onActive {
        val observer = Observer<T> { t ->
            thing.value = t
            result(t)
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
