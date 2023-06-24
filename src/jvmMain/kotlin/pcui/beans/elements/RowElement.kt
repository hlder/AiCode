package pcui.beans.elements

import pcui.beans.Element

class RowElement(
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val childs: List<Element>? = null, // 如果是row，column则会有子元素
) : Element(width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor)
