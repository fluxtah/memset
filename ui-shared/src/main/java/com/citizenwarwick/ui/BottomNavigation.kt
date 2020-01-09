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
package com.citizenwarwick.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.ExpandedHeight
import androidx.ui.layout.FlexRow
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.memset.core.Destination
import com.citizenwarwick.memset.core.goto

private val BottomNavigationHeight = 56.dp
private val BottomNavigationIconHeight = 48.dp
private val BottomNavigationPadding = 8.dp

@Composable
fun MemsetBottomNavigation() {
    Surface(color = (+MaterialTheme.colors()).primary) {
        Container(
            height = BottomNavigationHeight,
            expanded = true,
            padding = EdgeInsets(BottomNavigationPadding)
        ) {
            FlexRow {
                expanded(1f) {
                    NavItem("Home", icon = +vectorResource(R.drawable.ic_nav_home)) {
                        goto(Destination.HomeScreen)
                    }
                }
                expanded(1f) {
                    NavItem("Shared", icon = +vectorResource(R.drawable.ic_nav_global)) {
                        goto(Destination.QuxScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun NavItem(label: String, icon: VectorAsset, onClick: () -> Unit) {
    Container(width = BottomNavigationIconHeight, height = BottomNavigationIconHeight) {
        Ripple(bounded = false) {
            Clickable(onClick = onClick) {
                Column(modifier = ExpandedHeight) {
                    DrawVector(vectorImage = icon, alignment = Alignment.TopCenter)
                    Container(modifier = ExpandedHeight, alignment = Alignment.BottomCenter) {
                        Text(label)
                    }
                }
            }
        }
    }
}
