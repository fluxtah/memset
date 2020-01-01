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
import com.citizenwarwick.memset.router.goto

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
                        goto("http://memset.com/")
                    }
                }
                expanded(1f) {
                    NavItem("Shared", icon = +vectorResource(R.drawable.ic_nav_global)) {
                        goto("http://memset.com/global")
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
