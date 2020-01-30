package com.citizenwarwick.memset.core

import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf

inline fun <reified T> List<T>.toModelList(): ModelList<T> = modelListOf(*this.toTypedArray())
