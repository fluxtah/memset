package com.citizenwarwick.ui.card

import androidx.compose.Composable
import androidx.compose.frames.modelListOf
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Stack
import androidx.ui.material.Divider
import androidx.ui.material.ripple.Ripple
import androidx.ui.unit.dp
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.ui.R
import com.citizenwarwick.ui.widgets.DropDownPopupMenu
import com.citizenwarwick.ui.widgets.IconButton

@Composable
fun CardList(cards: List<MemoryCard>, cardActions: CardActions) {
    VerticalScroller {
        Column(modifier = LayoutPadding(8.dp)) {
            for (card in cards) {
                CardContainer(card, cardActions)
                Divider(height = 8.dp, color = Color.Transparent)
            }
        }
    }
}

class CardActions(
    var deleteCard: (card: MemoryCard) -> Unit = {},
    var editCard: (card: MemoryCard) -> Unit = {}
)

@Composable
fun CardContainer(card: MemoryCard, cardActions: CardActions) {
    Stack {
        MemoryCard(
            card = card,
            onSurfaceClicked = { card.facingFront = !card.facingFront },
            onElementClick = { card.facingFront = !card.facingFront })

        Container(LayoutGravity.TopRight) {
            CardDropDownMenu(card, cardActions)
        }
    }
}

@Composable
private fun CardDropDownMenu(card: MemoryCard, cardActions: CardActions) {
    Container {
        val isOpen = state { false }

        IconButton(
            vectorResourceId = R.drawable.ic_more,
            onClick = { isOpen.value = !isOpen.value })

        val items = modelListOf(
            "Delete" to { cardActions.deleteCard(card) },
            "Edit" to { cardActions.editCard(card) }
        )
        if (isOpen.value) {
            DropDownPopupMenu(
                modifier = LayoutPadding(right = 16.dp, bottom = 16.dp),
                items = items
            ) { item ->
                Ripple(bounded = true) {
                    Clickable(onClick = { item.second() }) {
                        Container {
                            Text(modifier = LayoutPadding(12.dp), text = item.first)
                        }
                    }
                }
            }
        }
    }
}
