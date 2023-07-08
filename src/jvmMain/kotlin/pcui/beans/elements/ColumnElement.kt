package pcui.beans.elements

import androidx.compose.ui.graphics.Color
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
    backgroundColor: Color? = null, // 背景颜色
    weight: Float? = null,
    align: LayoutAlignment? = null,
    childs: List<Element>? = null, // 如果是row，column则会有子元素
) : LayoutElement(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, weight, align, childs) {
    override fun createElementCreator(): ElementCreator<out Element> = ColumnCreator(this)
}
