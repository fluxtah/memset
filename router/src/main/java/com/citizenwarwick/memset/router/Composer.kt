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
