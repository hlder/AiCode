package widgets

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun HintTextFiled(
    value: String,
    hint: String? = null,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle
) {
    BasicTextField(textStyle = textStyle,
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        decorationBox = { innerTextField ->
            if (value.isEmpty() && hint != null) {
                Text(text = hint, color = Color.Gray, fontSize = textStyle.fontSize)
            }
            innerTextField()
        })
}