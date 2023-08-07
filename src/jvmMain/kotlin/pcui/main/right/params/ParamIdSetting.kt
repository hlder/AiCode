package pcui.main.right.params

import androidx.compose.runtime.Composable
import pcui.beans.Element

@Composable
fun ParamIdSetting(element: Element, notifyChange: () -> Unit){
    ParamSettingInputItem(
        element = element,
        label = "设置ID:",
        text = element.id,
        onValueChange = {
            element.id = it
            notifyChange.invoke()
        }
    )
}