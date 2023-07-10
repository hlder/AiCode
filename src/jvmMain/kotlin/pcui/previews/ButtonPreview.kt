package pcui.previews

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pcui.beans.elements.ButtonElement

class ButtonPreview(element: ButtonElement) : ElementPreview<ButtonElement>(element) {
    @Composable
    override fun preview() {
        Text("aaaaaa", modifier = Modifier.background(color = Color.Red))
    }
}