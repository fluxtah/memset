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
import androidx.compose.frames.modelListOf
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.FloatingActionButton
import com.citizenwarwick.memset.core.Destination
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.goto
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.router.Composer
import com.citizenwarwick.ui.card.CardActions
import com.citizenwarwick.ui.card.CardList
import com.citizenwarwick.ui.widgets.IconButton
import kotlinx.coroutines.runBlocking

class HomeScreenComposer(private val repository: MemoryCardRepository = get()) : Composer() {
    override fun compose() {
        MemsetMainTemplate {
            // TODO temporary solution until Compose matures with better ways to do
            //   async work other than relying on ViewModel
            val state by +state { HomeScreenState() }
            repository.getCards().observeForever {
                state.cards = modelListOf(*it.toTypedArray())
                state.loadingState = LoadingState.Loaded
            }

            when (state.loadingState) {
                LoadingState.Loaded -> {
                    CardListContainer(state.cards)
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
    private fun CardListContainer(cards: List<MemoryCard>) {
        Stack {
            expanded {
                CardList(cards, object : CardActions {
                    override fun deleteCard(card: MemoryCard) {
                        runBlocking {
                            repository.deleteCard(card)
                        }
                    }
                })
            }
            aligned(Alignment.BottomRight) {
                FloatingActionButton(modifier = Spacing(16.dp), elevation = 6.dp) {
                    IconButton(
                        vectorResourceId = R.drawable.ic_add_inverted,
                        onClick = { goto(Destination.CardDesigner) })
                }
            }
        }
    }
}
