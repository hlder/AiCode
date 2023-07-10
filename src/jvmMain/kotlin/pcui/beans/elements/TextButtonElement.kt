package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.TextButtonCreator
import pcui.beans.Element
import pcui.beans.TextWeight
import pcui.previews.ElementPreview

open class TextButtonElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Color? = null, // 背景颜色
    backgroundRounded: Int? = null, // 背景的圆角
    weight: Float? = null,
    val text: String? = null, // 文本内容
    val textColor: Color? = null, //字体颜色
    val textSize: Int? = null, // 字体大小，dp
    val textWeight: TextWeight? = null, // 字体的粗细
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, backgroundRounded, weight) {
    override fun createElementCreator(space: String): ElementCreator<out Element> = TextButtonCreator(this, space)
    override fun createElementPreview(): ElementPreview<out Element> {
        TODO("Not yet implemented")
    }
}
