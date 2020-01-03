package com.citizenwarwick.memset.features.home

import MemsetMainTemplate
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.Button
import androidx.ui.material.FloatingActionButton
import com.citizenwarwick.memset.core.Destination
import com.citizenwarwick.memset.core.goto
import com.citizenwarwick.memset.router.Composer

class HomeScreenComposer : Composer() {
    override fun compose() {
        MemsetMainTemplate {
            Container {
                Column {
                    FloatingActionButton {}
                    Button(text = "Go to Card Editor", onClick = {
                        goto(Destination.CardEditorScreen)
                    })
                }
            }
        }
    }
}
