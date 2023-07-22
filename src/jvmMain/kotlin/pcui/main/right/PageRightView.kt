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
        println("-----------baseConfig id:${nowSelectedElement?.id}")
        Column {
            Text("基础配置", modifier = Modifier.fillMaxWidth().padding(10.dp))
            ItemConfig("设置ID:", "(全局唯一)", nowSelectedElement?.id ?: "")
            ItemConfig("宽度:", "", "${nowSelectedElement?.width ?: ""}")
            ItemConfig("高度:", text = "${nowSelectedElement?.height ?: ""}")
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 10.dp)
            ) {
                ItemLabel("内间距(padding)")
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    ItemHintTextFiled("top", modifier = Modifier.weight(2f).padding(start = 10.dp))
                    ItemHintTextFiled("bottom", modifier = Modifier.weight(2f).padding(start = 10.dp))
                    ItemHintTextFiled("left", modifier = Modifier.weight(2f).padding(start = 10.dp))
                    ItemHintTextFiled("right", modifier = Modifier.weight(2f).padding(start = 10.dp))
                }
            }
            ItemConfig("背景颜色:", text = "${nowSelectedElement?.backgroundColor ?: ""}")
            ItemConfig("背景圆角弧度:", text = "${nowSelectedElement?.backgroundRounded ?: ""}")
            ItemConfig("设置权重:", "（权重越大占位越多）", "${nowSelectedElement?.weight ?: ""}")
        }
    }

    @Composable
    private fun ItemConfig(label: String, hint: String = "", text: String = "", modifier: Modifier = Modifier) {
        Row(
            modifier = Modifier.then(modifier).fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp, start = 20.dp, end = 10.dp)
        ) {
            ItemLabel(label, Modifier.weight(1f).align(Alignment.CenterVertically))
            ItemHintTextFiled(hint, text, Modifier.weight(2f).align(Alignment.CenterVertically))
        }
    }

    @Composable
    private fun ItemLabel(label: String, modifier: Modifier = Modifier) {
        Text(
            text = label, color = Color.White, fontSize = 12.sp, modifier = Modifier.then(modifier)
        )
    }

    @Composable
    private fun ItemHintTextFiled(hint: String, text: String = "", modifier: Modifier = Modifier) {
        val inputText = remember { mutableStateOf("") }
        inputText.value = text
        HintTextFiled(
            value = inputText.value, hint = hint, onValueChange = {
                inputText.value = it
            },
            modifier = Modifier.then(modifier).background(color = Color.White),
            textStyle = TextStyle(
                color = Color.Black,
                lineHeight = 14.sp,
                fontSize = 12.sp,
            )
        )
    }
}