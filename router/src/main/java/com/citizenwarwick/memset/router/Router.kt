package com.citizenwarwick.memset.router

import android.content.Intent
import android.net.Uri
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus

class Router {
    private val homeUri: Uri
    private val commandGroups: MutableList<CommandGroup> = mutableListOf()

    constructor(homeUri: String, mappings: Mapper.() -> Unit = {}) {
        this.homeUri = Uri.parse(homeUri)
        register(mappings)
    }

    constructor(homeUri: String, vararg mappings: Mapper.() -> Unit) {
        this.homeUri = Uri.parse(homeUri)
        mappings.forEach {
            register(it)
        }
    }

    private data class CommandGroup(
        val paths: MutableList<Pair<Regex, () -> Composer>> = mutableListOf(),
        val schemes: MutableList<Regex> = mutableListOf(),
        val hosts: MutableList<Regex> = mutableListOf()
    )

    private fun register(mappings: Mapper.() -> Unit) {
        val group = CommandGroup()
        mappings(Mapper(group.schemes, group.hosts, group.paths))
        commandGroups.add(group)
    }

    fun add(groupIndex: Int, mappings: Mapper.() -> Unit) {
        val group = commandGroups[groupIndex]
        mappings(Mapper(group.schemes, group.hosts, group.paths))
    }

    private fun findMapping(uri: Uri): () -> Composer {
        val group = commandGroups
            .firstOrNull { group ->
                group.schemes.any { uri.scheme?.matches(it) ?: false } &&
                    group.hosts.any { uri.host?.matches(it) ?: false }
            }
            ?: throw RuntimeException("no matching scheme for $uri")

        when (val match = group.paths.firstOrNull { it.first.matches(uri.path!!) }) {
            null -> {
                throw RuntimeException("no route mapped for $uri")
            }
            else -> {
                return match.second
            }
        }
    }

    @Composable
    fun startComposing(intent: Intent? = null) {
        val intentUri = intent?.data

        when {
            intentUri != null -> {
                currentUri = intentUri
            }
            currentUri == Uri.EMPTY -> {
                currentUri = homeUri
            }
        }

        var currentUri by +state { currentUri }
        gotoDelegate = {
            currentUri = Uri.parse(it)
            currentUri = Uri.parse(it)
        }
        findMapping(currentUri)().compose()
    }
}

private lateinit var startUri: Uri
private var currentUri: Uri = Uri.EMPTY
private var gotoDelegate: (uri: String) -> Unit = {}

fun goto(uri: String) {
    gotoDelegate(uri)
}
