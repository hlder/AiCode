package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.Element

abstract class ElementPreview<T : Element>(val element: T) {
    @Composable
    abstract fun preview(modifier: Modifier)
}