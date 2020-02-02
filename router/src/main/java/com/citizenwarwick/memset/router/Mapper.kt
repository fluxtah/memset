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

import android.net.Uri
import androidx.compose.Composable

class Mapper(
    private val schemes: MutableList<Regex>,
    private val hosts: MutableList<Regex>,
    private val paths: MutableList<Pair<Regex, @Composable() RouterContext.() -> Unit>>
) {
    infix fun String.routeTo(content: @Composable() RouterContext.() -> Unit) {
        paths.add(toRegex() to content)
    }

    infix fun Array<String>.routeTo(content: @Composable() RouterContext.() -> Unit) {
        this.forEach {
            paths.add(it.toRegex() to content)
        }
    }

    fun schemes(vararg schemes: String) {
        this.schemes.addAll(schemes.map { it.toRegex() })
    }

    fun hosts(vararg hosts: String) {
        this.hosts.addAll(hosts.map { it.toRegex() })
    }

}

data class RouterContext(val uri: Uri) {
    fun slug(index: Int) = uri.slug(index)
    fun param(name: String) = uri.queryParam(name)
}
