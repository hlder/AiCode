package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import pcui.beans.Element

abstract class LayoutElement(
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
    val isNeedScroll: Boolean? = false, // 是否需要滚动
    val align: LayoutAlignment? = null,
    val childs: List<Element>? = null, // 如果是row，column则会有子元素
) : Element(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, backgroundRounded, weight)

/**
 * 布局的对齐方式
 */
enum class LayoutAlignment {
    START, END, TOP, BOTTOM, CENTER_HORIZONTAL, CENTER_VERTICAL, CENTER, TOP_START, TOP_END, BOTTOM_START, BOTTOM_END
}