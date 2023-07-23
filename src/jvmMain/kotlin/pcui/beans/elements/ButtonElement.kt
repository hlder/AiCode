package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import createcode.templatecode.elements.ButtonCreator
import createcode.templatecode.elements.ElementCreator
import createcode.util.toCodeString
import pcui.beans.Element
import pcui.main.PageMainViewModel
import pcui.previews.ButtonPreview
import pcui.previews.ElementPreview

open class ButtonElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Color? = null, // 背景颜色
    backgroundRoundTopLeft: Int? = null, // 背景的圆角左上角
    backgroundRoundTopRight: Int? = null, // 背景的圆角右上角
    backgroundRoundBottomLeft: Int? = null, // 背景的圆角左下角
    backgroundRoundBottomRight: Int? = null, // 背景的圆角右下角
    text: String? = null, // 文本内容
    textColor: Color? = null, //字体颜色
    textSize: Int? = null, // 字体大小，dp
    textWeight: TextWeight? = null, // 字体的粗细
    textAlign: TextAlign? = null, // 文字的对齐方式
    weight: Float? = null,
    val buttonAction: ButtonAction // 按钮的行为（点击事件跳转）
) : TextElement(
    id,
    width,
    height,
    paddingTop,
    paddingBottom,
    paddingStart,
    paddingEnd,
    backgroundColor,
    backgroundRoundTopLeft,
    backgroundRoundTopRight,
    backgroundRoundBottomLeft,
    backgroundRoundBottomRight,
    weight,
    text,
    textColor,
    textSize,
    textWeight,
    textAlign
) {
    override fun createElementCreator(space: String): ElementCreator<out Element> = ButtonCreator(this, space)
    override fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element> =
        ButtonPreview(this, viewModel)
}

data class Action(
    val name: String
)

sealed class ButtonAction(action: Action) {
    abstract fun createCode(space: String): Pair<String, HashSet<String>>
}

class ButtonActionSkipPage(private val action: Action) : ButtonAction(action) {
    override fun createCode(space: String): Pair<String, HashSet<String>> {
        val pageName = action.name
        val codeStr = """
            navController.navigate("$pageName")
        """.toCodeString(space)
        val importStr = hashSetOf("")
        return Pair(codeStr, importStr)
    }
}