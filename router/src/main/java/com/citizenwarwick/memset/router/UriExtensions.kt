package com.citizenwarwick.memset.router

import android.net.Uri

fun Uri.queryParam(name: String): String? {
    return getQueryParameter(name)
}

fun Uri.pathSegment(index: Int): String? = when {
    index >= pathSegments.size -> null
    else -> pathSegments[index]
}
