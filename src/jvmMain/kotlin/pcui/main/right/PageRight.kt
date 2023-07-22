package pcui.main.right

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import pcui.main.PageMainViewModel

@Composable
fun PageRight(viewModel: PageMainViewModel) {
    val pageRightView = remember { PageRightView(viewModel) }
    pageRightView.BaseConfig()
}
