package com.citizenwarwick.memset.router

import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.setContent

class Router : LifecycleObserver {
    constructor(activity: AppCompatActivity, mappings: Mapper.() -> Unit = {}) {
        this.activity = activity
        configure(mappings)
    }

    constructor(activity: AppCompatActivity, vararg mappings: Mapper.() -> Unit) {
        this.activity = activity
        mappings.forEach {
            configure(it)
        }
    }

    private lateinit var model: RouterViewModel
    private val commandGroups: MutableList<CommandGroup> = mutableListOf()
    private val activity: AppCompatActivity
    private val logger = Logger(false)
    private var startUri: Uri = Uri.EMPTY

    var enableLogging: Boolean = false
        set(value) {
            logger.enabled = value
            field = value
        }

    private data class CommandGroup(
        val paths: MutableList<Pair<Regex, () -> Composer>> = mutableListOf(),
        val schemes: MutableList<Regex> = mutableListOf(),
        val hosts: MutableList<Regex> = mutableListOf()
    )

    private fun configure(mappings: Mapper.() -> Unit) {
        activity.lifecycle.addObserver(this)
        val group = CommandGroup()
        mappings(Mapper(this, group.schemes, group.hosts, group.paths))
        commandGroups.add(group)
    }

    fun add(groupIndex: Int, mappings: Mapper.() -> Unit) {
        val group = commandGroups[groupIndex]
        mappings(Mapper(this, group.schemes, group.hosts, group.paths))
    }

    fun navigate(uri: String) {
        navigate(Uri.parse(uri))
    }

    fun navigate(uri: Uri) {
        val group = commandGroups
            .firstOrNull { group ->
                group.schemes.any { it.matches(uri.scheme!!) } &&
                    group.hosts.any { it.matches(uri.host!!) }
            }
            ?: throw RuntimeException("no matching scheme for $uri")

        return when (val match = group.paths.firstOrNull { it.first.matches(uri.path!!) }) {
            null -> {
                throw RuntimeException("no route mapped for $uri")
            }
            else -> {
                compose(match.second, uri)
            }
        }
    }

    private fun compose(createComposer: () -> Composer, navigateUri: Uri) {
        createComposer().apply {
            uri = navigateUri
            logger.log("Compose", "${javaClass.simpleName} for $uri")
            activity.setContent {
                compose()
            }
        }
        model.currentUri = navigateUri
    }

    fun startAt(startUri: String): Router {
        this.startUri = Uri.parse(startUri)
        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onActivityCreated() {
        model = ViewModelProviders.of(activity).get(RouterViewModel::class.java)
        if (model.currentUri != Uri.EMPTY) {
            navigate(model.currentUri)
        } else {
            model.startUri = startUri
            if (startUri != Uri.EMPTY) {
                navigate(startUri)
            }
        }
    }

    class Logger(var enabled: Boolean) {
        val tag = Router::class.java.simpleName

        fun log(state: String, message: String = "") {
            if (enabled) Log.d(tag, "[$state] $message")
        }
    }

    internal class RouterViewModel : ViewModel() {
        lateinit var startUri: Uri
        var currentUri: Uri = Uri.EMPTY
    }
}
