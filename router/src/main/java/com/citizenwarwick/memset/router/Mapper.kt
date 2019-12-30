package com.citizenwarwick.memset.router

import androidx.compose.Composable

class Mapper(
    val router: Router,
    private val schemes: MutableList<Regex>,
    private val hosts: MutableList<Regex>,
    private val paths: MutableList<Pair<Regex, () -> Composer>>
) {
    infix fun String.mapTo(composer: () -> Composer) {
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

    infix fun Array<String>.mapTo(composer: () -> Composer) {
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
