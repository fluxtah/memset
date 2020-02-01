package com.citizenwarwick.memset.core.nav

import com.citizenwarwick.memset.router.Destination

sealed class MemsetDestination(uri: String) : Destination(uri) {
    object HomeScreen : MemsetDestination("/")
    object QuxScreen : MemsetDestination("/qux")
    data class CardDesigner(val cardUid: String = "") :
        MemsetDestination(if (cardUid.isEmpty()) "/designer" else "/designer/$cardUid")
}
