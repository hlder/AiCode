package pcui.main.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pcui.main.PageMainViewModel

@Composable
fun PageTop(viewModel: PageMainViewModel){
    Row(
        modifier = Modifier.height(50.dp).fillMaxWidth().background(color = Color.Red)
    ) {

    }
}
