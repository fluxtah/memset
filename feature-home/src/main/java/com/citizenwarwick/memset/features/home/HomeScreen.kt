package com.citizenwarwick.memset.features.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import com.citizenwarwick.memset.router.Composer
import com.citizenwarwick.memset.router.Router

class HomeScreen(private val router: Router) : Composer() {
    @Composable
    override fun compose() {
        MaterialTheme {
            Column {
                Text(text = "Home Screen")
                Button(text = "Card Editor", onClick = {
                    router.navigate("http://memset.com/cardeditor")
                })
            }
        }
    }
}
