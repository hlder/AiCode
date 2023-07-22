package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.Element
import pcui.main.PageMainViewModel

class EmptyPreview(element: Element, viewModel: PageMainViewModel) : ElementPreview<Element>(element, viewModel) {
    @Composable
    override fun previewImpl(modifier: Modifier) {
    }
}