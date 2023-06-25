package pcui.beans.elements

import pcui.beans.Element

class SpaceElement(
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
) : Element(width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor)
