package com.citizenwarwick.features.cardeditor

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    object Error : LoadingState()
}
