import androidx.compose.Composable
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme
import com.citizenwarwick.ui.MemsetBottomNavigation

@Composable
fun MemsetMainTemplate(content: @Composable() () -> Unit) {
    MaterialTheme {
        FlexColumn {
            expanded(1f) {
                content()
            }
            inflexible {
                MemsetBottomNavigation()
            }
        }
    }
}
