/**
 * Copyright 2020 Ian Warwick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citizenwarwick.memset.router

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Ambient
import androidx.compose.Composable
import androidx.compose.Composition
import androidx.compose.Model
import androidx.compose.ambient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent

class RouterSetup {
    private val homeUri: Uri
    private val commandGroups: MutableList<CommandGroup> = mutableListOf()
    private val model: RouterViewModel

    constructor(model: RouterViewModel, homeUri: String, mappings: Mapper.() -> Unit = {}) {
        this.model = model
        this.homeUri = Uri.parse(homeUri)
        register(mappings)
    }

    constructor(model: RouterViewModel, homeUri: String, vararg mappings: Mapper.() -> Unit) {
        this.model = model
        this.homeUri = Uri.parse(homeUri)
        mappings.forEach {
            register(it)
        }
    }

    private data class CommandGroup(
        val paths: MutableList<Pair<Regex, @Composable() RouterContext.() -> Unit>> = mutableListOf(),
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

    private fun findMapping(uri: Uri): @Composable() RouterContext.() -> Unit {
        val group = if (uri.scheme != null && uri.host != null) {
            commandGroups
                .firstOrNull { group ->
                    group.schemes.any { uri.scheme?.matches(it) ?: false } &&
                        group.hosts.any { uri.host?.matches(it) ?: false }
                }
                ?: throw RuntimeException("no matching scheme for $uri")
        } else {
            commandGroups.first()
        }
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
    fun start(intent: Intent? = null) {
        val state = model.state

        //
        // TODO all of this needs careful consideration, test all this for correctness
        //
        val intentUri = intent?.data

        when {
            intentUri != null && state.currentUri == Uri.EMPTY -> {
                state.currentUri = intentUri
            }
            state.currentUri == Uri.EMPTY -> {
                state.currentUri = homeUri
            }
        }

        RouterAmbient.Provider(value = model) {
            state.currentUri.let {
                findMapping(it).invoke(RouterContext(it))
            }
        }
    }
}

class RouterViewModel : ViewModel(), Router {
    val state = RouterState()

    override fun goto(uri: String) {
        state.currentUri = Uri.parse(uri)
    }

    override fun isCurrentUri(uri: String): Boolean {
        return state.currentUri.toString() == uri
    }
}

fun getRouter(activity: AppCompatActivity): Router {
    return ViewModelProvider(activity).get(RouterViewModel::class.java)
}

interface Router {
    fun goto(uri: String)
    fun isCurrentUri(uri: String): Boolean
}

@Model
data class RouterState(var currentUri: Uri = Uri.EMPTY)

val RouterAmbient = Ambient.of<Router>()

@Composable
fun goto(uri: String, context: GotoContext.() -> Unit = { go() }): () -> Unit {
    val model = ambient(RouterAmbient)
    return {
        context(GotoContext(model, uri))
    }
}

class GotoContext(private val router: Router, private val destination: String) {
    fun go() {
        router.goto(destination)
    }
}

fun AppCompatActivity.routings(homeUri: String, mappings: Mapper.() -> Unit = {}): Composition {
    return setContent {
        val model = ViewModelProvider(this).get(RouterViewModel::class.java)
        RouterSetup(model, homeUri, mappings).start(intent)
    }
}

fun AppCompatActivity.routings(homeUri: String, vararg mappings: Mapper.() -> Unit): Composition {
    val model = ViewModelProvider(this).get(RouterViewModel::class.java)
    return setContent {
        RouterSetup(model, homeUri, *mappings).start(intent)
    }
}

