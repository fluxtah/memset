package com.citizenwarwick.memset.router

import androidx.compose.Composable

abstract class Destination(val uri: String)

@Composable
fun goto(destination: Destination, context: GotoContext.() -> Unit = { go() }) = goto(destination.uri, context)

fun RouterContext.isCurrentDestination(destination: Destination): Boolean =
    isCurrentUri(destination.uri)

fun RouterContext.goto(destination: Destination) {
    goto(destination.uri)
}
