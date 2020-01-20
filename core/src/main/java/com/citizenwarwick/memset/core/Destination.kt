package com.citizenwarwick.memset.core

import com.citizenwarwick.memset.router.goto

sealed class Destination(val uri: String) {
    object HomeScreen : Destination("/")
    object QuxScreen : Destination("/qux")
    object CardDesigner : Destination("/designer")
}

fun goto(destination: Destination) {
    goto(destination.uri)
}
