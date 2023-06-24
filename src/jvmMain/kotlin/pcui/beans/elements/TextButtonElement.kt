package pcui.beans.elements

import pcui.beans.Element
import pcui.beans.ElementType
import pcui.beans.TextWeight

open class TextButtonElement(
    type: ElementType,
    width: Int? = null,
    height: Int? = null,
    paddingTop: Int? = null,
    paddingBottom: Int? = null,
    paddingStart: Int? = null,
    paddingEnd: Int? = null,
    backgroundColor: Int? = null, // 背景颜色
    val text: String? = null, // 文本内容
    val textColor: Int? = null, //字体颜色
    val textSize: Int? = null, // 字体大小，dp
    val textWeight: TextWeight? = null, // 字体的粗细
) : Element(type, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor)
