package com.citizenwarwick.memset.core

import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.memset.router.isCurrentUri

sealed class Destination(val uri: String) {
    object HomeScreen : Destination("/")
    object QuxScreen : Destination("/qux")
    data class CardDesigner(val cardUid: String = "") :
        Destination(if (cardUid.isEmpty()) "/designer" else "/designer/$cardUid")
}

fun goto(destination: Destination) = goto(destination.uri)
fun isCurrentDestination(destination: Destination): Boolean = isCurrentUri(destination.uri)
