package com.citizenwarwick.memset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import com.citizenwarwick.features.carddesigner.CardDesignerScreen
import com.citizenwarwick.memset.core.Destination
import com.citizenwarwick.memset.core.di.bootstrap
import com.citizenwarwick.memset.core.goto
import com.citizenwarwick.memset.core.isCurrentDestination
import com.citizenwarwick.memset.features.home.HomeScreen
import com.citizenwarwick.memset.router.Router
import com.citizenwarwick.memset.router.getRouter
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
        if (isCurrentDestination(this, Destination.CardDesigner())) {
            getRouter(this).goto(Destination.HomeScreen)
        } else {
            super.onBackPressed()
        }
    }
}
