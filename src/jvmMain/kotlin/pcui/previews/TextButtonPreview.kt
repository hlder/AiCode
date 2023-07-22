package pcui.previews

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.TextButtonElement
import pcui.main.PageMainViewModel

class TextButtonPreview(element: TextButtonElement, viewModel: PageMainViewModel) : ElementPreview<TextButtonElement>(element, viewModel) {
    @Composable
    override fun preview(modifier: Modifier) {
        TextButton(onClick = {}) {
            Text(text = "${element.text}")
        }
    }
}