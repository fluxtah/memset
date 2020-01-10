package com.citizenwarwick.memset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Text
import androidx.ui.core.setContent
import com.citizenwarwick.features.cardeditor.CardEditorScreenComposer
import com.citizenwarwick.features.cardeditor.model.vm.CardEditorViewModel
import com.citizenwarwick.memset.features.home.HomeScreenComposer
import com.citizenwarwick.memset.router.Router
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Router("https://memset.com/cardeditor") {
                schemes("https", "http")
                hosts("memset.com", "www.memset.com")

                "/" composeWith { HomeScreenComposer() }
                "/cardeditor" composeWith { CardEditorScreenComposer(model(CardEditorViewModel::class)) }
                ".*" composeTo { Text("404 Not Found") }

            }.startComposing(intent)
        }
    }

    private fun <T : ViewModel> model(type: KClass<T>): T = ViewModelProviders.of(this).get(type.java)
}
