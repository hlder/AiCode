package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.Element

class EmptyPreview(element: Element) : ElementPreview<Element>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
    }
}