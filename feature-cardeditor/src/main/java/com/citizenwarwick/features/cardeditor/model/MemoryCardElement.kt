package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model
import androidx.compose.frames.ModelMap
import androidx.compose.frames.modelMapOf

@Model
data class MemoryCardElement(val type: String, val properties: ModelMap<String, String> = modelMapOf())
