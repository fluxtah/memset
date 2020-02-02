package com.citizenwarwick.memset.router

import android.net.Uri

fun Uri.queryParam(name: String): String? {
    return getQueryParameter(name)
}

fun Uri.slug(index: Int): String? = when {
    index >= pathSegments.size -> null
    else -> pathSegments[index]
}
