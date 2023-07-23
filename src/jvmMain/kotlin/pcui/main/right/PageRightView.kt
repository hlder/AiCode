package pcui.main.right

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pcui.main.PageMainViewModel
import widgets.HintTextFiled

class PageRightView(private val viewModel: PageMainViewModel) {
    @Composable
    fun BaseConfig() {
        val nowSelectedElement = remember { viewModel.nowSelectedElement }.value
        viewModel.changeParamVersion.value
        println("-----------baseConfig id:${nowSelectedElement?.id}")
        Column {
            Text("基础配置", modifier = Modifier.fillMaxWidth().padding(10.dp))
            ItemConfig(viewModel, "设置ID:", "(全局唯一)", nowSelectedElement?.id ?: "") {
                nowSelectedElement?.id = it
            }
            ItemConfig(viewModel, "宽度:", "", "${nowSelectedElement?.width ?: ""}") { text ->
                nowSelectedElement?.width = getIntValue(text,nowSelectedElement?.width)
            }
            ItemConfig(viewModel, "高度:", text = "${nowSelectedElement?.height ?: ""}") { text ->
                nowSelectedElement?.height = getIntValue(text,nowSelectedElement?.height)
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 10.dp)
            ) {
                ItemLabel("内间距(padding)")
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    ItemHintTextFiled(
                        viewModel,
                        hint = "top",
                        text = "${nowSelectedElement?.paddingTop ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.paddingTop = getIntValue(text,nowSelectedElement?.paddingTop)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "bottom",
                        text = "${nowSelectedElement?.paddingBottom ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.paddingBottom = getIntValue(text,nowSelectedElement?.paddingBottom)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "start",
                        text = "${nowSelectedElement?.paddingStart ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.paddingStart = getIntValue(text,nowSelectedElement?.paddingStart)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "end",
                        text = "${nowSelectedElement?.paddingEnd ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.paddingEnd = getIntValue(text,nowSelectedElement?.paddingEnd)
                    }
                }
            }
            ItemConfig(viewModel, "背景颜色:", text = "${nowSelectedElement?.backgroundColor ?: ""}") {
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 10.dp)
            ) {
                ItemLabel("背景圆角弧度")
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    ItemHintTextFiled(
                        viewModel,
                        hint = "topLeft",
                        text = "${nowSelectedElement?.backgroundRoundTopLeft ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.backgroundRoundTopLeft = getIntValue(text,nowSelectedElement?.backgroundRoundTopLeft)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "bottomLeft",
                        text = "${nowSelectedElement?.backgroundRoundBottomLeft ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.backgroundRoundBottomLeft = getIntValue(text,nowSelectedElement?.backgroundRoundBottomLeft)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "topRight",
                        text = "${nowSelectedElement?.backgroundRoundTopRight ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.backgroundRoundTopRight = getIntValue(text,nowSelectedElement?.backgroundRoundTopRight)
                    }
                    ItemHintTextFiled(
                        viewModel,
                        hint = "bottomRight",
                        text = "${nowSelectedElement?.backgroundRoundBottomRight ?: ""}",
                        modifier = Modifier.weight(2f).padding(start = 10.dp)
                    ) { text ->
                        nowSelectedElement?.backgroundRoundBottomRight = getIntValue(text,nowSelectedElement?.backgroundRoundBottomRight)
                    }
                }
            }
            ItemConfig(viewModel, "设置权重:", "（权重越大占位越多）", "${nowSelectedElement?.weight ?: ""}") {text ->
                nowSelectedElement?.weight = getFloatValue(text,nowSelectedElement?.weight)
            }
        }
    }

    // 转int，如果转不了则返回原本的值
    private fun getIntValue(text: String, def: Int?): Int? {
        return if (text.isNotEmpty()) {
            text.toIntOrNull() ?: def
        } else {
            null
        }
    }

    // 转float，如果转不了则返回原本的值
    private fun getFloatValue(text: String, def: Float?): Float? {
        return if (text.isNotEmpty()) {
            text.toFloatOrNull() ?: def
        } else {
            null
        }
    }

    @Composable
    private fun ItemConfig(
        viewModel: PageMainViewModel,
        label: String,
        hint: String = "",
        text: String? = null,
        modifier: Modifier = Modifier,
        onValueChange: ((String) -> Unit)? = null
    ) {
        Row(
            modifier = Modifier.then(modifier).fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 10.dp)
        ) {
            ItemLabel(label, Modifier.weight(1f).align(Alignment.CenterVertically))
            ItemHintTextFiled(
                viewModel,
                hint,
                text,
                onValueChange = onValueChange,
                modifier = Modifier.weight(2f).align(Alignment.CenterVertically)
            )
        }
    }

    @Composable
    private fun ItemLabel(label: String, modifier: Modifier = Modifier) {
        Text(
            text = label, color = Color.White, fontSize = 12.sp, modifier = Modifier.then(modifier)
        )
    }

    @Composable
    private fun ItemHintTextFiled(
        viewModel: PageMainViewModel,
        hint: String,
        text: String? = null,
        modifier: Modifier = Modifier,
        onValueChange: ((String) -> Unit)? = null
    ) {
        HintTextFiled(
            value = text ?: "", hint = hint, onValueChange = {
                onValueChange?.invoke(it)
                viewModel.changeParamVersion.value++
            },
            modifier = Modifier.then(modifier).background(color = Color.White),
            textStyle = TextStyle(
                color = Color.Black,
                lineHeight = 14.sp,
                fontSize = 12.sp,
            )
        )
//        val changeParamVersion = remember { viewModel.changeParamVersion }
//
//        val inputText = remember { mutableStateOf("") }
//
//        val lastText = remember { mutableStateOf(text) }
//        if (lastText.value != text) {
//            inputText.value = text ?: ""
//            lastText.value = text
//        }
//        HintTextFiled(
//            value = inputText.value, hint = hint, onValueChange = {
//                inputText.value = it
//                onValueChange?.invoke(it)
//                viewModel.changeParamVersion.value++
//            },
//            modifier = Modifier.then(modifier).background(color = Color.White),
//            textStyle = TextStyle(
//                color = Color.Black,
//                lineHeight = 14.sp,
//                fontSize = 12.sp,
//            )
//        )
    }
}