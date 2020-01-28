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
        val paths: MutableList<Pair<Regex, @Composable() () -> Unit>> = mutableListOf(),
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

    private fun findMapping(uri: Uri): @Composable() () -> Unit {
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
    fun startComposing(intent: Intent? = null) {
        //
        // TODO all of this needs careful consideration, test all this for correctness
        //
        val intentUri = intent?.data

        when {
            intentUri != null && currentUri == Uri.EMPTY -> {
                currentUri = intentUri
            }
            currentUri == Uri.EMPTY -> {
                currentUri = homeUri
            }
        }

        var currentUriState by +state { currentUri }
        gotoDelegate = {
            currentUriState = Uri.parse(it)
            currentUri = currentUriState
        }
        findMapping(currentUriState).invoke()
    }
}

private var currentUri: Uri = Uri.EMPTY
private var gotoDelegate: (uri: String) -> Unit = {}

fun goto(uri: String) = gotoDelegate(uri)
fun isCurrentUri(uri: String): Boolean = currentUri.toString() == uri
