package com.citizenwarwick.ui.card

import androidx.compose.Composable
import androidx.compose.frames.modelListOf
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
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
                CardContainer(LayoutGravity.Center, card, cardActions)
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
fun CardContainer(modifier: Modifier = Modifier.None, card: MemoryCard, cardActions: CardActions) {
    Stack(modifier = modifier + LayoutWidth.Constrain(0.dp, 496.dp)) {
        MemoryCard(
            card = card,
            onSurfaceClicked = { card.facingFront = !card.facingFront },
            onElementClick = { card.facingFront = !card.facingFront })

        Container(LayoutGravity.TopEnd) {
            CardDropDownMenu(card, cardActions)
        }
    }
}

@Composable
private fun CardDropDownMenu(card: MemoryCard, cardActions: CardActions) {
    Container {
        var isOpen by state { false }

        IconButton(
            vectorResourceId = R.drawable.ic_more,
            onClick = { isOpen = !isOpen })

        val context = ContextAmbient.current
        val menuItems = modelListOf(
            "Delete" to { cardActions.deleteCard(card) },
            "Edit" to { cardActions.editCard(card) }
            //   "Print" to { print(context, card) }
        )
        if (isOpen) {
            DropDownPopupMenu(
                modifier = LayoutPadding(end = 16.dp, bottom = 16.dp),
                items = menuItems
            ) { item ->
                Ripple(bounded = true) {
                    Clickable(onClick = { item.second() }) {
                        Container(modifier = LayoutWidth.Fill) {
                            Text(modifier = LayoutPadding(12.dp), text = item.first)
                        }
                    }
                }
            }
        }
    }
}
//
//private fun print(context: Context, card: MemoryCard) {
//    PrintHelper(context).apply {
//        scaleMode = PrintHelper.SCALE_MODE_FIT
//    }.also { printHelper ->
//        val view = (context as Activity).window.decorView.findViewById<View>(android.R.id.content)
//        val bitmap = view.drawToBitmap(Bitmap.Config.ARGB_8888)
//        printHelper.printBitmap("Memset Card", bitmap)
//    }
//}
