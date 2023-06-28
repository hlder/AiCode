package pcui.beans.elements

import createcode.templatecode.elements.DividerCreator
import createcode.templatecode.elements.ElementCreator
import pcui.beans.Element

class DividerElement(
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val dividerColor: Int? = null, // 分割线的颜色
) : Element(width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor) {
    private var dividerCreator: DividerCreator? = null
    override fun createElementCreator(): ElementCreator<out Element> = dividerCreator ?: DividerCreator(this).apply {
        dividerCreator = this
    }
}
