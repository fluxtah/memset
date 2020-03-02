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
import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.Button
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.core.nav.MemsetDestination
import com.citizenwarwick.memset.core.observe
import com.citizenwarwick.memset.core.toModelList
import com.citizenwarwick.memset.router.RouterAmbient
import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.ui.card.CardActions
import com.citizenwarwick.ui.card.CardList
import com.citizenwarwick.ui.widgets.IconButton
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(repository: MemoryCardRepository = get()) {
    val router = RouterAmbient.current
    val screenState = remember { HomeScreenState() }
    var error by state { true } // TODO just a silly flag for debugging something, set to true to see an error retry :) can be removed and usages

    val cardActions = CardActions(
        deleteCard = { card ->
            // TODO temporary solution until Compose matures with better ways to do
            //   async work other than relying on ViewModel
            runBlocking {
                repository.deleteCard(card)
            }
        },
        editCard = { card ->
            router.goto(MemsetDestination.CardDesigner(card.uuid))
        })

    Recompose { recompose ->
        //
        // Observe for as long as HomeScreen is composed, changes
        // will cause re-composition down but replacing HomeScreen will
        // remove the observer from this live data
        //
        observe(repository.getCards()) {
            onStart { screenState.loadingState = LoadingState.Loading }
            onResult {
                screenState.apply {
                    loadingState = if (error) LoadingState.Error else LoadingState.Loaded
                    cards = result.toModelList()
                    error = false
                }
            }
        }

        MemsetMainTemplate {
            when (screenState.loadingState) {
                LoadingState.Loaded -> {
                    HomeScreenContent(screenState.cards, cardActions)
                }
                LoadingState.Loading -> {
                    Text("Loading...")
                }
                LoadingState.Error -> {
                    Column {
                        Text("Error")
                        Button(onClick = { recompose() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeScreenContent(cards: List<MemoryCard>, cardActions: CardActions) {
    Column {
        TopBar()
        Stack(modifier = LayoutFlexible(1f) + LayoutWidth.Fill) {
            if (cards.isNotEmpty()) {
                CardList(cards, cardActions)
            } else {
                Container(LayoutGravity.Center + LayoutPadding(12.dp)) {
                    Text(
                        text = "\uD83D\uDC40 Looks like you don't have any cards yet, press the round + button below to add a card!",
                        style = MaterialTheme.typography().h3
                    )
                }
            }
            Container(modifier = LayoutGravity.BottomRight) {
                FloatingActionButton(modifier = LayoutPadding(16.dp), elevation = 6.dp) {
                    IconButton(
                        vectorResourceId = R.drawable.ic_add_inverted,
                        onClick = goto(MemsetDestination.CardDesigner())
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar() {
    TopAppBar(
        title = { Text("My Cards") },
        actionData = listOf<String>()
    ) {}
}
