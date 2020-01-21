package com.citizenwarwick.memset.core

import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.memset.router.isCurrentUri

sealed class Destination(val uri: String) {
    object HomeScreen : Destination("/")
    object QuxScreen : Destination("/qux")
    object CardDesigner : Destination("/designer")
}

fun goto(destination: Destination) = goto(destination.uri)
fun isCurrentDestination(destination: Destination): Boolean = isCurrentUri(destination.uri)
