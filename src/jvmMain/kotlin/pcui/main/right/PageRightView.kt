package pcui.main.right

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pcui.main.PageMainViewModel
import pcui.main.right.params.ParamBackGroundColorSetting
import pcui.main.right.params.ParamIdSetting
import pcui.main.right.params.ParamPaddingSetting
import pcui.main.right.params.ParamSizeSetting
import pcui.main.right.params.ParamWeightSetting

class PageRightView(private val viewModel: PageMainViewModel) {
    @Composable
    fun BaseConfig() {
        val nowSelectedElement = remember { viewModel.nowSelectedElement }.value
        viewModel.changeParamVersion.value
        println("-----------baseConfig id:${nowSelectedElement?.id}")
        Column {
            Text("基础配置", modifier = Modifier.fillMaxWidth().padding(10.dp))
            nowSelectedElement?.let {
                val notifyChange: () -> Unit = {
                    viewModel.changeParamVersion.value++
                }
                // 设置控件id
                ParamIdSetting(it, notifyChange)
                Spacer(modifier = Modifier.height(10.dp))
                // 控件的大小设置
                ParamSizeSetting(it, notifyChange)
                Spacer(modifier = Modifier.height(10.dp))
                // 内间距设置
                ParamPaddingSetting(it, notifyChange)
                Spacer(modifier = Modifier.height(10.dp))
                // 设置背景颜色
                ParamBackGroundColorSetting(it, notifyChange)
                Spacer(modifier = Modifier.height(10.dp))
                // 设置权重
                ParamWeightSetting(it, notifyChange)
            }
        }
    }
}