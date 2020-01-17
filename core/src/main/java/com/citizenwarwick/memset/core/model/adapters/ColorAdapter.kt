package com.citizenwarwick.memset.core.model.adapters

import androidx.ui.graphics.Color
import androidx.ui.graphics.toArgb
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ColorAdapter {
    @FromJson
    fun fromJson(color: Int): Color = Color(color)

    @ToJson
    fun toJson(color: Color): Int = color.toArgb()
}
