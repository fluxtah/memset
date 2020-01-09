/**
 * Copyright 2020 Ian Warwick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
