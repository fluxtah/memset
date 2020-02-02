package com.citizenwarwick.memset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import com.citizenwarwick.features.carddesigner.CardDesignerScreen
import com.citizenwarwick.memset.core.di.bootstrap
import com.citizenwarwick.memset.core.nav.MemsetDestination
import com.citizenwarwick.memset.features.home.HomeScreen
import com.citizenwarwick.memset.router.getRouter
import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.memset.router.isCurrentDestination
import com.citizenwarwick.memset.router.routings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bootstrap(this)

        routings("https://memset.com/") {
            schemes("https", "http")
            hosts("memset.com", "www.memset.com")

            "/" routeTo { HomeScreen() }
            "/designer/.*" routeTo { CardDesignerScreen(cardUuid = slug(1)) }
            "/designer" routeTo { CardDesignerScreen() }
            ".*" routeTo { Text("404 Not Found ($uri)") }
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
