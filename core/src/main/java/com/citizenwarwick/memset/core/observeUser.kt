package com.citizenwarwick.memset.core

import androidx.compose.effectOf
import androidx.compose.memo
import androidx.compose.onCommit
import androidx.compose.stateFor
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> observe(data: LiveData<T>, result: (T) -> Unit) {
    +effectOf<T?> {
        val thing = +stateFor<T?> { null }
        val observer = +memo {
            Observer<T> {
                thing.value = it
                result(it)
            }
        }
        +onCommit(observer) {
            data.observeForever(observer)
            onDispose {
                data.removeObserver(observer)
            }
        }
        thing.value
    }
}
