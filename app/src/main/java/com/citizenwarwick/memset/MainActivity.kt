package com.citizenwarwick.memset

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Text
import com.citizenwarwick.features.cardeditor.CardEditorScreen
import com.citizenwarwick.features.cardeditor.model.vm.CardEditorViewModel
import com.citizenwarwick.memset.features.home.HomeScreen
import com.citizenwarwick.memset.router.Router
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private val router = Router(this) {
        schemes("https", "http")
        hosts("memset.com", "www.memset.com")

        "/" composeTo { HomeScreen(router).compose() }
        "/cardeditor" composeTo { CardEditorScreen(router, model(CardEditorViewModel::class)).compose() }
        ".*" composeTo { Text("404 Not Found") }

    }.startAt("https://memset.com/")

    private fun <T : ViewModel> model(type: KClass<T>): T = ViewModelProviders.of(this).get(type.java)
}
