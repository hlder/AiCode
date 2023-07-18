package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.RowCreator
import pcui.beans.Element
import pcui.previews.ElementPreview
import pcui.previews.RowPreview

class RowElement(
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
    isNeedScroll: Boolean? = false, // 是否需要滚动
    align: LayoutAlignment? = null,
    childs: MutableList<Element>? = null, // 如果是row，column则会有子元素
) : LayoutElement(id, width, height, paddingTop, paddingBottom, paddingStart, paddingEnd, backgroundColor, backgroundRounded, weight, isNeedScroll, align, childs) {
    override fun createElementCreator(space: String): ElementCreator<out Element> = RowCreator(this, space)
    override fun createElementPreview(): ElementPreview<out Element> = RowPreview(this)
}
