package com.citizenwarwick.features.cardeditor.model

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    object Error : LoadingState()
}
