package pcui.previews

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.ButtonElement
import pcui.main.PageMainViewModel

class ButtonPreview(element: ButtonElement, viewModel: PageMainViewModel) : ElementPreview<ButtonElement>(element, viewModel) {
    @Composable
    override fun previewImpl(modifier: Modifier) {
        Button(
            modifier = modifier,
            onClick = {}
        ) {
            TextPreview(element, viewModel).preview(Modifier)
        }
    }
}