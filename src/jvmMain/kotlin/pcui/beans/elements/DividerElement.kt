package pcui.beans.elements

import pcui.beans.Element
import pcui.beans.ElementType

class DividerElement(
    type: ElementType,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val dividerColor: Int? = null, // 分割线的颜色
) : Element(type, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor)
