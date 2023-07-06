package pcui.beans.elements

import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.RowCreator
import pcui.beans.Element

class RowElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    childs: List<Element>? = null, // 如果是row，column则会有子元素
) : LayoutElement(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, childs) {
    override fun createElementCreator(): ElementCreator<out Element> = RowCreator(this)
}
