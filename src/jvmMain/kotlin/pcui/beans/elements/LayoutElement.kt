package pcui.beans.elements

import pcui.beans.Element

abstract class LayoutElement(
    id: String,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    weight: Float? = null,
    val align: LayoutAlignment? = null,
    val childs: List<Element>? = null, // 如果是row，column则会有子元素
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, weight)

/**
 * 布局的对齐方式
 */
enum class LayoutAlignment {
    START, END, TOP, BOTTOM, CENTER_HORIZONTAL, CENTER_VERTICAL, CENTER, TOP_START, TOP_END, BOTTOM_START, BOTTOM_END
}