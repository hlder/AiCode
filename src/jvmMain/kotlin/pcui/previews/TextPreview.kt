package pcui.previews

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import pcui.beans.elements.TextElement
import pcui.previews.propertys.ModifierPreview

class TextPreview(element: TextElement) : ElementPreview<TextElement>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
        Text(
            text = "${element.text}",
            fontSize = element.textSize?.sp ?: TextUnit.Unspecified,
            color = element.textColor?: Color.Unspecified,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = ModifierPreview(element).getModifier().then(modifier)
        )
    }
}