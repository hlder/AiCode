package pcui.beans.elements

import createcode.templatecode.elements.ButtonCreator
import createcode.templatecode.elements.ElementCreator
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
    textAlign: TextAlign? = null // 文字的对齐方式
) : TextElement(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, text, textColor, textSize, textWeight, textAlign) {
    private var buttonCreator: ButtonCreator? = null
    override fun createElementCreator(): ElementCreator<out Element> = buttonCreator ?: ButtonCreator(this).apply {
        buttonCreator = this
    }
}
