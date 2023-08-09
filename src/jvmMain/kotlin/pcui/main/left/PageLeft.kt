package pcui.main.left

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import pcui.main.PageMainViewModel

// 拖动时候的拖动条的颜色
val dragColor = Color(0x880059ff)

// 文本的颜色
val textColor: Color = Color(0xffbbbbbb)

@Composable
fun PageLeft(viewModel: PageMainViewModel) {
    val pageLeftView = remember { PageLeftView(viewModel) }
    pageLeftView.onView()
}

