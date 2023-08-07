package pcui.main.right.params

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.beans.Element
import widgets.HintTextFiled

// 总行的padding
const val contentPaddingStart = 20
const val contentPaddingEnd = 10

// label的大小
const val labelWidth = 80
const val labelFontSize = 14
val labelColor: Color = Color.White

// 输入框的设置
const val inputPaddingTopBottom = 5
const val inputPaddingStartEnd = 10
const val inputFontSize = 12

/**
 * 输入框的样式
 */
val inputTextStyle = TextStyle(
    color = Color.White,
    lineHeight = 14.sp,
    fontSize = inputFontSize.sp,
)

@Composable
fun ParamSettingTextField(
    text: String,
    hint: String = "",
    modifier: Modifier = Modifier,
    onKeyEvent: ((KeyEvent) -> Boolean) = { _ -> false },
    onValueChange: ((String) -> Unit)? = null
) {
    Box(
        modifier = Modifier.then(modifier)
            .border(1.dp, Color.White, shape = RoundedCornerShape(2.dp))
            .padding(
                start = inputPaddingStartEnd.dp,
                end = inputPaddingStartEnd.dp,
                top = inputPaddingTopBottom.dp,
                bottom = inputPaddingTopBottom.dp
            )
    ) {
        HintTextFiled(
            value = text,
            hint = hint,
            onValueChange = {
                onValueChange?.invoke(it)
            },
            modifier = Modifier.fillMaxWidth().onKeyEvent(onKeyEvent),
            textStyle = LocalTextStyle.current.copy(
                color = inputTextStyle.color,
                lineHeight = inputTextStyle.lineHeight,
                fontSize = inputTextStyle.fontSize,
            )
        )
    }
}

/**
 * 参数设置，输入框设置
 *
 * @author Hld
 */
@Composable
fun ParamSettingInputItem(
    element: Element,
    label: String,
    text: String,
    hint: String = "",
    onValueChange: ((String) -> Unit)? = null,
    showInput: Boolean = true,
    dropdownMenuContent: (@Composable ColumnScope.(close: () -> Unit) -> Unit)? = null
) {
    Box(modifier = Modifier.padding(start = contentPaddingStart.dp, end = contentPaddingEnd.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.width(labelWidth.dp)) {
                Text(
                    text = label,
                    color = labelColor,
                    fontSize = labelFontSize.sp
                )
            }
            val rightArrowHeight = remember { mutableStateOf(0) }
            val dropdownMenuWidth = remember { mutableStateOf(0) }
            Box(
                modifier = Modifier.weight(1f)
                    .border(1.dp, Color.White, shape = RoundedCornerShape(2.dp))
                    .onGloballyPositioned {
                        rightArrowHeight.value = it.size.height
                        dropdownMenuWidth.value = it.size.width
                    },
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Box(
                        modifier = Modifier.weight(1f)
                            .padding(
                                start = inputPaddingStartEnd.dp,
                                end = inputPaddingStartEnd.dp,
                                top = inputPaddingTopBottom.dp,
                                bottom = inputPaddingTopBottom.dp
                            )
                    ) {
                        if (showInput) {
                            val textFiled = remember { mutableStateOf(text) }
                            // element切换，需要重新刷新输入框
                            var lastElement = remember<Element?> { null }
                            if (lastElement != element) {
                                lastElement = remember { element }
                                textFiled.value = text
                            }
                            // text从外部改变，需要重新刷新输入框
                            var lastText = remember { "" }
                            if (lastText != text) {
                                lastText = remember { text }
                                textFiled.value = text
                            }
                            HintTextFiled(
                                value = textFiled.value,
                                hint = hint,
                                onValueChange = {
                                    onValueChange?.invoke(it)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = LocalTextStyle.current.copy(
                                    color = inputTextStyle.color,
                                    lineHeight = inputTextStyle.lineHeight,
                                    fontSize = inputTextStyle.fontSize,
                                )
                            )
                        } else {
                            Text(
                                text = text,
                                color = inputTextStyle.color,
                                lineHeight = inputTextStyle.lineHeight,
                                fontSize = inputTextStyle.fontSize
                            )
                        }
                    }
                    if (dropdownMenuContent != null) {
                        val dropdownMenuExpanded = remember { mutableStateOf(false) }
                        Spacer(
                            modifier = Modifier.width(1.dp).height(rightArrowHeight.value.dp)
                                .background(Color.White)
                        )
                        Box(
                            modifier = Modifier.width(rightArrowHeight.value.dp)
                                .height(rightArrowHeight.value.dp).clickable {
                                    dropdownMenuExpanded.value = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource("icon_arraw_triangle.png"),
                                contentDescription = "下拉",
                                modifier = Modifier.width(10.dp).height(10.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = dropdownMenuExpanded.value,
                            modifier = Modifier.background(color = Color(0xff333333))
                                .width(dropdownMenuWidth.value.dp),
                            onDismissRequest = {
                                dropdownMenuExpanded.value = false
                            }
                        ) {
                            dropdownMenuContent.invoke(this) {
                                dropdownMenuExpanded.value = false
                            }
                        }

                    }
                }
            }
        }
    }
}
