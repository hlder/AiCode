package pcui.main.right.params

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.Element

@Composable
fun ParamPaddingSetting(element: Element, notifyChange: () -> Unit) {
    Column(
        modifier = Modifier.padding(
            start = contentPaddingStart.dp,
            end = contentPaddingEnd.dp
        )
    ) {
        Text(
            text = "内间距(Padding):",
            color = inputColor,
            fontSize = labelFontSize.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        ParamSettingInputItem(
            element = element,
            label = "左边距",
            text = "${element.paddingStart?:""}",
            onValueChange = {
                if (it.toIntOrNull() != null || it.isEmpty()) {
                    element.paddingStart = it.toIntOrNull()
                }
                notifyChange.invoke()
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        ParamSettingInputItem(
            element = element,
            label = "右边距",
            text = "${element.paddingEnd?:""}",
            onValueChange = {
                if (it.toIntOrNull() != null || it.isEmpty()) {
                    element.paddingEnd = it.toIntOrNull()
                }
                notifyChange.invoke()
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        ParamSettingInputItem(
            element = element,
            label = "上边距",
            text = "${element.paddingTop?:""}",
            onValueChange = {
                if (it.toIntOrNull() != null || it.isEmpty()) {
                    element.paddingTop = it.toIntOrNull()
                }
                notifyChange.invoke()
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        ParamSettingInputItem(
            element = element,
            label = "下边距",
            text = "${element.paddingBottom?:""}",
            onValueChange = {
                if (it.toIntOrNull() != null || it.isEmpty()) {
                    element.paddingBottom = it.toIntOrNull()
                }
                notifyChange.invoke()
            }
        )
    }
}