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
package com.citizenwarwick.ui.card

import androidx.compose.Composable
import androidx.ui.foundation.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.material.surface.Surface
import androidx.ui.unit.dp

@Composable
fun SelectionBorder(child: @Composable() () -> Unit) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        color = Color.Transparent,
        border = Border(2.dp, Color.Black)
    ) {
        child()
    }
}
