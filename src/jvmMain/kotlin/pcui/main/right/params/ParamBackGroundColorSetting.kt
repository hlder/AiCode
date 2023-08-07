package pcui.main.right.params

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.Element
import pcui.main.right.toColorHexString
import pcui.main.right.toColorInt

@Composable
fun ParamBackGroundColorSetting(element: Element, notifyChange: () -> Unit) {
    val colorStr = element.backgroundColor?.run {
        val alphaStr = (alpha * 255).toInt().toColorHexString()
        val redStr = (red * 255).toInt().toColorHexString()
        val greenStr = (green * 255).toInt().toColorHexString()
        val blueStr = (blue * 255).toInt().toColorHexString()
        println("a:${alphaStr} r:${redStr} g:${greenStr} b:${blueStr}")
        "#${alphaStr}${redStr}${greenStr}${blueStr}"
    } ?: ""
    ParamSettingInputItem(
        element = element,
        label = "背景颜色:",
        text = colorStr,
        showInput = false,
        onValueChange = {}
    ) { close ->
        MenuContent(colorStr) {
            element.backgroundColor = it
            notifyChange.invoke()
        }
    }
}

/**
 * 菜单的内容
 */
@Composable
private fun MenuContent(colorStr: String, notifyChange: (color: Color) -> Unit) {
    val inputText = remember { mutableStateOf(colorStr) }
    var lastColorStr = remember { colorStr }
    if (lastColorStr != colorStr) {
        lastColorStr = remember { colorStr }
        inputText.value = colorStr
    }

    DropdownMenuItem(
        onClick = {}, enabled = false
    ) {
        val itemClickFun: (color: Color) -> Unit = {
            notifyChange.invoke(it)
        }
        Column {
            Row {
                ColorItem(Color.Black, itemClickFun)
                ColorItem(Color.DarkGray, itemClickFun)
                ColorItem(Color.Gray, itemClickFun)
                ColorItem(Color.LightGray, itemClickFun)
            }
            Row {
                ColorItem(Color.White, itemClickFun)
                ColorItem(Color.Red, itemClickFun)
                ColorItem(Color.Green, itemClickFun)
                ColorItem(Color.Blue, itemClickFun)
            }
            Row {
                ColorItem(Color.Yellow, itemClickFun)
                ColorItem(Color.Cyan, itemClickFun)
                ColorItem(Color.Magenta, itemClickFun)
                ColorItem(Color.Transparent, itemClickFun)
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "色值:",
                    color = labelColor,
                    fontSize = labelFontSize.sp
                )
                Spacer(modifier = Modifier.width(2.dp))
                ParamSettingTextField(
                    text = inputText.value,
                    modifier = Modifier.weight(1f)
                ) {
                    if (it.length <= 9) {
                        inputText.value = it
                    }
                }
                Spacer(modifier = Modifier.width(2.dp))

                Box(
                    modifier = Modifier
                        .background(color = Color.Blue, shape = RoundedCornerShape(5.dp))
                        .clickable {
                            doChangeColor(inputText.value, notifyChange)
                        }
                ) {
                    Text(
                        text = "确定",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                    )
                }
            }

        }
    }
}

// 执行切换color
private fun doChangeColor(text: String, notifyChange: (color: Color) -> Unit) {
    var inputTextStr = text
    if (inputTextStr.startsWith("#")) {
        inputTextStr = inputTextStr.replace("#", "")
    }
    when (inputTextStr.length) {
        6 -> {
            val red = inputTextStr.substring(0, 1).toColorInt() / 255f
            val green = inputTextStr.substring(2, 3).toColorInt() / 255f
            val blue = inputTextStr.substring(4, 5).toColorInt() / 255f
            notifyChange.invoke(Color(red, green, blue))
        }
        8 -> {
            val alpha = inputTextStr.substring(0, 1).toColorInt() / 255f
            val red = inputTextStr.substring(2, 3).toColorInt() / 255f
            val green = inputTextStr.substring(4, 5).toColorInt() / 255f
            val blue = inputTextStr.substring(6, 7).toColorInt() / 255f
            notifyChange.invoke(Color(alpha, red, green, blue))
        }
        else -> {

        }
    }
}

@Composable
private fun ColorItem(color: Color, click: (color: Color) -> Unit) {
    Box(
        modifier = Modifier.padding(5.dp)
    ) {
        Spacer(
            modifier = Modifier.background(
                color = color,
                shape = RoundedCornerShape(5.dp)
            ).width(30.dp)
                .height(30.dp)
                .border(2.dp, color = color, shape = RoundedCornerShape(5.dp))
                .clickable {
                    click.invoke(color)
                }
        )
    }
}