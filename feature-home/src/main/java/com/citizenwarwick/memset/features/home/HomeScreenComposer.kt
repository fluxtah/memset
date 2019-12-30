package com.citizenwarwick.memset.features.home

import MemsetMainTemplate
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.Button
import com.citizenwarwick.memset.router.ActiveRouter
import com.citizenwarwick.memset.router.Composer

class HomeScreenComposer : Composer() {
    override fun compose() {
        val router = +ambient(ActiveRouter)
        MemsetMainTemplate {
            Container {
                Column {
                    Button(text = "Go to Card Editor", onClick = {
                        router.goto("http://memset.com/cardeditor")
                    })
                }
            }
        }
    }
}
