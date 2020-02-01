package com.citizenwarwick.memset.core

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import com.citizenwarwick.memset.router.GotoContext
import com.citizenwarwick.memset.router.RouterContext
import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.memset.router.isCurrentUri

sealed class Destination(val uri: String) {
    object HomeScreen : Destination("/")
    object QuxScreen : Destination("/qux")
    data class CardDesigner(val cardUid: String = "") :
        Destination(if (cardUid.isEmpty()) "/designer" else "/designer/$cardUid")
}

@Composable
fun goto(destination: Destination, context: GotoContext.() -> Unit = { go() }) = goto(destination.uri, context)

fun isCurrentDestination(activity: AppCompatActivity, destination: Destination): Boolean =
    isCurrentUri(activity, destination.uri)

fun RouterContext.goto(destination: Destination) {
    goto(destination.uri)
}
