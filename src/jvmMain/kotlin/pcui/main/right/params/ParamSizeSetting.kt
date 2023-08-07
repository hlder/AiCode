package pcui.main.right.params

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pcui.beans.Element

/**
 * 对大小的设置
 */
@Composable
fun ParamSizeSetting(element: Element, notifyChange: () -> Unit) {
    Column {
        ItemParamSizeSetting(label = "宽度", element = element, size = element.width) {
            element.width = it
            notifyChange.invoke()
        }
        Spacer(modifier = Modifier.height(10.dp))
        ItemParamSizeSetting(label = "高度", element = element, size = element.height) {
            element.height = it
            notifyChange.invoke()
        }
    }

}

@Composable
fun ItemParamSizeSetting(
    label: String,
    element: Element,
    size: Int?,
    notifyChange: (size: Int?) -> Unit
) {
    val text = when (size) {
        null -> ""
        Integer.MAX_VALUE -> "填满"
        else -> "$size"
    }
    val isShowInput = remember { mutableStateOf(true) }
    isShowInput.value = size == null || size != Integer.MAX_VALUE
    ParamSettingInputItem(
        element = element,
        label = "$label:",
        text = text,
        hint = label,
        showInput = isShowInput.value,
        onValueChange = {
            it.toIntOrNull()?.apply {
                notifyChange.invoke(this)
            }
        },
    ) { close ->
        DropdownMenuItem(
            onClick = {
                close()
                notifyChange.invoke(Integer.MAX_VALUE)
            }
        ) {
            Text("填满", color = Color.White)
        }
        DropdownMenuItem(
            onClick = {
                close()
                if (size == Integer.MAX_VALUE) {
                    notifyChange.invoke(null)
                } else {
                    notifyChange.invoke(size)
                }
            }
        ) {
            Text("自定义", color = Color.White)
        }
    }
}
