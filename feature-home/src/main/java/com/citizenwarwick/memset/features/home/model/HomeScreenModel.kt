package com.citizenwarwick.memset.features.home.model

interface HomeScreenModel {
    val state: HomeScreenState

    fun loadCards()
}
