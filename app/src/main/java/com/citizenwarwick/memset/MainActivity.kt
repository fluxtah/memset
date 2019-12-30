package com.citizenwarwick.memset

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Text
import com.citizenwarwick.features.cardeditor.CardEditorScreenComposer
import com.citizenwarwick.features.cardeditor.model.vm.CardEditorViewModel
import com.citizenwarwick.memset.features.home.HomeScreenComposer
import com.citizenwarwick.memset.router.Router
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private val router = Router(this) {
        schemes("https", "http")
        hosts("memset.com", "www.memset.com")

        "/" composeWith { HomeScreenComposer() }
        "/cardeditor" composeWith { CardEditorScreenComposer(model(CardEditorViewModel::class)) }
        ".*" composeTo { Text("404 Not Found") }

    }.startAt("https://memset.com/")

    private fun <T : ViewModel> model(type: KClass<T>): T = ViewModelProviders.of(this).get(type.java)
}

