package com.citizenwarwick.memset.router

import androidx.compose.Composable

abstract class Destination(val uri: String)

@Composable
fun goto(destination: Destination, context: GotoContext.() -> Unit = { go() }) = goto(destination.uri, context)

fun Router.isCurrentDestination(destination: Destination): Boolean =
    isCurrentUri(destination.uri)

fun Router.goto(destination: Destination) {
    goto(destination.uri)
}
