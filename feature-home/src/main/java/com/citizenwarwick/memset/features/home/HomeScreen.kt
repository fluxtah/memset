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
import androidx.compose.ambient
import androidx.compose.remember
import androidx.ui.core.Text
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.FloatingActionButton
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
    //
    // We want to remember this state across re-composition
    //
    val state = remember { HomeScreenState() }

    //
    // Observe for as long as HomeScreen is composed, changes
    // will cause re-composition down but replacing HomeScreen will
    // remove the observer from this live data
    //
    observe(repository::getCards) {
        state.apply {
            loadingState = LoadingState.Loaded
            cards = it.toModelList()
        }
    }

    val router = ambient(RouterAmbient)

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

    HomeScreenContent(state, cardActions)
}

@Composable
fun HomeScreenContent(state: HomeScreenState, cardActions: CardActions) {
    MemsetMainTemplate {
        when (state.loadingState) {
            LoadingState.Loaded -> {
                CardListContainer(state.cards, cardActions)
            }
            LoadingState.Loading -> {
                Text("Loading...")
            }
            LoadingState.Error -> {
                Text("Error")
            }
        }
    }
}

@Composable
private fun CardListContainer(cards: List<MemoryCard>, cardActions: CardActions) {
    Stack(modifier = LayoutWidth.Fill) {
        CardList(cards, cardActions)
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
