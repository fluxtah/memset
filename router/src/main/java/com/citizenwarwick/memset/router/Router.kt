package com.citizenwarwick.memset.router

import android.net.Uri
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class Router {
    private lateinit var model: RouterViewModel
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
    fun startComposing(activity: FragmentActivity) {
        model = ViewModelProviders.of(activity).get(RouterViewModel::class.java)

        val intentUri = activity.intent.data

        when {
            intentUri != null -> {
                model.currentUri = intentUri
            }
            model.currentUri == Uri.EMPTY -> {
                model.currentUri = homeUri
            }
        }

        var currentUri by +state { model.currentUri }
        gotoDelegate = {
            model.currentUri = Uri.parse(it)
            currentUri = Uri.parse(it)
        }
        findMapping(currentUri)().compose()
    }

    internal class RouterViewModel : ViewModel() {
        lateinit var startUri: Uri
        var currentUri: Uri = Uri.EMPTY
    }
}

private var gotoDelegate: (uri: String) -> Unit = {}
fun goto(uri: String) {
    gotoDelegate(uri)
}
