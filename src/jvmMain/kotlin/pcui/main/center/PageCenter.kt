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
    val movePositionVersion = remember { viewModel.movePositionVersion }.value
    val changeParamVersion = remember { viewModel.changeParamVersion }.value
    val nowSelectPage = remember { viewModel.nowSelectPage }.value
    val selectedPage = viewModel.listPage[nowSelectPage]

    Row() {
        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
        Column(modifier = Modifier.width(410.dp).fillMaxHeight().background(color = Color.Black)) {
            PhonePreview(selectedPage, viewModel)
        }
        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Text("位置版本:${movePositionVersion}")
            Text("参数版本:${changeParamVersion}")
        }
//        Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
    }
}
