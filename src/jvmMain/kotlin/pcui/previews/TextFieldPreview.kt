package pcui.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import pcui.beans.elements.TextFieldElement
import pcui.main.PageMainViewModel
import pcui.previews.propertys.ModifierPreview
import widgets.HintTextFiled

class TextFieldPreview(element: TextFieldElement, viewModel: PageMainViewModel) : ElementPreview<TextFieldElement>(element, viewModel) {
    @Composable
    override fun previewImpl(modifier: Modifier) {
        HintTextFiled(
            textStyle = TextStyle(
                fontSize = element.textSize?.sp?:TextUnit.Unspecified,
                color = element.textColor?: Color.Unspecified,
                fontWeight = element.textWeight?.getFontWeight(),
                textAlign = element.textAlign,
            ),
            modifier = ModifierPreview(element).getModifier().then(modifier),
            hint = element.hintText,
            value = element.text?:"",
            onValueChange = {}
        )
    }
}