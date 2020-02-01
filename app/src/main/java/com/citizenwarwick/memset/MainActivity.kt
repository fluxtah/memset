package com.citizenwarwick.memset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import com.citizenwarwick.features.carddesigner.CardDesignerScreen
import com.citizenwarwick.memset.core.di.bootstrap
import com.citizenwarwick.memset.core.nav.MemsetDestination
import com.citizenwarwick.memset.features.home.HomeScreen
import com.citizenwarwick.memset.router.Router
import com.citizenwarwick.memset.router.getRouter
import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.memset.router.isCurrentDestination
import com.citizenwarwick.memset.router.pathSegment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bootstrap(this)

        setContent {
            Router("https://memset.com/") {
                schemes("https", "http")
                hosts("memset.com", "www.memset.com")

                "/" composeTo { HomeScreen() }
                "/designer/.*" composeTo { uri ->
                    CardDesignerScreen(cardUuid = uri.pathSegment(1)!!)
                }
                "/designer" composeTo { CardDesignerScreen() }
                ".*" composeTo { Text("404 Not Found") }

            }.startComposing(intent)
        }
    }

    override fun onBackPressed() {
        val router = getRouter(this)
        if (router.isCurrentDestination(MemsetDestination.CardDesigner())) {
            router.goto(MemsetDestination.HomeScreen)
        } else {
            super.onBackPressed()
        }
    }
}
