package pcui.beans.elements

import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.TextFieldCreator
import pcui.beans.Element
import pcui.beans.TextAlign
import pcui.beans.TextWeight

open class TextFieldElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val text: String? = null, // 文本内容
    val textColor: Int? = null, //字体颜色
    val textSize: Int? = null, // 字体大小，dp
    val textWeight: TextWeight? = null, // 字体的粗细
    val textAlign: TextAlign? = null // 文字的对齐方式
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor) {
    override fun createElementCreator(): ElementCreator<out Element> = TextFieldCreator(this)
}
