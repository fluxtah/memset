package com.citizenwarwick.features.cardeditor.config

import androidx.annotation.DrawableRes
import androidx.compose.Model

@Model
data class EditorFunctionConfig(val name: String, @DrawableRes val icon: Int)
