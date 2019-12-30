package com.citizenwarwick.memset.router

import android.net.Uri
import androidx.compose.Composable
import kotlin.reflect.KProperty

abstract class Composer {
    internal var uri: Uri = Uri.EMPTY

    @Composable
    abstract fun compose()

    inner class QueryParamDelegate(private val name: String = "") {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
            return uri.getQueryParameter(if (name.isNotEmpty()) name else property.name)
        }
    }

    protected fun queryParam(name: String = ""): QueryParamDelegate {
        return QueryParamDelegate(name)
    }

    inner class PathSegmentDelegate(private val index: Int) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String? = when {
            index >= uri.pathSegments.size -> null
            else -> uri.pathSegments[index]
        }
    }

    protected fun pathSegment(index: Int): PathSegmentDelegate {
        return PathSegmentDelegate(index)
    }
}
