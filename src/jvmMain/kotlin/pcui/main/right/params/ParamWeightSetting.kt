package pcui.main.right.params

import androidx.compose.runtime.Composable
import pcui.beans.Element

@Composable
fun ParamWeightSetting(element: Element, notifyChange: () -> Unit) {
    ParamSettingInputItem(
        element = element,
        label = "权重:",
        text = "${element.weight ?: ""}",
        onValueChange = {
            val value = it.toFloatOrNull()
            if (value != null) {
                if (it.isEmpty()) {
                    element.weight = null
                } else if (value == 0f) {
                    element.weight = null
                }else {
                    element.weight = value
                }
            }
            notifyChange.invoke()
        }
    )
}