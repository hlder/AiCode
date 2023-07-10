package pcui.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.elements.TextFieldElement
import widgets.HintTextFiled

class TextFieldPreview(element: TextFieldElement) : ElementPreview<TextFieldElement>(element) {
    @Composable
    override fun preview(modifier: Modifier) {
        HintTextFiled(
            textStyle = TextStyle(
                fontSize = element.textSize?.sp?:TextUnit.Unspecified,
                color = element.textColor?: Color.Unspecified,
                fontWeight = element.textWeight?.getFontWeight(),
                textAlign = element.textAlign,
            ),
            hint = element.hintText,
            value = element.text?:"",
            onValueChange = {}
        )
    }
}