package pcui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pcui.main.center.PageCenter
import pcui.main.left.PageLeft
import pcui.main.right.PageRight
import pcui.main.top.PageTop
import pcui.theme.WindowColors

@Composable
fun PageMain() {
    val viewModel = remember { PageMainViewModel() }
    Column {
        PageTop(viewModel)
        Row {
            Box(
                modifier = Modifier.fillMaxHeight().width(300.dp)
                    .background(color = WindowColors.baseBackgroundColor)
            ) {
                PageLeft(viewModel)
            }
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight(), color = Color.Black)
            Box(
                modifier = Modifier.fillMaxHeight().fillMaxWidth().weight(1f)
                    .background(color = WindowColors.baseBackgroundColor)
            ) {
                PageCenter(viewModel)
            }
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight(), color = Color.Black)
            Box(
                modifier = Modifier.fillMaxHeight().width(300.dp)
                    .background(color = WindowColors.baseBackgroundColor)
            ) {
                PageRight(viewModel)
            }
        }
    }
}