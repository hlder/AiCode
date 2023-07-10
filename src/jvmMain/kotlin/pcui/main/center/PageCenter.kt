package pcui.main.center

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pcui.main.PageMainViewModel

@Composable
fun PageCenter(viewModel: PageMainViewModel) {
    Row() {
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        Column(modifier = Modifier.width(480.dp).fillMaxHeight().background(color = Color.Black)) {
            PhonePreview(viewModel.listPage[0])
        }
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}
