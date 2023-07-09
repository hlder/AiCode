package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.DividerCreator
import createcode.templatecode.elements.ElementCreator
import pcui.beans.Element

class DividerElement(
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
    val dividerColor: Color? = null, // 分割线的颜色
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, backgroundRounded, weight) {
    override fun createElementCreator(): ElementCreator<out Element> = DividerCreator(this)
}
