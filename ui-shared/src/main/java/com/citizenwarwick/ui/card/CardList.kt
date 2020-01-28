package com.citizenwarwick.ui.card

import androidx.compose.Composable
import androidx.compose.frames.modelListOf
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.Divider
import androidx.ui.material.ripple.Ripple
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.ui.R
import com.citizenwarwick.ui.widgets.DropDownPopupMenu
import com.citizenwarwick.ui.widgets.IconButton

@Composable
fun CardList(cards: List<MemoryCard>, cardActions: CardActions) {
    VerticalScroller {
        Column(modifier = Spacing(8.dp)) {
            for (card in cards) {
                CardContainer(card, cardActions)
                Divider(height = 8.dp, color = Color.Transparent)
            }
        }
    }
}

class CardActions(
    var deleteCard: (card: MemoryCard) -> Unit = {}
)

@Composable
fun CardContainer(card: MemoryCard, cardActions: CardActions) {
    Stack {
        expanded {
            MemoryCard(
                card = card,
                onSurfaceClicked = { card.facingFront = !card.facingFront },
                onElementClick = { card.facingFront = !card.facingFront })
        }
        aligned(Alignment.TopRight) {
            CardDropDownMenu(card, cardActions)
        }
    }
}

@Composable
private fun CardDropDownMenu(card: MemoryCard, cardActions: CardActions) {
    Container {
        var isOpen by +state { false }

        IconButton(
            vectorResourceId = R.drawable.ic_more,
            onClick = { isOpen = !isOpen })

        val items = modelListOf<Pair<String, () -> Unit>>(
            "Delete" to { cardActions.deleteCard(card) }
        )
        if (isOpen) {
            DropDownPopupMenu(
                modifier = Spacing(right = 16.dp, bottom = 16.dp),
                items = items
            ) { item ->
                Ripple(bounded = true) {
                    Clickable(onClick = { item.second() }) {
                        Container {
                            Text(modifier = Spacing(12.dp), text = item.first)
                        }
                    }
                }
            }
        }
    }
}
