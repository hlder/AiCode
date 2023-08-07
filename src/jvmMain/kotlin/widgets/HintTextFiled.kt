package widgets

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@Composable
fun HintTextFiled(
    value: String,
    hint: String? = null,
    cursorColor: Color = Color.Black,
    hintColor: Color = Color.Gray,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle()
) {
    BasicTextField(
        textStyle = textStyle,
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            if (value.isEmpty() && hint != null) {
                Text(
                    text = hint,
                    color = hintColor,
                    fontSize = textStyle.fontSize,
                    lineHeight = textStyle.lineHeight
                )
            }
            innerTextField()
        },
        cursorBrush = SolidColor(cursorColor),
    )
}