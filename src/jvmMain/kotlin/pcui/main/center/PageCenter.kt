package pcui.main.center

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pcui.main.PageMainViewModel

@Composable
fun PageCenter(viewModel: PageMainViewModel) {
    val listVersion = remember { viewModel.version }
    Row() {
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        Column(modifier = Modifier.width(480.dp).fillMaxHeight().background(color = Color.Black)) {
            PhonePreview(viewModel.listPage[1])
        }
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Text("版本:${listVersion.value}")
        }
//        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}
