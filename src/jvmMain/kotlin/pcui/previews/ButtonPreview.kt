package pcui.previews

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pcui.beans.elements.ButtonElement

class ButtonPreview(element: ButtonElement) : ElementPreview<ButtonElement>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
        Button(
            modifier = modifier,
            onClick = {}
        ) {
            TextPreview(element).preview(Modifier)
        }
    }
}