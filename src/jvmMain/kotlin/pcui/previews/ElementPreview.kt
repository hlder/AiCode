package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.Element
import pcui.main.PageMainViewModel

abstract class ElementPreview<T : Element>(val element: T, val viewModel: PageMainViewModel) {
    @Composable
    abstract fun preview(modifier: Modifier)
}