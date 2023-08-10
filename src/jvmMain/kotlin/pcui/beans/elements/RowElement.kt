package pcui.beans.elements

import androidx.compose.ui.graphics.Color
import createcode.templatecode.elements.ElementCreator
import createcode.templatecode.elements.RowCreator
import pcui.beans.Element
import pcui.main.PageMainViewModel
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
    backgroundRoundTopLeft: Int? = null, // 背景的圆角左上角
    backgroundRoundTopRight: Int? = null, // 背景的圆角右上角
    backgroundRoundBottomLeft: Int? = null, // 背景的圆角左下角
    backgroundRoundBottomRight: Int? = null, // 背景的圆角右下角
    weight: Float? = null,
    isNeedScroll: Boolean? = false, // 是否需要滚动
    align: LayoutAlignment? = null,
    childs: MutableList<Element> = mutableListOf(), // 如果是row，column则会有子元素
) : LayoutElement(
    id,
    width,
    height,
    paddingTop,
    paddingBottom,
    paddingStart,
    paddingEnd,
    backgroundColor,
    backgroundRoundTopLeft,
    backgroundRoundTopRight,
    backgroundRoundBottomLeft,
    backgroundRoundBottomRight,
    weight,
    isNeedScroll,
    align,
    childs
) {
    override fun createElementCreator(space: String): ElementCreator<out Element> =
        RowCreator(this, space)

    override fun createElementPreview(viewModel: PageMainViewModel): ElementPreview<out Element> =
        RowPreview(this, viewModel)

    override fun getElementName(): String = "横布局"

    companion object {
        fun new(): RowElement {
            return RowElement("row" + getBaseId())
        }
    }
}
