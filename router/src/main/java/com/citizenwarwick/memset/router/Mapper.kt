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

import androidx.compose.Composable

class Mapper(
    private val schemes: MutableList<Regex>,
    private val hosts: MutableList<Regex>,
    private val paths: MutableList<Pair<Regex, () -> Composer>>
) {
    infix fun String.composeWith(composer: () -> Composer) {
        paths.add(toRegex() to composer)
    }

    infix fun String.composeTo(content: @Composable() () -> Unit) {
        paths.add(toRegex() to {
            object : Composer() {
                @Composable
                override fun compose() = content()
            }
        })
    }

    infix fun Array<String>.composeWith(composer: () -> Composer) {
        this.forEach {
            paths.add(it.toRegex() to composer)
        }
    }

    fun schemes(vararg schemes: String) {
        this.schemes.addAll(schemes.map { it.toRegex() })
    }

    fun hosts(vararg hosts: String) {
        this.hosts.addAll(hosts.map { it.toRegex() })
    }
}
