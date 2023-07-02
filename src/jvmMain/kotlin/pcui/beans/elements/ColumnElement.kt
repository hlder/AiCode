package pcui.beans.elements

import createcode.templatecode.elements.ColumnCreator
import createcode.templatecode.elements.ElementCreator
import pcui.beans.Element

class ColumnElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val childs: List<Element>? = null, // 如果是row，column则会有子元素
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor) {
    private var columnCreator: ColumnCreator? = null
    override fun createElementCreator(): ElementCreator<out Element> = columnCreator ?: ColumnCreator(this).apply {
        columnCreator = this
    }
}
