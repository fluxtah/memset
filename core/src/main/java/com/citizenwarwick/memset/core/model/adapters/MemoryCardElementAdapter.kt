package com.citizenwarwick.memset.core.model.adapters

import androidx.ui.core.Alignment
import androidx.ui.graphics.Color
import androidx.ui.graphics.toArgb
import androidx.ui.text.font.FontWeight
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.citizenwarwick.memset.core.model.PianoRollElement
import com.citizenwarwick.memset.core.model.ShapeElement
import com.citizenwarwick.memset.core.model.ShapeType
import com.citizenwarwick.memset.core.model.TextElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import androidx.ui.graphics.Color.Companion as Color1

class MemoryCardElementAdapter {
    @FromJson
    fun fromJson(jsonElement: MemoryCardElementJson): MemoryCardElement = when (jsonElement.type) {
        "text" -> {
            TextElement().apply {
                color = jsonElement.properties["color"]?.let { Color(it.toInt()) } ?: Color1.Black
                text = jsonElement.properties["text"] ?: ""
                fontSize = jsonElement.properties["fontSize"]?.toFloat() ?: 8f
                fontWeight = jsonElement.properties["fontWeight"]?.let {
                    if (it == "bold") FontWeight.Bold else FontWeight.Normal
                } ?: FontWeight.Normal
            }
        }
        "shape" -> {
            ShapeElement().apply {
                width = jsonElement.properties["width"]?.toFloat() ?: 64f
                height = jsonElement.properties["height"]?.toFloat() ?: 64f
                color = jsonElement.properties["color"]?.let { Color(it.toInt()) } ?: Color.Black
                shapeType = jsonElement.properties["shapeType"]?.let { ShapeType.valueOf(it) } ?: ShapeType.Oval
            }
        }
        "pianoroll" -> {
            PianoRollElement().apply {
                highlightedNotes = jsonElement.properties["highlightednotes"] ?: ""
                scale = jsonElement.properties["scale"]?.toFloat() ?: 1.5f
            }
        }
        else -> throw RuntimeException("Not support data type")
    }.apply {
        name = jsonElement.properties["name"] ?: ""
        alignment = jsonElement.properties["alignment"]?.let { Alignment.valueOf(it) } ?: Alignment.Center
        spacingTop = jsonElement.properties["spacingTop"]?.toFloat() ?: 0f
        spacingLeft = jsonElement.properties["spacingLeft"]?.toFloat() ?: 0f
        spacingRight = jsonElement.properties["spacingRight"]?.toFloat() ?: 0f
        spacingBottom = jsonElement.properties["spacingBottom"]?.toFloat() ?: 0f
    }

    @ToJson
    fun toJson(card: MemoryCardElement): MemoryCardElementJson = when (card) {
        is TextElement -> MemoryCardElementJson("text", card.mapProperties())
        is ShapeElement -> MemoryCardElementJson("shape", card.mapProperties())
        is PianoRollElement -> MemoryCardElementJson("pianoroll", card.mapProperties())
        else -> throw RuntimeException("Not support data type")
    }
}

private fun MemoryCardElement.mapProperties(): Map<String, String> {
    return mutableMapOf<String, String>().also { map ->
        map["editorGuid"] = uuid
        map["name"] = name
        map["alignment"] = alignment.name
        map["spacingTop"] = spacingTop.toString()
        map["spacingLeft"] = spacingLeft.toString()
        map["spacingRight"] = spacingRight.toString()
        map["spacingBottom"] = spacingBottom.toString()

        when (this) {
            is TextElement -> {
                map["color"] = color.toArgb().toString()
                map["text"] = text
                map["fontSize"] = fontSize.toString()
                map["fontWeight"] = if (fontWeight == FontWeight.Bold) "bold" else "normal"
            }
            is ShapeElement -> {
                map["color"] = color.toArgb().toString()
                map["width"] = width.toString()
                map["height"] = height.toString()
                map["shapeType"] = shapeType.name
            }
            is PianoRollElement -> {
                map["scale"] = scale.toString()
                map["highlightednotes"] = highlightedNotes
            }
        }
    }
}

data class MemoryCardElementJson(val type: String, val properties: Map<String, String>)
