package com.citizenwarwick.memset.features.home

import androidx.compose.Composable
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import com.citizenwarwick.memset.router.Composer
import com.citizenwarwick.memset.router.Router
import com.citizenwarwick.ui.MemsetBottomNavigation

class HomeScreenComposer(private val router: Router) : Composer() {
    @Composable
    override fun compose() {
        MaterialTheme {
            FlexColumn {
                expanded(1f) {
                    Container {
                        Column {
                            Button(text = "Go to Card Editor", onClick = {
                                router.navigate("http://memset.com/cardeditor")
                            })
                        }
                    }
                }
                inflexible {
                    MemsetBottomNavigation(router)
                }
            }
        }
    }
}
