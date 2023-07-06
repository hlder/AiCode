package pcui.beans.elements

import createcode.templatecode.elements.ButtonCreator
import createcode.templatecode.elements.ElementCreator
import createcode.util.toCodeString
import pcui.beans.Element
import pcui.beans.TextAlign
import pcui.beans.TextWeight

open class ButtonElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    text: String? = null, // 文本内容
    textColor: Int? = null, //字体颜色
    textSize: Int? = null, // 字体大小，dp
    textWeight: TextWeight? = null, // 字体的粗细
    textAlign: TextAlign? = null, // 文字的对齐方式
    val buttonAction: ButtonAction // 按钮的行为（点击事件跳转）
) : TextElement(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, text, textColor, textSize, textWeight, textAlign) {
    override fun createElementCreator(): ElementCreator<out Element> = ButtonCreator(this)
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