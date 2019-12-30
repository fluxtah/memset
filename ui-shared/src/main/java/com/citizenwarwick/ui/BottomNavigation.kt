package com.citizenwarwick.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.FlexRow
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.memset.router.Router

private val BottomNavigationHeight = 56.dp
private val BottomNavigationIconHeight = 48.dp
private val BottomNavigationPadding = 16.dp

@Composable
fun MemsetBottomNavigation(router: Router) {
    Surface(color = (+MaterialTheme.colors()).primary) {
        Container(
            height = BottomNavigationHeight,
            expanded = true,
            padding = EdgeInsets(left = BottomNavigationPadding, right = BottomNavigationPadding)
        ) {
            FlexRow {
                flexible(1f) {
                    NavItem(icon = +vectorResource(R.drawable.ic_nav_home)) {
                        router.navigate("http://memset.com/")
                    }
                }
                flexible(1f) {
                    NavItem(icon = +vectorResource(R.drawable.ic_nav_global)) {
                        router.navigate("http://memset.com/global")
                    }
                }
            }
        }
    }
}

@Composable
fun NavItem(icon: VectorAsset, onClick: () -> Unit) {
    Clickable(onClick = onClick) {
        Container(width = BottomNavigationIconHeight, height = BottomNavigationIconHeight) {
            Ripple(bounded = false) {
                DrawVector(vectorImage = icon)
            }
        }
    }
}
